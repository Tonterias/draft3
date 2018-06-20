package com.raro.web.repository.search;

import com.raro.web.domain.Interest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Interest entity.
 */
public interface InterestSearchRepository extends ElasticsearchRepository<Interest, Long> {
}
