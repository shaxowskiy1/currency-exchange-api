package ru.shaxowskiy.springlesson.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.springlesson.models.Currency;
import ru.shaxowskiy.springlesson.models.ExchangeRates;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExchangeRatesRepository implements CrudRepository<ExchangeRates, Integer> {

    private DataSource dataSource;

    @Autowired
    public ExchangeRatesRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public ExchangeRates findById(Integer id) {
        ExchangeRates exchangeRates = null;
        PreparedStatement preparedStatement = null;
        try(Connection connection = dataSource.getConnection()) {

            final String query = "SELECT\n" +
                    "    e.id AS id,\n" +
                    "    baseCurrency.id AS baseCurrencyId,\n" +
                    "    baseCurrency.fullname AS baseCurrencyName,\n" +
                    "    baseCurrency.code AS baseCurrencyCode,\n" +
                    "    baseCurrency.sign AS baseCurrencySign,\n" +
                    "    targetCurrency.id AS targetCurrencyId,\n" +
                    "    targetCurrency.fullname AS targetCurrencyName,\n" +
                    "    targetCurrency.code AS targetCurrencyCode,\n" +
                    "    targetCurrency.sign AS targetCurrencySign,\n" +
                    "    e.rate AS rate\n" +
                    "FROM\n" +
                    "    exchangerates e\n" +
                    "        JOIN\n" +
                    "    currencies baseCurrency ON e.basecurrencyid = baseCurrency.id\n" +
                    "        JOIN\n" +
                    "    currencies targetCurrency ON e.targetcurrencyid = targetCurrency.id\n" +
                    "where e.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            exchangeRates = parsingRowsToExchangeRatesFromDB(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }

    @Override
    public ExchangeRates findByName(String name) {
        ExchangeRates exchangeRates = null;
       PreparedStatement preparedStatement = null;
        try(Connection connection = dataSource.getConnection()) {

            final String query = "SELECT\n" +
                    "    e.id AS id,\n" +
                    "    baseCurrency.id AS baseCurrencyId,\n" +
                    "    baseCurrency.fullname AS baseCurrencyName,\n" +
                    "    baseCurrency.code AS baseCurrencyCode,\n" +
                    "    baseCurrency.sign AS baseCurrencySign,\n" +
                    "    targetCurrency.id AS targetCurrencyId,\n" +
                    "    targetCurrency.fullname AS targetCurrencyName,\n" +
                    "    targetCurrency.code AS targetCurrencyCode,\n" +
                    "    targetCurrency.sign AS targetCurrencySign,\n" +
                    "    e.rate AS rate\n" +
                    "FROM\n" +
                    "    exchangerates e\n" +
                    "        JOIN\n" +
                    "    currencies baseCurrency ON e.basecurrencyid = baseCurrency.id\n" +
                    "        JOIN\n" +
                    "    currencies targetCurrency ON e.targetcurrencyid = targetCurrency.id\n" +
                    "where concat(baseCurrency.code, targetCurrency.code) = ?\n";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            exchangeRates = parsingRowsToExchangeRatesFromDB(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchangeRates;
    }

    @Override
    public List<ExchangeRates> findAll() {
        List<ExchangeRates> exchangeRatesList = new ArrayList<>();
        ExchangeRates exchangeRates = null;
        Currency baseCurrency = null;
        Currency targetCurrency = null;
        Statement statement = null;
        try(Connection connection = dataSource.getConnection()){
            statement = connection.createStatement();
            final String query = "SELECT\n" +
                    "    e.id AS id,\n" +
                    "    baseCurrency.id AS baseCurrencyId,\n" +
                    "    baseCurrency.fullname AS baseCurrencyName,\n" +
                    "    baseCurrency.code AS baseCurrencyCode,\n" +
                    "    baseCurrency.sign AS baseCurrencySign,\n" +
                    "    targetCurrency.id AS targetCurrencyId,\n" +
                    "    targetCurrency.fullname AS targetCurrencyName,\n" +
                    "    targetCurrency.code AS targetCurrencyCode,\n" +
                    "    targetCurrency.sign AS targetCurrencySign,\n" +
                    "    e.rate AS rate\n" +
                    "FROM\n" +
                    "    exchangerates e\n" +
                    "        JOIN\n" +
                    "    currencies baseCurrency ON e.basecurrencyid = baseCurrency.id\n" +
                    "        JOIN\n" +
                    "    currencies targetCurrency ON e.targetcurrencyid = targetCurrency.id;\n";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                exchangeRates = parsingRowsToExchangeRatesFromDB(resultSet);
                exchangeRatesList.add(exchangeRates);
            }
        } catch (SQLException e){
            e.printStackTrace();

        }
        for(ExchangeRates exchangeRates1 : exchangeRatesList){
            System.out.println(exchangeRates1.toString());
        }
        return exchangeRatesList;
    }

    @Override
    public void create(ExchangeRates exchangeRates) {
        final String query = "insert into exchangerates (basecurrencyid, targetcurrencyid, rate) values  (?, ?, ?)";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initializingExchangeRatesForPRST(exchangeRates, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ExchangeRates entity, Integer integer) {

    }

    @Override
    public void delete(Integer id) {
        final String query = "DELETE FROM exchangerates WHERE id=?";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializingExchangeRatesForPRST(ExchangeRates exchangeRates, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, exchangeRates.getBaseCurrency().getId());
        preparedStatement.setInt(2, exchangeRates.getTargetCurrency().getId());
        preparedStatement.setBigDecimal(3, exchangeRates.getRate());
    }

    private static @NotNull ExchangeRates parsingRowsToExchangeRatesFromDB(ResultSet resultSet) throws SQLException {
        Currency targetCurrency;
        Currency baseCurrency;
        ExchangeRates exchangeRates;
        baseCurrency = new Currency();
        targetCurrency = new Currency();
        exchangeRates = new ExchangeRates();
        exchangeRates.setId(resultSet.getInt("id"));
        baseCurrency.setId(resultSet.getInt("baseCurrencyId"));
        baseCurrency.setFullname(resultSet.getString("baseCurrencyName"));
        baseCurrency.setCode(resultSet.getString("baseCurrencyCode"));
        baseCurrency.setSign(resultSet.getString("baseCurrencySign"));
        exchangeRates.setBaseCurrency(baseCurrency);
        targetCurrency.setFullname(resultSet.getString("targetCurrencyName"));
        targetCurrency.setCode(resultSet.getString("targetCurrencyCode"));
        targetCurrency.setSign(resultSet.getString("targetCurrencySign"));
        targetCurrency.setId(resultSet.getInt("targetCurrencyId"));
        exchangeRates.setTargetCurrency(targetCurrency);
        exchangeRates.setRate(resultSet.getBigDecimal("rate"));
        return exchangeRates;
    }
}
