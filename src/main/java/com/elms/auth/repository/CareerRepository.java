package com.elms.auth.repository;

import com.elms.auth.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CareerRepository extends JpaRepository<Career, Long>, JpaSpecificationExecutor<Career> {
    Career findTopByCareerPath(String careerPath);

    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteAllByAccountId(Long id);

    @Query("SELECT r" +
            " FROM Career r" +
            " WHERE r.account.id = :accountId")
    List<Career> findAllByShopId(@Param("accountId") Long accountId);
}
