package com.hopoong.elasticsearch.api.station.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationInfoModel {
    private String lineNum;
    private String stationCd;
    private String stationNm;
    private String frCode;
}
