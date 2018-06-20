package com.raro.web.repository;

import com.raro.web.domain.Topic;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("select distinct topic from Topic topic left join fetch topic.posts")
    List<Topic> findAllWithEagerRelationships();

    @Query("select topic from Topic topic left join fetch topic.posts where topic.id =:id")
    Topic findOneWithEagerRelationships(@Param("id") Long id);

}
