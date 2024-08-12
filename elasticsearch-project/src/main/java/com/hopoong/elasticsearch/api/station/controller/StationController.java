package com.hopoong.elasticsearch.api.station.controller;


import com.hopoong.elasticsearch.api.station.model.StationInfoModel;
import com.hopoong.elasticsearch.api.station.service.StationService;
import com.hopoong.elasticsearch.document.StationDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/station")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;


    @GetMapping("/save/station-index-all")
    public void saveStationData() throws IOException {
        stationService.saveStationData();
    }

    @GetMapping("/delete/station-index-all")
    public void deleteStationData() {
        stationService.deleteStationData();
    }

    @GetMapping("/select/station-index-all")
    public List<StationInfoModel> selectStationData() {
        return stationService.selectStationData();
    }

    @GetMapping("/delete/station-index")
    public void deleteStationInfoIndex() {
        stationService.deleteStationInfoIndex("station");
    }

    @GetMapping("/search/station-index/stationName")
    public List<StationInfoModel> autocompleteStationName(
            @RequestParam("type") String type,
            @RequestParam("input") String input) {
        return stationService.autocompleteStationName(type, input);
    }

}
