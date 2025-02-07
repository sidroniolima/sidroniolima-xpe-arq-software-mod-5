package br.com.sidroniolima.xpe.service.product;

import br.com.sidroniolima.xpe.model.product.Product;
import br.com.sidroniolima.xpe.repository.product.ProductJpaEntity;
import br.com.sidroniolima.xpe.repository.product.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        Objects.requireNonNull(productRepository);
        this.productRepository = productRepository;
    }

    public String create(final CreateProductCommand aCommand) {
        final var aDescription = aCommand.description();
        final var isActive = aCommand.active();
        final var aPrice = aCommand.price();

        final var product = Product.newProduct(
                aDescription,
                isActive,
                aPrice
        );

        this.productRepository.save(ProductJpaEntity.from(product));

        return product.getId().getValue();
    }

    public String update(UpdateProductCommand aCommand) {
        final var anId = aCommand.id();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.active();
        final var aPrice = aCommand.price();

        final var existingProduct = this.productRepository.findById(anId)
                .map(ProductJpaEntity::toEntity)
                .orElse(null);

        if (existingProduct == null) {
            return null;
        }
        existingProduct
                .update(aDescription, isActive, aPrice);

        this.productRepository.save(ProductJpaEntity.from(existingProduct));

        return anId;
    }

    public void delete(final String anId) {
        if (productRepository.existsById(anId)) {
            this.productRepository.deleteById(anId);
        }
    }

    public Product getById(final String anId) {
        return this.productRepository.findById(anId)
                .map(ProductJpaEntity::toEntity)
                .orElse(null);
    }

    public List<Product> listAll(final String searchQuery) {

        final var where = Optional.ofNullable(searchQuery)
                .filter(str -> !str.isBlank())
                .map(str -> "%" + str.toUpperCase() + "%")
                .map(this::assembleSpecification)
                .orElse(null);

        return this.productRepository.findAll(Specification.where(where), Sort.by("description"))
                .stream()
                .map(ProductJpaEntity::toEntity)
                .toList();
    }

    private Specification<ProductJpaEntity> assembleSpecification(final String terms) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("description")), terms);
    }

    public int count() {
        return (int) this.productRepository.count();
    }
}
