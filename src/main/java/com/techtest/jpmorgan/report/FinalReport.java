package com.techtest.jpmorgan.report;

import com.techtest.jpmorgan.helper.DataLoader;
import com.techtest.jpmorgan.helper.DateHelper;
import com.techtest.jpmorgan.util.Flag;
import com.techtest.jpmorgan.util.TradeEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The report result.
 */
public class FinalReport {

    private static final String TABLE_FORMAT = "|%1$-8s|%2$-8s|%3$-8s|%4$-8s|%5$-15s|%6$-18s|%7$-18s|%8$-6s|%9$-15s|%10$-14s|%11$-10s|%12$-18s|\n";

    public static void main(String[] args)  {
        List<TradeEntity> dataList = new DataLoader().loadData();
        updateSettlementDateAmount(dataList);
        updateRank(dataList, Flag.B);
        updateRank(dataList, Flag.S);
        // Result
        System.out.format(TABLE_FORMAT, "Entity", "Buy/Sell", "AgreedFx", "Currency", "InstructionDate", "Previous Settlement", "New Settlement", "Units", "Price per unit", "Amount[USD]", "In/Out", "Rank");
        for (TradeEntity data : dataList) {
            System.out.format(TABLE_FORMAT, data.getEntity(), data.getFlag(), data.getAgreedFx(), data.getCurrency(), DateHelper.FORMATTER.format(data.getInstructionDate()), DateHelper.FORMATTER.format(data.getSettlementDate()),
                    data.getNewSettlementDate() != null ? DateHelper.FORMATTER.format(data.getNewSettlementDate()) : "-NA-", data.getUnits(), data.getPricePerUnit(), data.getUsdAmout(), data.getFlag().getTypeDesc(), data.getRank());
        }
    }

    /**
     * Update settlement date if it falls in weekend and calculate USD amount from Units, PricePerUnit & AgreedFx.
     * @param dataList Trade data
     */
    private static void updateSettlementDateAmount(List<TradeEntity> dataList) {
        for (TradeEntity data : dataList) {
            String day = DateHelper.DAY_START.format(data.getSettlementDate());
            // Check if the settlement date is weekend.
            if (data.getCurrency().getWeekEndDays().contains(day)) {
                data.setNewSettlementDate(DateHelper.getNextWorkingDay(data.getSettlementDate(), data.getCurrency()));            }
                // USD amount  = Price per unit * Units * Agreed Fx
                BigDecimal usdAmount = data.getPricePerUnit().multiply(BigDecimal.valueOf(data.getUnits())).multiply(data.getAgreedFx());
                data.setUsdAmout(usdAmount);
        }
    }

    /**
     * Rank USD amount only for data with given flag filter.
     * @param dataList
     * @param flag
     */
    private static void updateRank(List<TradeEntity> dataList, Flag flag) {
        int rank = 1;
        List<TradeEntity> updatedList = dataList.stream().filter(d -> flag.equals(d.getFlag())).sorted(Comparator.comparing(TradeEntity::getUsdAmout).reversed()).collect(Collectors.toCollection(ArrayList::new));
        for (TradeEntity data : updatedList) {
            data.setRank(data.getFlag().getTypeDesc() + " Rank " + rank++);
        }
    }
}
