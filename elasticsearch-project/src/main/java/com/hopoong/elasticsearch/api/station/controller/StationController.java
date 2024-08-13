package com.hopoong.elasticsearch.api.station.controller;


import com.hopoong.elasticsearch.api.station.service.StationService;
import com.hopoong.elasticsearch.response.CommonResponseCodeEnum;
import com.hopoong.elasticsearch.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/station")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;


    /*
     * init 데이터 저장
     */
    @GetMapping("/save/station-info-index")
    public ResponseEntity<SuccessResponse> saveStationInfoIndexData() throws IOException {
        stationService.saveStationData();
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, "Data saved successfully"));
    }


    /*
     * station_info index 데이터 전체 삭제
     */
    @GetMapping("/delete/station-info-index")
    public ResponseEntity<SuccessResponse> deleteStationInfoIndexData() {
        stationService.deleteStationData();
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, "Data deleted successfully"));
    }


    /*
     * station_info index 데이터 전체 조회
     */
    @GetMapping("/select/station-info-index")
    public ResponseEntity<SuccessResponse> selectStationInfoIndexData() {
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, stationService.selectStationData()));
    }


    /*
     * station_info index 삭제
     */
    @GetMapping("/drop/station-info-index")
    public ResponseEntity<SuccessResponse> dropStationInfoIndex() {
        stationService.deleteStationInfoIndex("station");
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, "Index dropped successfully"));
    }


    /*
     * station_info index stationName 조회
     */
    @GetMapping("/station-info-index/search")
    public ResponseEntity<SuccessResponse> autocompleteStationName(@RequestParam(value = "stationName", required = true) String stationName) {
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, stationService.autocompleteStationName(stationName)));
    }

}
