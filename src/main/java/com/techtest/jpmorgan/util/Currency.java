package com.techtest.jpmorgan.util;

import java.util.Arrays;
import java.util.List;

public enum Currency {
    SGP(Arrays.asList("Sun","Sat")), AED(Arrays.asList("Sat","Fri")), SAR(Arrays.asList("Sat","Fri"));

    private List<String> weekEndDays;

    public List<String> getWeekEndDays() {
        return weekEndDays;
    }
    public void setWeekEndDays(List<String> weekEndDays) {
        this.weekEndDays = weekEndDays;
    }
    Currency(List<String> weekEndDays) {
        this.weekEndDays = weekEndDays;
    }
}
