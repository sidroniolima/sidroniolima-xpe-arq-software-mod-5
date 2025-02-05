package br.com.sidroniolima.xpe.service.product;

import br.com.sidroniolima.xpe.model.product.Product;
import br.com.sidroniolima.xpe.repository.product.ProductJpaEntity;
import br.com.sidroniolima.xpe.repository.product.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public void update(final Product aProduct) {
        this.productRepository.save(ProductJpaEntity.from(aProduct));
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

    public List<Product> getAll() {
        return this.productRepository.findAll(Sort.by("description"))
                .stream()
                .map(ProductJpaEntity::toEntity)
                .toList();
    }

    public List<Product> getByDescription(final String aDescription) {
        final var query = "%" + aDescription.toUpperCase() + "%";
        return this.productRepository.findByDescription(query);
    }

    public int count() {
        return (int) this.productRepository.count();
    }
}
