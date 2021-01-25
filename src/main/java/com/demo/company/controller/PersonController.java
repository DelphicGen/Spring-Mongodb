package com.demo.company.controller;

import com.demo.base.BaseResponse;
import com.demo.base.SingleBaseResponse;
import com.demo.company.entity.Employee;
import com.demo.company.entity.Person;
import com.demo.company.service.PersonService;
import com.demo.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = PersonControllerPath.BASE_PATH)
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAllPerson () throws Exception {
        return personService.findAllPerson();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody PersonCreateRequest request) throws Exception {
        Person person = Person.builder().personName(request.getPersonName()).personCode(request.getPersonCode()).addresses(request.getAddresses()).build();
//        BeanUtils.copyProperties(request, person);
        this.personService.create(person);
    }

    @RequestMapping(value = EmployeeControllerPath.FIND_BY_CODE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPersonByCode(@PathVariable String code) throws Exception {
        Person person = this.personService.findPersonByCode(code);
        return person;
    }

    @RequestMapping(value = EmployeeControllerPath.DELETE_BY_CODE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePersonByCode(@RequestParam String code) throws Exception {
        this.personService.deletePersonByCode(code);
    }

    @RequestMapping(value = EmployeeControllerPath.UPDATE_BY_CODE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updatePersonByCode( @PathVariable String code, @RequestBody PersonUpdateRequest request) throws Exception {
//        Person person = Person.builder().personName(request.getPersonName()).addresses(request.getAddresses()).build();
        Person person = Person.builder().build();
        BeanUtils.copyProperties(request, person);
        this.personService.update(code, person);
    }

}
