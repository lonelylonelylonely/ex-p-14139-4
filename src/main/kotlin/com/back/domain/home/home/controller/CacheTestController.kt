package com.back.domain.home.home.controller

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class CacheTestController(
    private val cacheTestService: CacheTestService,
) {
    @GetMapping("/cache-test/{key}")
    fun cacheTest(@PathVariable key: String): Map<String, String> {
        val result = cacheTestService.getCachedValue(key)
        return mapOf("key" to key, "value" to result)
    }
}

@Service
class CacheTestService {
    @Cacheable(cacheNames = ["test"], key = "#key")
    fun getCachedValue(key: String): String {
        return "generated-at-${LocalDateTime.now()}"
    }
}
