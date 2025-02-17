package com.fhbhub.javaspringapi.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
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

    @JsonProperty("launch_date")
    private Date launchDate;

    private String title;
    private String author;
    private Double price;

}
