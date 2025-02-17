package com.fhbhub.javaspringapi.service;

import com.fhbhub.javaspringapi.data.dto.PersonDTO;
import com.fhbhub.javaspringapi.data.model.Person;
import com.fhbhub.javaspringapi.exception.ResourceNotFoundException;
import com.fhbhub.javaspringapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fhbhub.javaspringapi.mapper.ObjectMapper.parseListObjects;
import static com.fhbhub.javaspringapi.mapper.ObjectMapper.parseObject;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public PersonDTO create(PersonDTO person) {
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        return parseObject(getPersonById(id), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        Person entity = getPersonById(person.getKey());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        repository.delete(getPersonById(id));
    }

    private Person getPersonById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

}
