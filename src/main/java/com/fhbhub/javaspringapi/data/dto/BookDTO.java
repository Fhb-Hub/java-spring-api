package com.fhbhub.javaspringapi.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "title", "author", "launchDate", "price"})
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @NotNull(message = "Campo 'title' não pode ser nulo")
    private String title;

    @NotNull(message = "Campo 'author' não pode ser nulo")
    private String author;

    @NotNull(message = "Campo 'price' não pode ser nulo")
    private Double price;

    @JsonProperty("launch_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date launchDate;
}
