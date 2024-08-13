package com.hopoong.elasticsearch.api.station.repository;

import com.hopoong.elasticsearch.document.StationMultilingualDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface StationMultilingualRepository extends ElasticsearchRepository<StationMultilingualDocument, String> {
}