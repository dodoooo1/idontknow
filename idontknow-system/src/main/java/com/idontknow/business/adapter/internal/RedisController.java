package com.idontknow.business.adapter.internal;

import com.idontknow.business.jpa.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value, @RequestParam(required = false, defaultValue = "3600") long ttl) {
        redisService.setValue(key, value, ttl);
        return "Value stored successfully";
    }

    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        Object value = redisService.getValue(key);
        if (value != null) {
            return "Value: " + value.toString();
        } else {
            return "Key not found";
        }
    }

    @DeleteMapping("/delete")
    public String deleteValue(@RequestParam String key) {
        redisService.deleteValue(key);
        return "Key deleted successfully";
    }

    @GetMapping("/exists")
    public String exists(@RequestParam String key) {
        boolean exists = redisService.exists(key);
        return "Key exists: " + exists;
    }

    @PostMapping("/map/set")
    public String setMapValue(@RequestParam String mapName, @RequestParam String key, @RequestParam String value, @RequestParam(required = false, defaultValue = "3600") long ttl) {
        redisService.setMapValue(mapName, key, value, ttl);
        return "Map value stored successfully";
    }

    @GetMapping("/map/get")
    public String getMapValue(@RequestParam String mapName, @RequestParam String key) {
        Object value = redisService.getMapValue(mapName, key);
        if (value != null) {
            return "Map Value: " + value.toString();
        } else {
            return "Map Key not found";
        }
    }

    @DeleteMapping("/map/delete")
    public String deleteMapValue(@RequestParam String mapName, @RequestParam String key) {
        redisService.deleteMapValue(mapName, key);
        return "Map Key deleted successfully";
    }

    @GetMapping("/map/exists")
    public String mapKeyExists(@RequestParam String mapName, @RequestParam String key) {
        boolean exists = redisService.mapKeyExists(mapName, key);
        return "Map Key exists: " + exists;
    }
}



