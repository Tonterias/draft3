package com.raro.web.repository.search;

import com.raro.web.domain.Blockeduser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Blockeduser entity.
 */
public interface BlockeduserSearchRepository extends ElasticsearchRepository<Blockeduser, Long> {
}
