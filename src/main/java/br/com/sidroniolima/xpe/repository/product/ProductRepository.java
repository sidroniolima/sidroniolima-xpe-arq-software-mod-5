package br.com.sidroniolima.xpe.repository.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, String> {

    @Query(value = "select p from Product p where (:terms is null or UPPER(p.description) like :description)")
    List<ProductJpaEntity> findByDescription(@Param("description") final String aQuery);

    List<ProductJpaEntity> findAll(Specification<ProductJpaEntity> where, Sort description);
}
