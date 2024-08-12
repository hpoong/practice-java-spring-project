package com.hopoong.elasticsearch.document;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@Document(indexName = "station")
//@Setting(settingPath = "/elastic/word/2-station-index-settings.json")
//@Mapping(mappingPath = "/elastic/word/2-station-index-mappings.json")
@Setting(settingPath = "/elastic/initial_consonant/1-station-index-settings.json")
@Mapping(mappingPath = "/elastic/initial_consonant/1-station-index-mappings.json")
public class StationDocument {


    @Id
    private String id;
    private String lineNum;         // 호선
    private String stationCd;       // 전철역코드
    private String stationNm;       // 전철역명
    private String frCode;          // 외부코드


    @Builder
    public StationDocument(String lineNum, String stationCd, String stationNm, String frCode) {
        this.lineNum = lineNum;
        this.stationCd = stationCd;
        this.stationNm = stationNm;
        this.frCode = frCode;
    }

}