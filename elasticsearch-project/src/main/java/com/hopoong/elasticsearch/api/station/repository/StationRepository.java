package com.hopoong.elasticsearch.api.station.repository;

import com.hopoong.elasticsearch.document.StationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends ElasticsearchRepository<StationDocument, String> {
}