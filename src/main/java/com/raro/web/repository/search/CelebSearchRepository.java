package com.raro.web.repository.search;

import com.raro.web.domain.Celeb;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Celeb entity.
 */
public interface CelebSearchRepository extends ElasticsearchRepository<Celeb, Long> {
}
