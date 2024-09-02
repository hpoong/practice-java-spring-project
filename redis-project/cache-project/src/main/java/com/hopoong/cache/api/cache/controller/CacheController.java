package com.hopoong.cache.api.cache.controller;

import com.hopoong.cache.api.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

}
