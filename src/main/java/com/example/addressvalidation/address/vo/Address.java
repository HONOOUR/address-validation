package com.example.addressvalidation.address.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Setter
@Getter
public class Address {
    @JsonProperty
    HashMap<String, Object> common;

    @JsonProperty("juso")
    List<HashMap<String, Object>> addresses;
}
