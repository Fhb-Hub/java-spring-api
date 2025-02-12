package com.fhbhub.javaspringapi.controller.swagger;

import com.fhbhub.javaspringapi.data.vo.BookVO;
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

@Tag(name = "Books", description = "Manage book records efficiently and effortlessly.")
public interface BookSwagger {

    @Operation(summary = "Register a new book",
            description = "Add a new book to the system by providing its details in JSON format.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @PostMapping(consumes = {"application/json"})
    ResponseEntity<BookVO> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book data in JSON format")
    @RequestBody BookVO book);

    @Operation(summary = "Retrieve all registered books",
            description = "Fetch a comprehensive list of all books stored in the system, complete with relevant details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @GetMapping
    ResponseEntity<List<BookVO>> findAll();

    @Operation(summary = "Find a book by ID",
            description = "Retrieve details of a specific book using its unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @GetMapping("/{id}")
    ResponseEntity<BookVO> findById(@Parameter(description = "Unique ID of the book", example = "1") @PathVariable("id") Long id);

    @Operation(summary = "Update an existing book",
            description = "Modify the details of an existing book by passing updated information in JSON format.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @PutMapping(consumes = {"application/json"})
    ResponseEntity<BookVO> update(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated book data in JSON format")
    @RequestBody BookVO book);

    @Operation(summary = "Remove a book from the system",
            description = "Delete a book’s record by providing its unique ID. This action is irreversible.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "Unique ID of the book to be deleted", example = "1")
    @PathVariable("id") Long id);
}
