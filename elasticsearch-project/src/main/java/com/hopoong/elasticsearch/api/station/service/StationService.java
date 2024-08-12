package com.hopoong.elasticsearch.api.station.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopoong.elasticsearch.api.station.model.StationInfoModel;
import com.hopoong.elasticsearch.api.station.repository.StationRepository;
import com.hopoong.elasticsearch.document.StationDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final ElasticsearchOperations elasticsearchOperations;


    @Transactional
    public void saveStationData() throws IOException {
        ClassPathResource resource = new ClassPathResource("elastic/data_set/seoul_station_info.json");
        InputStream inputStream = resource.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON → JAVA Object
        List<StationInfoModel> stationInfoList = objectMapper.readValue(inputStream, new TypeReference<List<StationInfoModel>>() {});

        List<StationDocument> stationDocument = stationInfoList.stream().map(data ->
            StationDocument.builder()
                .lineNum(data.getLineNum())
                .stationCd(data.getStationCd())
                .stationNm(data.getStationNm())
                .frCode(data.getFrCode())
                .build()
        ).collect(Collectors.toList());

        stationRepository.saveAll(stationDocument);
    }

    @Transactional
    public void deleteStationData() {
        stationRepository.deleteAll();
    }


    @Transactional(readOnly = true)
    public List<StationInfoModel> selectStationData() {
        return StreamSupport.stream(stationRepository.findAll().spliterator(), false)
                .map(data -> new StationInfoModel(data.getLineNum(), data.getStationCd(), data.getStationNm(), data.getFrCode()))
                .collect(Collectors.toList());
    }


    @Transactional
    public void deleteStationInfoIndex(String indexName) {
        boolean deleted = elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).delete();
        System.out.println(deleted ? "Index deleted successfully" : "Failed to delete index");
    }


    public List<StationInfoModel> autocompleteStationName(String type, String input) {

        if("word".equals(type)) {
            Criteria criteria = Criteria.where("stationNm").startsWith(input);
            CriteriaQuery query = new CriteriaQuery(criteria);

            SearchHits<StationDocument> searchHits = elasticsearchOperations.search(query, StationDocument.class);

            return searchHits.map(hit -> hit.getContent()).toList().stream().map(
                    data -> new StationInfoModel(data.getLineNum(), data.getStationCd(), data.getStationNm(), data.getFrCode())
            ).collect(Collectors.toList());
            // return searchHits.map(hit -> hit.getContent()).toList();
        }

        else {

//            String initialConsonants = extractInitialConsonants(input);
//            Criteria criteria = Criteria.where("stationNm").matches(initialConsonants);
//            CriteriaQuery query = new CriteriaQuery(criteria);
//            SearchHits<StationDocument> searchHits = elasticsearchOperations.search(query, StationDocument.class);
//            return searchHits.map(hit -> hit.getContent()).toList().stream().map(
//                    data -> new StationInfoModel(data.getLineNum(), data.getStationCd(), data.getStationNm(), data.getFrCode())
//            ).collect(Collectors.toList());


//            Criteria criteria = Criteria.where("stationNm").matches(input);
//            CriteriaQuery query = new CriteriaQuery(criteria);
//            SearchHits<StationDocument> searchHits = elasticsearchOperations.search(query, StationDocument.class);
//            return searchHits.map(hit -> hit.getContent()).toList().stream().map(
//                    data -> new StationInfoModel(data.getLineNum(), data.getStationCd(), data.getStationNm(), data.getFrCode())
//            ).collect(Collectors.toList());
            return null;
        }
    }

    public String extractInitialConsonants(String input) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (ch >= 0xAC00 && ch <= 0xD7A3) {
                int base = ch - 0xAC00;
                int cho = (base / (21 * 28));
                result.append((char) (0x1100 + cho));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }




}
