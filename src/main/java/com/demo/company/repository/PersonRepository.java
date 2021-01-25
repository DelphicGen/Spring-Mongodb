package com.demo.company.repository;

import com.demo.company.entity.Employee;
import com.demo.company.entity.Person;
import com.demo.dto.PersonCreateRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
    Person findFirstByPersonCode(String code);
    void deleteByPersonCode(String code);
}
