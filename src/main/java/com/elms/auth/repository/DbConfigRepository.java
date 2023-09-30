package com.elms.auth.repository;


import com.elms.auth.model.DbConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DbConfigRepository extends JpaRepository<DbConfig, Long>, JpaSpecificationExecutor<DbConfig> {
    DbConfig findByName(String name);

    @Query("SELECT d" +
            " FROM DbConfig d" +
            " JOIN Career r ON d.career = r" +
            " WHERE r.id = :careerId")
    DbConfig findByCareerId(@Param("careerId") Long careerId);
}
