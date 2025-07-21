package com.example.session09.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/structured-log")
public class StructuredLogController {

    private static final Logger logger = LoggerFactory.getLogger(StructuredLogController.class);

    @GetMapping("/order")
    public String logOrderEvent() {
        String orderId = UUID.randomUUID().toString();

        logger.info("Đơn hàng mới đã được tạo: {}", orderId);

        return "Tạo đơn hàng thành công với ID: " + orderId;
    }
}