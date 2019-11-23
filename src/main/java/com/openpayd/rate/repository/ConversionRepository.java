package com.openpayd.rate.repository;

import com.openpayd.rate.model.entity.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {

    @Transactional
    @Query(value = "SELECT con FROM Conversion con WHERE con.transactionId=:transactionId or transactionDate=:transactionDate ")
    List<Conversion> findByIdOrDate(@Param("transactionId") Long transactionId, @Param("transactionDate") LocalDate transactionDate);

}
