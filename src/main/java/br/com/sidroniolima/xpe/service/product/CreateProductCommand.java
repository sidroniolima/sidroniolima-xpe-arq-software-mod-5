package br.com.sidroniolima.xpe.service.product;

public record CreateProductCommand(
        String description,
        Boolean active,
        double price
){
    public static CreateProductCommand with(
            final String aDescription,
            final Boolean isActive,
            final Double aPrice
    ){
        return new CreateProductCommand(aDescription, isActive, aPrice);
    }
}
