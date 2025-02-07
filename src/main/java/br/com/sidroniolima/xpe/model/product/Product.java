package br.com.sidroniolima.xpe.model.product;

import br.com.sidroniolima.xpe.model.Entity;
import br.com.sidroniolima.xpe.utils.InstantUtils;

import java.time.Instant;

public class Product extends Entity<ProductID> {

    private String description;
    private boolean active;
    private double price;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Product(final ProductID anId,
                   final String description,
                   final boolean active,
                   final double price,
                   final Instant createdAt,
                   final Instant updatedAt,
                   final Instant deletedAt
    ) {
        super(anId);
        this.description = description;
        this.active = active;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Product newProduct(
            final String aDescription,
            final boolean isActive,
            final double aPrice
    ) {
        final var anId = ProductID.unique();
        final var now = InstantUtils.now();
        final var deleteAt = isActive ? null : now;

        return new Product(anId, aDescription, isActive, aPrice, now, now, deleteAt);
    }

    public static Product with(final ProductID anId,
                               final String aDescription,
                               final boolean isActive,
                               final double aPrice,
                               final Instant createdAt,
                               final Instant updatedAt,
                               final Instant deletedAt
    ) {
        return new Product(anId,
                aDescription,
                isActive,
                aPrice,
                createdAt,
                updatedAt,
                deletedAt);
    }

    public Product update(String aDescription, Boolean isActive, Double aPrice) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }

        this.description = aDescription;
        this.price = aPrice;
        this.updatedAt = InstantUtils.now();

        return this;
    }

    private void activate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
    }

    private void deactivate() {
        this.deletedAt = null;
        this.active = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public double getPrice() {
        return price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
