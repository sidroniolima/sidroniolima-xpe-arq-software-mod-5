package br.com.sidroniolima.xpe.repository.product;

import br.com.sidroniolima.xpe.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, String> {

    @Query(value = "select p from Product p where (:terms is null or UPPER(p.description) like :description)")
    public List<Product> findByDescription(@Param("description") final String aQuery);
}
