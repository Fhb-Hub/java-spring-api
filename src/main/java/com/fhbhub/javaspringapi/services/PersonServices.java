package com.fhbhub.javaspringapi.services;

import com.fhbhub.javaspringapi.converter.DozerConverter;
import com.fhbhub.javaspringapi.data.model.Person;
import com.fhbhub.javaspringapi.data.vo.PersonVO;
import com.fhbhub.javaspringapi.exception.ResourceNotFoundException;
import com.fhbhub.javaspringapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServices {

    private final PersonRepository repository;

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public List<PersonVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        return DozerConverter.parseObject(getPerson(id), PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        Person entity = getPerson(person.getKey());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        repository.delete(getPerson(id));
    }

    private Person getPerson(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

}
