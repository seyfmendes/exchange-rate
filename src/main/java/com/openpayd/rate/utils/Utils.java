package com.openpayd.rate.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static double multiply(Double exchangeRate, Double sourceAmount) {
        BigDecimal convertedExchangeRate = new BigDecimal(exchangeRate);
        BigDecimal convertedSourceAmount = new BigDecimal(sourceAmount);
        return convertedExchangeRate.multiply(convertedSourceAmount).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }
}

