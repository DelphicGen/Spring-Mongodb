package com.demo.company.service;

import com.demo.company.entity.Person;
import com.demo.company.repository.PersonRepository;
import com.demo.config.data.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceBean implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceBean.class);
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void create(Person person) throws Exception {
        person.setStoreId(MDC.get(Credential.CREDENTIAL_STORE_ID));
        personRepository.save(person);
    }

    @Override
    public Page<Person> findAllPerson(Pageable pageable) throws Exception {

//        LOGGER.trace("A TRACE Message");
//        LOGGER.debug("A DEBUG Message");
//        LOGGER.info("An INFO Message");
//        LOGGER.warn("A WARN Message");
//        LOGGER.error("An ERROR Message");
        return personRepository.findByMarkForDeleteFalse(pageable);
    }

    @Override
    public Person findByPersonCode(String code) throws Exception {
        return personRepository.findFirstByPersonCodeAndMarkForDeleteFalse(code);
    }

    @Override
    public void update(String personCode, Person person) throws Exception {
        Person oldPerson = personRepository.findFirstByPersonCodeAndMarkForDeleteFalse(personCode);
        oldPerson.setPersonName(person.getPersonName());
        oldPerson.setAddresses(person.getAddresses());
        personRepository.save(oldPerson);
    }

    @Override
    public void deleteByPersonCode(String code) throws Exception {
        this.personRepository.deleteByPersonCode(code);
    }


}
