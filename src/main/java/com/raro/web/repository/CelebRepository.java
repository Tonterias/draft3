package com.raro.web.repository;

import com.raro.web.domain.Celeb;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Celeb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CelebRepository extends JpaRepository<Celeb, Long> {
    @Query("select distinct celeb from Celeb celeb left join fetch celeb.parties")
    List<Celeb> findAllWithEagerRelationships();

    @Query("select celeb from Celeb celeb left join fetch celeb.parties where celeb.id =:id")
    Celeb findOneWithEagerRelationships(@Param("id") Long id);

}
