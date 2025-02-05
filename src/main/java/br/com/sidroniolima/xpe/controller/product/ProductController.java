package br.com.sidroniolima.xpe.controller.product;

import br.com.sidroniolima.xpe.controller.api.ProductAPI;
import br.com.sidroniolima.xpe.service.product.CreateProductCommand;
import br.com.sidroniolima.xpe.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class ProductController implements ProductAPI {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        Objects.requireNonNull(productService);
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> createProduct(final ProductRequest aRequest) {
        final var aCommand = CreateProductCommand.with(
                aRequest.description(),
                aRequest.active(),
                aRequest.price()
        );

        final var output = this.productService.create(aCommand);

        return ResponseEntity.created(URI.create("/products/" + output)).body(output);
    }

    @Override
    public ProductResponse getById(final String anId) {
        final var product = this.productService.getById(anId);

        return new ProductResponse(
                product.getId().getValue(),
                product.getDescription(),
                product.isActive(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getDeletedAt()
        );
    }
}
