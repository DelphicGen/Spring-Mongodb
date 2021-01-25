package com.demo.company.repository;

import com.demo.company.entity.Employee;
import com.demo.company.entity.Person;
import com.demo.dto.PersonCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
//    Person findFirstByPersonCode(String code);
//    void deleteByPersonCode(String code);
    Person findFirstByPersonCodeAndMarkForDeleteFalse(String code);
    Page<Person> findByMarkForDeleteFalse(Pageable pageable);
    void deleteByPersonCode(String code);
}
