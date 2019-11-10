package com.techtest.jpmorgan.util;

public enum Flag {

    B("Buy", "Outgoing"), S("Sell", "Incoming");
    private String type;
    private String typeDesc;
    Flag(String type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }
    public String getType() {
        return type;
    }
    public String getTypeDesc() {
        return typeDesc;
    }
}
