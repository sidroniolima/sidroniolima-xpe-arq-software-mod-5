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
    ResponseEntity<?> createProduct(@RequestBody ProductRequest aRequest);

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
    ProductResponse getById(@PathVariable(name = "id") String id);
}
