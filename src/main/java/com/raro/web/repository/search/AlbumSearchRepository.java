package com.raro.web.repository.search;

import com.raro.web.domain.Album;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Album entity.
 */
public interface AlbumSearchRepository extends ElasticsearchRepository<Album, Long> {
}
