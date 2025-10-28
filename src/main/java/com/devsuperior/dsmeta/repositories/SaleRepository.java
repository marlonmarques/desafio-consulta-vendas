package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @EntityGraph(attributePaths = {"seller"})
    @Query("SELECT obj FROM Sale obj " +
           "WHERE (:min IS NULL OR obj.date >= :min) " +
           "AND (:max IS NULL OR obj.date <= :max) " +
           "AND (:name IS NULL OR UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')))")
    Page<Sale> findSalesPage(
                     @Param("min") LocalDate min, 
                     @Param("max") LocalDate max, 
                     @Param("name") String name, 
                     Pageable pageable);

    //@EntityGraph(attributePaths = {"seller"})
    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinSummaryDTO(obj.seller.name, SUM(obj.amount)) " +
           "FROM Sale obj JOIN obj.seller " +
           "WHERE (:min IS NULL OR obj.date >= :min) " +
           "AND (:max IS NULL OR obj.date <= :max) " +
           "GROUP BY obj.seller.name")
    List<SaleMinSummaryDTO> findSummin(
                     @Param("min") LocalDate min, 
                     @Param("max") LocalDate max);

}
