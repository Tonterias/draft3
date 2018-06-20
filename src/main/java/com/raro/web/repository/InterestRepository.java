package com.raro.web.repository;

import com.raro.web.domain.Interest;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Interest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    @Query("select distinct interest from Interest interest left join fetch interest.parties")
    List<Interest> findAllWithEagerRelationships();

    @Query("select interest from Interest interest left join fetch interest.parties where interest.id =:id")
    Interest findOneWithEagerRelationships(@Param("id") Long id);

}
