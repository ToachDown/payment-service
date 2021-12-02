package com.example.paymentbackendtemplate.helpers;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonTestHelper {

    private static final String PATH_REQUEST = "E:/payment/payment-backend-service/src/test/java/com/example/paymentbackendtemplate/json/request";

    private static final String PATH_RESPONSE = "E:/payment/payment-backend-service/src/test/java/com/example/paymentbackendtemplate/json/response";

    private static final JSONParser parser = new JSONParser();

    public static String getOkApprovedResponseJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_RESPONSE + "/ok-approved.json"));
        return obj.toJSONString();
    }

    public static String getOkDeclinedResponseJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_RESPONSE + "/ok-declined.json"));
        return obj.toJSONString();
    }

    public static String getOkFailureResponseJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_RESPONSE + "/ok-failure.json"));
        return obj.toJSONString();
    }

    public static String getProcessingResponseJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_RESPONSE + "/processing.json"));
        return obj.toJSONString();
    }

    public static String getErrorResponseJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_RESPONSE + "/error.json"));
        return obj.toJSONString();
    }


    public static String getStartRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/start-payment.json"));
        return obj.toJSONString();
    }

    public static String getUpdateRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/update-payment.json"));
        return obj.toJSONString();
    }

    public static String getCaptureRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/capture-payment.json"));
        return obj.toJSONString();
    }

    public static String getCancelRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/cancel-payment.json"));
        return obj.toJSONString();
    }

    public static String getRefundRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/refund-payment.json"));
        return obj.toJSONString();
    }

    public static String getStartBrokenRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/broken-start-payment.json"));
        return obj.toJSONString();
    }

    public static String getUpdateBrokenRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/broken-update-payment.json"));
        return obj.toJSONString();
    }

    public static String getCaptureBrokenRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/broken-capture-payment.json"));
        return obj.toJSONString();
    }

    public static String getCancelBrokenRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/broken-cancel-request.json"));
        return obj.toJSONString();
    }

    public static String getRefundBrokenRequestJson() throws ParseException, FileNotFoundException {
        JSONObject obj = (JSONObject) parser.parse(new FileReader(PATH_REQUEST + "/broken-refund-payment.json"));
        return obj.toJSONString();
    }

}
