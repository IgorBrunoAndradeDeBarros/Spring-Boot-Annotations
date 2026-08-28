package com.spring.boot.annotations.utils;

import org.springframework.stereotype.Component;

@Component
public class ParkingSpotFormatter {

    public String formatCode(String code) {
        return code.toUpperCase().trim();
    }
}