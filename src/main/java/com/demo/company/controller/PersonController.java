package com.demo.company.controller;

import com.demo.base.BaseResponse;
import com.demo.base.ListBaseResponse;
import com.demo.base.Metadata;
import com.demo.base.SingleBaseResponse;
import com.demo.company.entity.Employee;
import com.demo.company.entity.Person;
import com.demo.company.service.PersonService;
import com.demo.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = PersonControllerPath.BASE_PATH)
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListBaseResponse<PersonResponse> findAllPerson (@RequestParam String storeId, @RequestParam String channelId,
                                       @RequestParam String clientId, @RequestParam String requestId, @RequestParam String username,
                                       @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<Person> persons = personService.findAllPerson(pageable);
        List<PersonResponse> responses = persons.getContent().stream().map(this::toPersonResponse).collect(Collectors.toList());
        return new ListBaseResponse<>(null, null, true, requestId, responses,
                new Metadata(page, size, persons.getTotalElements()));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(@RequestParam String storeId, @RequestParam String channelId,
                       @RequestParam String clientId, @RequestParam String requestId, @RequestParam String username, @RequestBody PersonCreateRequest request) throws Exception {
        this.personService.create(toPerson(request));
        return new BaseResponse(null, null, true, requestId);
    }

    @RequestMapping(value = EmployeeControllerPath.FIND_BY_CODE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SingleBaseResponse<PersonResponse> findPersonByCode(@RequestParam String storeId,
                                   @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
                                   @RequestParam String username, @PathVariable String code) throws Exception {
        Person person = this.personService.findByPersonCode(code);
        PersonResponse personResponse = Optional.ofNullable(person).map(this::toPersonResponse).orElse(null);
        return new SingleBaseResponse<>(null, null, true, requestId, personResponse);
    }

    @RequestMapping(value = EmployeeControllerPath.DELETE_BY_CODE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deletePersonByCode(@RequestParam String storeId, @RequestParam String channelId,
                                   @RequestParam String clientId, @RequestParam String requestId, @RequestParam String username,
                                   @RequestParam String code) throws Exception {
        this.personService.deleteByPersonCode(code);
        return new BaseResponse(null, null, true, requestId);
    }

    @RequestMapping(value = EmployeeControllerPath.UPDATE_BY_CODE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updatePersonByCode( @RequestParam String storeId, @RequestParam String channelId,
                                    @RequestParam String clientId, @RequestParam String requestId, @RequestParam String username, @PathVariable String code,
                                    @RequestBody PersonUpdateRequest request) throws Exception {
        personService.update(code, toPerson(request));
        return new BaseResponse(null, null, true, requestId);
    }

    private PersonResponse toPersonResponse(Person person) {
        return Optional.ofNullable(person).map(e -> {
            PersonResponse personResponse = PersonResponse.builder().build();
            BeanUtils.copyProperties(e, personResponse);
            return personResponse;
        }).orElse(null);
    }

    private Person toPerson(PersonCreateRequest request) {
        return Optional.ofNullable(request).map(e -> {
            Person person = Person.builder().build();
            BeanUtils.copyProperties(e, person);
            return person;
        }).orElse(null);
    }

    private Person toPerson(PersonUpdateRequest request) {
        return Optional.ofNullable(request).map(e -> {
            Person person = Person.builder().build();
            BeanUtils.copyProperties(e, person);
            return person;
        }).orElse(null);
    }
}
