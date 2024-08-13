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
@Document(indexName = "station_info")
@Setting(settingPath = "/elastic/index_set/1-station-info-index-settings.json")
@Mapping(mappingPath = "/elastic/index_set/1-station-info-index-mappings.json")
public class StationInfoDocument {

    @Id
    private String id;
    private String lineNum;         // 호선
    private String stationCd;       // 전철역코드
    private String stationNm;       // 전철역명
    private String frCode;          // 외부코드


    @Builder
    public StationInfoDocument(String lineNum, String stationCd, String stationNm, String frCode) {
        this.lineNum = lineNum;
        this.stationCd = stationCd;
        this.stationNm = stationNm;
        this.frCode = frCode;
    }

}