package com.fhbhub.javaspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhbhub.javaspringapi.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}