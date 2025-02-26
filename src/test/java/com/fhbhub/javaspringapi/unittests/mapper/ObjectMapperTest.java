package com.fhbhub.javaspringapi.unittests.mapper;

import com.fhbhub.javaspringapi.data.dto.PersonDTO;
import com.fhbhub.javaspringapi.data.model.Person;
import com.fhbhub.javaspringapi.unittests.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fhbhub.javaspringapi.mapper.ObjectMapper.parseListObjects;
import static com.fhbhub.javaspringapi.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjectMapperTest {
    private MockPerson inputObject;

    @BeforeEach
    void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    @DisplayName("Should convert a single entity to DTO correctly")
    void shouldConvertEntityToDTO() {
        PersonDTO output = parseObject(inputObject.mockEntity(), PersonDTO.class);
        validatePersonDTO(output, 0, "Male");
    }

    @Test
    @DisplayName("Should convert a list of entities to a list of DTOs correctly")
    void shouldConvertEntityListToDTOList() {
        List<PersonDTO> outputList = parseListObjects(inputObject.mockEntityList(), PersonDTO.class);
        assertEquals(14, outputList.size());

        validatePersonDTO(outputList.get(0), 0, "Male");
        validatePersonDTO(outputList.get(7), 7, "Female");
        validatePersonDTO(outputList.get(12), 12, "Male");
    }

    @Test
    @DisplayName("Should convert a single DTO to entity correctly")
    void shouldConvertDTOToEntity() {
        Person output = parseObject(inputObject.mockDTO(), Person.class);
        validatePerson(output, 0, "Male");
    }

    @Test
    @DisplayName("Should convert a list of DTOs to a list of entities correctly")
    void shouldConvertDTOListToEntityList() {
        List<Person> outputList = parseListObjects(inputObject.mockDTOList(), Person.class);
        assertEquals(14, outputList.size());
        System.out.println(outputList.get(0));
        validatePerson(outputList.get(0), 0, "Male");
        validatePerson(outputList.get(7), 7, "Female");
        validatePerson(outputList.get(12), 12, "Male");
    }

    private void validatePersonDTO(PersonDTO person, int index, String expectedGender) {
        assertAll("Validating PersonDTO at index " + index,
                () -> assertEquals("First Name Test" + index, person.getFirstName()),
                () -> assertEquals("Last Name Test" + index, person.getLastName()),
                () -> assertEquals("Address Test" + index, person.getAddress()),
                () -> assertEquals(expectedGender, person.getGender())
        );
    }

    private void validatePerson(Person person, int index, String expectedGender) {
        assertAll("Validating Person at index " + index,
                () -> assertEquals("First Name Test" + index, person.getFirstName()),
                () -> assertEquals("Last Name Test" + index, person.getLastName()),
                () -> assertEquals("Address Test" + index, person.getAddress()),
                () -> assertEquals(expectedGender, person.getGender())
        );
    }
}
