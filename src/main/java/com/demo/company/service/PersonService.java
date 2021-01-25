package com.demo.company.service;

import com.demo.company.entity.Employee;
import com.demo.company.entity.Person;
import com.demo.dto.PersonCreateRequest;

import java.util.List;

public interface PersonService {
    void create(Person person) throws Exception;
    List<Person> findAllPerson() throws Exception;
    Person findPersonByCode(String code) throws Exception;
    void update(String personCode, Person person) throws Exception;
    void deletePersonByCode(String code) throws Exception;
}
