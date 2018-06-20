package com.raro.web.repository.search;

import com.raro.web.domain.Urllink;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Urllink entity.
 */
public interface UrllinkSearchRepository extends ElasticsearchRepository<Urllink, Long> {
}
