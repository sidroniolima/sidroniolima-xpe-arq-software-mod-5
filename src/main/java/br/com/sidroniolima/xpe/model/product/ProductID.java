package br.com.sidroniolima.xpe.model.product;

import br.com.sidroniolima.xpe.model.Identifier;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

public class ProductID extends Identifier {

    private final String value;

    public ProductID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ProductID unique() {
        final var uniqueId = UUID.randomUUID().toString().toLowerCase().replace("-","");
        return ProductID.from(uniqueId);
    }

    public static ProductID from (final String anId) {
        return new ProductID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return Objects.equals(getValue(), productID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
