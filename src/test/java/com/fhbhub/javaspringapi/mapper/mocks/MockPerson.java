package com.fhbhub.javaspringapi.mapper.mocks;

import com.fhbhub.javaspringapi.data.model.Person;
import com.fhbhub.javaspringapi.data.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
    	return mockEntity(0);
    }
    
    public PersonDTO mockDTO() {
    	return mockDTO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i));
        }
        return persons;
    }
    
    private Person mockEntity(Integer number) {
    	Person person = new Person();
    	person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    private PersonDTO mockDTO(Integer number) {
    	PersonDTO person = new PersonDTO();
    	person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setKey(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}
