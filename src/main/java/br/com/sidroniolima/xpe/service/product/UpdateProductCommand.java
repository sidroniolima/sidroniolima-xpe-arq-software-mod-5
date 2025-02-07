package br.com.sidroniolima.xpe.service.product;

public record UpdateProductCommand(
        String id,
        String description,
        Boolean active,
        Double price
) {
    public static UpdateProductCommand with(
            String id,
            String description,
            Boolean active,
            Double price
    ) {
        return new UpdateProductCommand(
                id, description, active, price
        );
    }
}
