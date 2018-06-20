package com.raro.web.repository.search;

import com.raro.web.domain.Party;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Party entity.
 */
public interface PartySearchRepository extends ElasticsearchRepository<Party, Long> {
}
