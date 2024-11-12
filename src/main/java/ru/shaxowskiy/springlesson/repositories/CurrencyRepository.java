package ru.shaxowskiy.springlesson.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.springlesson.models.Currency;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyRepository implements CrudRepository<Currency, Integer>
{
    private DataSource dataSource;

    @Autowired
    public CurrencyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Currency findById(Integer id) {
        final String query = "SELECT * FROM currencies WHERE id=?";
        PreparedStatement preparedStatement =
                null;
        Currency currency = null;
        try (Connection connection = dataSource.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            currency = new Currency();
            parsingToCurrencyFromResultSet(currency, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currency;
    }

    @Override
    public Currency findByName(String name) {
        final String query = "SELECT * FROM currencies WHERE code=?";
        PreparedStatement preparedStatement =
                null;
        Currency currency = null;
        try (Connection connection = dataSource.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            currency = new Currency();
            parsingToCurrencyFromResultSet(currency, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currency;
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> currencyList = new ArrayList<>();
        Currency currency = null;
        Statement statement = null;
        try (Connection connection = dataSource.getConnection()){
            statement = connection.createStatement();
            final String query = "SELECT * FROM currencies";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                currency = new Currency();
                parsingToCurrencyFromResultSet(currency, resultSet);
                currencyList.add(currency);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currencyList;
    }

    @Override
    public void create(Currency currency) {
        final String query = "INSERT INTO currencies (code, fullname, sign) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initializingRowsFromDatabase(currency, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Currency currency, Integer id) {
        final String query = "UPDATE currencies SET code=?, fullname=?, sign=? WHERE id=?";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            initializingRowsFromDatabase(currency,preparedStatement);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {
        final String query = "DELETE FROM currencies WHERE id=?";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializingRowsFromDatabase(Currency currency, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, currency.getCode());
        preparedStatement.setString(2, currency.getFullname());
        preparedStatement.setString(3, currency.getSign());
    }

    private static void parsingToCurrencyFromResultSet(Currency currency, ResultSet resultSet) throws SQLException {
        currency.setId(resultSet.getInt("id"));
        currency.setFullname(resultSet.getString("fullname"));
        currency.setCode(resultSet.getString("code"));
        currency.setSign(resultSet.getString("sign"));
    }
}
