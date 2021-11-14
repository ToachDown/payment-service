package com.example.bluecodepay.model.enums;

import lombok.Data;

public enum Source {
    POS("pos"),
    ECOMMERCE("ecommerce"),
    MCOMMERCE("mcommerce"),
    VENDING("vending");

    Source(String source) {
    }
}
