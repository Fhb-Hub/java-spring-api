package com.fhbhub.javaspringapi.converter;

import com.fhbhub.javaspringapi.converter.mocks.MockPerson;
import com.fhbhub.javaspringapi.data.model.Person;
import com.fhbhub.javaspringapi.data.vo.PersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DozerConverterTest {
    private MockPerson inputObject;

    @BeforeEach
    void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    @DisplayName("Should convert a single entity to VO correctly")
    void shouldConvertEntityToVO() {
        PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
        validatePersonVO(output, 0, "Male");
    }

    @Test
    @DisplayName("Should convert a list of entities to a list of VOs correctly")
    void shouldConvertEntityListToVOList() {
        List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
        assertEquals(14, outputList.size());

        validatePersonVO(outputList.get(0), 0, "Male");
        validatePersonVO(outputList.get(7), 7, "Female");
        validatePersonVO(outputList.get(12), 12, "Male");
    }

    @Test
    @DisplayName("Should convert a single VO to entity correctly")
    void shouldConvertVOToEntity() {
        Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
        validatePerson(output, 0, "Male");
    }

    @Test
    @DisplayName("Should convert a list of VOs to a list of entities correctly")
    void shouldConvertVOListToEntityList() {
        List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Person.class);
        assertEquals(14, outputList.size());

        validatePerson(outputList.get(0), 0, "Male");
        validatePerson(outputList.get(7), 7, "Female");
        validatePerson(outputList.get(12), 12, "Male");
    }

    private void validatePersonVO(PersonVO person, int index, String expectedGender) {
        assertAll("Validating PersonVO at index " + index,
                () -> assertEquals(index, person.getId()),
                () -> assertEquals("First Name Test" + index, person.getFirstName()),
                () -> assertEquals("Last Name Test" + index, person.getLastName()),
                () -> assertEquals("Address Test" + index, person.getAddress()),
                () -> assertEquals(expectedGender, person.getGender())
        );
    }

    private void validatePerson(Person person, int index, String expectedGender) {
        assertAll("Validating Person at index " + index,
                () -> assertEquals(index, person.getId()),
                () -> assertEquals("First Name Test" + index, person.getFirstName()),
                () -> assertEquals("Last Name Test" + index, person.getLastName()),
                () -> assertEquals("Address Test" + index, person.getAddress()),
                () -> assertEquals(expectedGender, person.getGender())
        );
    }
}
