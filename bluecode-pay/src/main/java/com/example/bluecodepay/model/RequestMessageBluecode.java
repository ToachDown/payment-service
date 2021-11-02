package com.example.bluecodepay.model;

import template.model.RequestMessage;

public class RequestMessageBluecode extends RequestMessage {

    private String scheme;
    private String branchExtId;
    private String merchantExtId;
    private String barcode;
    private int requestAmount;
    private int purchaseAmount;
    private int discountAmount;
    private int tipAmount;
    private String currency;
    private String slip;
    private String slipDateTime;
    private String source;
    private String operator;
    private String terminal;
    private String entryMode;
    private String endToEnd;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getBranchExtId() {
        return branchExtId;
    }

    public void setBranchExtId(String branchExtId) {
        this.branchExtId = branchExtId;
    }

    public String getMerchantExtId() {
        return merchantExtId;
    }

    public void setMerchantExtId(String merchantExtId) {
        this.merchantExtId = merchantExtId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(int tipAmount) {
        this.tipAmount = tipAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSlip() {
        return slip;
    }

    public void setSlip(String slip) {
        this.slip = slip;
    }

    public String getSlipDateTime() {
        return slipDateTime;
    }

    public void setSlipDateTime(String slipDateTime) {
        this.slipDateTime = slipDateTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getEntryMode() {
        return entryMode;
    }

    public void setEntryMode(String entryMode) {
        this.entryMode = entryMode;
    }

    public String getEndToEnd() {
        return endToEnd;
    }

    public void setEndToEnd(String endToEnd) {
        this.endToEnd = endToEnd;
    }

    public RequestMessageBluecode(String type) {
        super(type);
    }
}
