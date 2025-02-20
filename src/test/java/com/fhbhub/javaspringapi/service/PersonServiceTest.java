package com.fhbhub.javaspringapi.service;


import com.fhbhub.javaspringapi.data.dto.PersonDTO;
import com.fhbhub.javaspringapi.data.model.Person;
import com.fhbhub.javaspringapi.exception.ResourceNotFoundException;
import com.fhbhub.javaspringapi.mocks.MockPerson;
import com.fhbhub.javaspringapi.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    private MockPerson mockPerson;

    @BeforeEach
    public void setUp() {
        mockPerson = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        PersonDTO personDTO = mockPerson.mockDTO();
        Person person = mockPerson.mockEntity();
        person.setId(1L);
        PersonDTO expectedResult = mockPerson.mockDTO();
        expectedResult.setKey(1L);

        when(repository.save(any(Person.class))).thenReturn(person);

        var result = service.create(personDTO);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAll() {
        List<Person> personList = mockPerson.mockEntityList();
        when(repository.findAll()).thenReturn(personList);

        List<PersonDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(personList.size(), result.size());
    }

    @Test
    public void testFindById() {
        PersonDTO personDTO = mockPerson.mockDTO(1);
        Person personEntity = mockPerson.mockEntity(1);

        when(repository.findById(anyLong())).thenReturn(Optional.of(personEntity));

        PersonDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
    }

    @Test
    public void testResourceNotFoundException() {
        String expectedMessage = "No records found for this ID!";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(1L);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testUpdate() {
        PersonDTO personDTO = mockPerson.mockDTO();
        personDTO.setKey(1L);
        Person personEntity = mockPerson.mockEntity();
        personEntity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(personEntity));
        when(repository.save(any(Person.class))).thenReturn(personEntity);

        PersonDTO result = service.update(personDTO);

        assertNotNull(result);
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
    }


    @Test
    public void testDelete() {
        Person personEntity = mockPerson.mockEntity(1);

        when(repository.findById(anyLong())).thenReturn(Optional.of(personEntity));

        service.delete(1L);

        verify(repository, times(1)).delete(personEntity);
    }
}
