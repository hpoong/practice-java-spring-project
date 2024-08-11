package com.hopoong.elasticsearch.document;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "station")
public class StationDocument {


    @Id
    private String id;
    private String lineNum;         // 호선
    private String stationCd;       // 전철역코드
    private String stationNm;       // 전철역명
    private String frCode;          // 외부코드

}