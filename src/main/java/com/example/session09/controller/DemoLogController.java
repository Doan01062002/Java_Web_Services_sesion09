package com.example.session09.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo-log")
public class DemoLogController {

    private static final Logger logger = LoggerFactory.getLogger(DemoLogController.class);

    @GetMapping("/trace")
    public String logTrace() {
        logger.trace("Đã ghi log trace");
        return "Log TRACE đã được ghi!";
    }

    @GetMapping("/debug")
    public String logDebug() {
        logger.debug("Đã ghi log debug");
        return "Log DEBUG đã được ghi!";
    }

    @GetMapping("/info")
    public String logInfo() {
        logger.info("Đã ghi log info");
        return "Log INFO đã được ghi!";
    }

    @GetMapping("/warning")
    public String logWarn() {
        logger.warn("Đã ghi log warning");
        return "Log WARN đã được ghi!";
    }

    @GetMapping("/error")
    public String logError() {
        logger.error("Đã ghi log error");
        return "Log ERROR đã được ghi!";
    }
}
