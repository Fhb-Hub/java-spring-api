package com.fhbhub.javaspringapi.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fhbhub.javaspringapi.serializer.CreditCardMaskSerializer;
import com.github.dozermapper.core.Mapping;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({ "id", "firstName", "lastName", "address", "gender" })
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @JsonProperty("first_name")
    @NotNull(message = "Campo 'title' não pode ser nulo")
    private String firstName;

    @JsonProperty("last_name")
    @NotNull(message = "Campo 'title' não pode ser nulo")
    private String lastName;


    @Schema(
            description = "Gender of the person. It can be 'male' or 'female'.",
            allowableValues = {"male", "female"},
            example = "male"
    )
    @NotNull(message = "Campo 'title' não pode ser nulo")
    private String gender;

    @NotNull(message = "Campo 'title' não pode ser nulo")
    private String address;

    @JsonSerialize(using = CreditCardMaskSerializer.class)
    @NotNull(message = "Campo 'creditCard' não pode ser nulo")
    @JsonProperty("credit_card")
    private String creditCard;
}