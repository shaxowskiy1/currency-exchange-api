package ru.shaxowskiy.springlesson.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shaxowskiy.springlesson.people.dao.daoPeople;
import ru.shaxowskiy.springlesson.people.model.Person;

@Component
public class PersonValidator implements Validator {

    private final daoPeople Daopeople;

    public PersonValidator(daoPeople daopeople) {
        Daopeople = daopeople;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(Daopeople.getPersonById(person.getEmail()) != null) {

            errors.rejectValue("email", "", "Email already exists");
        }

    }
}
