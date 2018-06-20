package com.raro.web.repository.search;

import com.raro.web.domain.Proposal;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Proposal entity.
 */
public interface ProposalSearchRepository extends ElasticsearchRepository<Proposal, Long> {
}
