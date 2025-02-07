package br.com.sidroniolima.xpe.controller.product;

import br.com.sidroniolima.xpe.controller.api.ProductAPI;
import br.com.sidroniolima.xpe.service.product.CreateProductCommand;
import br.com.sidroniolima.xpe.service.product.ProductService;
import br.com.sidroniolima.xpe.service.product.UpdateProductCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductController implements ProductAPI {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        Objects.requireNonNull(productService);
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> create(final ProductRequest aRequest) {
        final var aCommand = CreateProductCommand.with(
                aRequest.description(),
                aRequest.active(),
                aRequest.price()
        );

        final var output = this.productService.create(aCommand);

        return ResponseEntity.created(URI.create("/products/" + output)).body(output);
    }

    @Override
    public ResponseEntity<?> update(String anId, ProductRequest aRequest) {
        final var aCommand = UpdateProductCommand.with(
                anId,
                aRequest.description(),
                aRequest.active(),
                aRequest.price()
        );

        final var updatedId = this.productService.update(aCommand);

        if (updatedId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedId);
    }

    @Override
    public void deleteById(String anId) {
        this.productService.delete(anId);
    }

    @Override
    public ResponseEntity<?> getById(final String anId) {
        final var product = this.productService.getById(anId);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(new ProductResponse(
                product.getId().getValue(),
                product.getDescription(),
                product.isActive(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getDeletedAt()
        ));
    }

    @Override
    public List<ProductResponse> list(final String search) {
        return this.productService.listAll(search)
                .stream()
                .map(product -> {
                    return new ProductResponse(
                            product.getId().getValue(),
                            product.getDescription(),
                            product.isActive(),
                            product.getPrice(),
                            product.getCreatedAt(),
                            product.getUpdatedAt(),
                            product.getDeletedAt()
                    );
                }).toList();
    }

    @Override
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(this.productService.count());
    }
}
