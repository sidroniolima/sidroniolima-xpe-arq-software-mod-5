package br.com.sidroniolima.xpe.controller.api;

import br.com.sidroniolima.xpe.controller.product.ProductRequest;
import br.com.sidroniolima.xpe.controller.product.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "products")
@Tag(name = "Product")
public interface ProductAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "A internal server error was thrown")
    })
    ResponseEntity<?> create(@RequestBody ProductRequest aRequest);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a product by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "404", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "A internal server error was thrown")
    })
    ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody ProductRequest aRequest);

    @DeleteMapping(
            value = "{id}"
    )
    @Operation(summary = "Delete a product by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "A internal server error was thrown")
    })
    void deleteById(@PathVariable(name = "id") String id);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a product by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product was not found"),
            @ApiResponse(responseCode = "500", description = "A internal server error was thrown")
    })
    ResponseEntity<?> getById(@PathVariable(name = "id") String id);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "500", description = "A internal server error was thrown")
    })
    List<ProductResponse> list(
            @RequestParam(name="search", required = false, defaultValue = "") final String search
    );

    @GetMapping(
            path = "/count",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Return the number of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Counted successfully"),
            @ApiResponse(responseCode = "500", description = "A internal server error was thrown")
    })
    ResponseEntity<?> count();
}
