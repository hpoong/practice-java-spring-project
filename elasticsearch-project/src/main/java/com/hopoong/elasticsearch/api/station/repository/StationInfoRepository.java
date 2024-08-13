package com.hopoong.elasticsearch.api.station.repository;

import com.hopoong.elasticsearch.document.StationInfoDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationInfoRepository extends ElasticsearchRepository<StationInfoDocument, String> {
}