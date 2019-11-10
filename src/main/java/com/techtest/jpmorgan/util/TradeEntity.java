package com.techtest.jpmorgan.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class is used for trade entity data.
 */
public class TradeEntity {
    private String entity;
    private Date instructionDate;
    private Date settlementDate;
    private Date newSettlementDate;
    private Flag flag;
    private BigDecimal agreedFx;
    private int units;
    private Currency currency;
    private BigDecimal pricePerUnit;
    private BigDecimal usdAmout;
    private String rank;

    public String getEntity() {
        return entity;
    }
    public void setEntity(String entity) {
        this.entity = entity;
    }
    public Date getInstructionDate() {
        return instructionDate;
    }
    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }
    public Date getSettlementDate() {
        return settlementDate;
    }
    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Date getNewSettlementDate() {
        return newSettlementDate;
    }

    public void setNewSettlementDate(Date newSettlementDate) {
        this.newSettlementDate = newSettlementDate;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(BigDecimal agreedFx) {
        this.agreedFx = agreedFx;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getUsdAmout() {
        return usdAmout;
    }

    public void setUsdAmout(BigDecimal usdAmout) {
        this.usdAmout = usdAmout;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
