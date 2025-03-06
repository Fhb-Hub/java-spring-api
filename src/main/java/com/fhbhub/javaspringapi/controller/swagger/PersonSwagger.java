package com.fhbhub.javaspringapi.controller.swagger;

import com.fhbhub.javaspringapi.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@Tag(name = "People", description = "Manage people records efficiently and effortlessly.")
public interface PersonSwagger {

    @Operation(summary = "Register a new person",
            description = "Add a new individual to the system by providing their details in JSON format.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @PostMapping(consumes = {"application/json"})
    ResponseEntity<PersonDTO> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Person data in JSON format")
    @RequestBody PersonDTO person);

    @Operation(summary = "Retrieve all registered people",
            description = "Fetch a comprehensive list of all individuals stored in the system, complete with relevant details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @GetMapping
    ResponseEntity<List<PersonDTO>> findAll();

    @Operation(summary = "Find a person by ID",
            description = "Retrieve details of a specific individual using their unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @GetMapping("/{id}")
    ResponseEntity<PersonDTO> findById(@Parameter(description = "Unique ID of the person") @PathVariable("id") Long id);

    @Operation(summary = "Update an existing person",
            description = "Modify the details of an existing person by passing updated information in JSON format.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @PutMapping(consumes = {"application/json"})
    ResponseEntity<PersonDTO> update(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated person data in JSON format")
    @RequestBody PersonDTO person);

    @Operation(summary = "Remove a person from the system",
            description = "Delete a person’s record by providing their unique ID. This action is irreversible.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "Unique ID of the person to be deleted", example = "1")
    @PathVariable("id") Long id);
}
