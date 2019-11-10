package com.techtest.jpmorgan.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.techtest.jpmorgan.report.FinalReport;
import com.techtest.jpmorgan.util.Currency;
import com.techtest.jpmorgan.util.TradeEntity;
import com.techtest.jpmorgan.util.Flag;

/**
 * This class is used for to load data from dataset.
 */
public class DataLoader {
    /**
     * Load data from dataSet.csv
     */
    public List<TradeEntity> loadData() {

        boolean skipFirstRow = false;
        List<TradeEntity> dataList = new ArrayList<>();
        InputStream inputStream = FinalReport.class.getClassLoader().getResourceAsStream("dataSet.csv");
        if (null != inputStream) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line = br.readLine();
                while (line != null) {
                    if (!skipFirstRow) {
                        skipFirstRow = true;
                        line = br.readLine();
                        continue;
                    }
                    String[] attributes = line.split(",");
                    TradeEntity data = new TradeEntity();
                    data.setEntity(attributes[0]);
                    data.setFlag(Flag.valueOf(attributes[1]));
                    data.setAgreedFx(new BigDecimal(attributes[2]));
                    data.setCurrency(Currency.valueOf(attributes[3]));
                    data.setInstructionDate(DateHelper.FORMATTER.parse(attributes[4]));
                    data.setSettlementDate(DateHelper.FORMATTER.parse(attributes[5]));
                    data.setUnits(Integer.parseInt(attributes[6]));
                    data.setPricePerUnit(new BigDecimal(attributes[7]));
                    dataList.add(data);
                    line = br.readLine();
                }

            } catch (IOException | ParseException ioe) {
                ioe.printStackTrace();
            }
        }
        return dataList;

    }

}
