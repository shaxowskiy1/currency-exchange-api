package ru.shaxowskiy.springlesson.dao;

import org.springframework.stereotype.Component;
import ru.shaxowskiy.springlesson.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class daoPeople {

    private int PEOPLE_COUNT;
    private List<Person> listOfPeople;

    public daoPeople() {
        listOfPeople = new ArrayList<>();
        listOfPeople.add(new Person(++PEOPLE_COUNT, "Nikita"));
        listOfPeople.add(new Person(++PEOPLE_COUNT, "Alex"));
        listOfPeople.add(new Person(++PEOPLE_COUNT, "Eugene"));
    }

    public List<Person> getListOfPeople() {
        return listOfPeople;
    }

    /*
    for(int i = 0; i < listOfPeople.size(); i++)
    {

    }
     */
    public Person getPersonById(int id) {
        return listOfPeople.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        listOfPeople.add(person);
    }

    public void update(int id, Person person) {
        Person updatedPerson = getPersonById(id);
        updatedPerson.setName(person.getName());
    }

    public void delete(int id) {
        listOfPeople.removeIf(person -> person.getId() == id);

    }
}


