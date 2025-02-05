package br.com.sidroniolima.xpe.repository.product;

import br.com.sidroniolima.xpe.model.product.Product;
import br.com.sidroniolima.xpe.model.product.ProductID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity(name = "Product")
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE(10,2)")
    private double price;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public ProductJpaEntity() {
    }

    private ProductJpaEntity(final String anId,
                            final String aDescription,
                            final boolean isActive,
                            final double price,
                            final Instant createdAt,
                            final Instant updatedAt,
                            final Instant deletedAt) {
        this.id = anId;
        this.description = aDescription;
        this.active = isActive;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static ProductJpaEntity from(final Product aProduct) {
        return new ProductJpaEntity(
                aProduct.getId().getValue(),
                aProduct.getDescription(),
                aProduct.isActive(),
                aProduct.getPrice(),
                aProduct.getCreatedAt(),
                aProduct.getUpdatedAt(),
                aProduct.getDeletedAt()
        );
    }

    public Product toEntity() {
        return Product.with(
                ProductID.from(getId()),
                getDescription(),
                isActive(),
                getPrice(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    public String getId() {
        return id;
    }

    public ProductJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductJpaEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ProductJpaEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductJpaEntity setPrice(double price) {
        this.price = price;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public ProductJpaEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public ProductJpaEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public ProductJpaEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
