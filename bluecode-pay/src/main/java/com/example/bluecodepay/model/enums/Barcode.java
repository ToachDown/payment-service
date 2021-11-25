package com.example.bluecodepay.model.enums;

public enum Barcode {
    SUCCESS_ALWAYS("98802222100100123456"),
    SUCCESS_WITH_END_TO_END_ID("98800000000000000100"),
    SUCCESS_WITH_EXTRA_UNDOCUMENTED_ATTRIBUTES("98800000000000000101"),
    SUCCESS_WITH_ACQUIRER_TX_ID("98800000000000000099"),

    PROCESSING_DELAY_1("98802222999900301000"),
    PROCESSING_DELAY_5("98802222999900305000"),
    PROCESSING_DELAY_9("98802222999900309000"),
    PROCESSING_DELAY_8_TTL_5000("98802222999900308001"),

    ERROR_INVALID_BARCODE("invalid"),
    ERROR_INVALID_STATE("98804444000000402005"),
    ERROR_LIMIT_EXCEEDED("98804444000000402007"),
    ERROR_SYSTEM_FAILURE("98802222999900500500"),
    ERROR_AFTER_15_DELAY("98802222999900315000");

    private String barcode;

    Barcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }
}
