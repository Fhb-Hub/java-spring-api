package com.fhbhub.javaspringapi.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({ "id", "firstName", "lastName", "address", "gender" })
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    private String address;
    private String gender;

}