package com.hopoong.elasticsearch.document;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;



//@Data
//@NoArgsConstructor
//@Document(indexName = "station_multilingual")
//@Setting(settingPath = "/elastic/index_set/station-multilingual-index-settings.json")
//@Mapping(mappingPath = "/elastic/index_set/station-multilingual-index-mappings.json")
public class StationMultilingualDocument {

    @Id
    private String id;
    private String stationNmCn; // 중국어 역 이름
    private String stationNmJp; // 일본어 역 이름
    private String stationNmEn; // 영어 역 이름
    private String lineNum;     // 호선
    private String stationCd;   // 전철역코드
    private String frCode;      // 외부코드


    @Builder
    public StationMultilingualDocument(String stationNmCn, String stationNmJp, String stationNmEn, String lineNum, String stationCd, String frCode) {
        this.stationNmCn = stationNmCn;
        this.stationNmJp = stationNmJp;
        this.stationNmEn = stationNmEn;
        this.lineNum = lineNum;
        this.stationCd = stationCd;
        this.frCode = frCode;
    }
}