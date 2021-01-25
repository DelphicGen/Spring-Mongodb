package com.demo.company.service;

import com.demo.company.entity.Person;
import com.demo.company.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceBean implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void create(Person person) throws Exception {
//        person.setStoreId(MDC.get(Credential.CREDENTIAL_STORE_ID));
        personRepository.save(person);
    }

    @Override
    public List<Person> findAllPerson() throws Exception {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonByCode(String code) throws Exception {
        return personRepository.findFirstByPersonCode(code);
    }

    @Override
    public void update(String personCode, Person person) throws Exception {
        Person oldPerson = personRepository.findFirstByPersonCode(personCode);
        oldPerson.setPersonName(person.getPersonName());
        oldPerson.setAddresses(person.getAddresses());
        personRepository.save(oldPerson);
    }

    @Override
    public void deletePersonByCode(String code) throws Exception {
        this.personRepository.deleteByPersonCode(code);
    }


}
