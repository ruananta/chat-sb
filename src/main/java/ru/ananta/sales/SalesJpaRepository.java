package ru.ananta.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SalesJpaRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByAmountGreaterThan(BigDecimal amount);
}
