package com.demo.company.entity;

import com.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = Person.COLLECTION_NAME) // buat document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person extends MongoBaseEntity {
    public static final String COLLECTION_NAME = "person";

//    public static final String FIELD_ID = "id";
    public static final String FIELD_PERSON_CODE = "personCode";
    public static final String FIELD_PERSON_NAME = "personName";
    public static final String FIELD_ADDRESSES = "addresses";

//    @Id
//    @Field(value = MongoBaseEntity.FIELD_ID)
//    private String id;

    @Field(value = Person.FIELD_PERSON_CODE)
    private String personCode;

    @Field(value = Person.FIELD_PERSON_NAME)
    private String personName;

    @Field(value = Person.FIELD_ADDRESSES)
    private List<Address> addresses;

}
