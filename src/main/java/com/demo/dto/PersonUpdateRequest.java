package com.demo.dto;

import com.demo.company.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonUpdateRequest {
    private String personName;
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();
}
