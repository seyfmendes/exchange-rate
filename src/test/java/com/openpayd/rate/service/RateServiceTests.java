package com.openpayd.rate.service;


import com.openpayd.rate.exception.RateException;
import com.openpayd.rate.model.dto.ConversionResultModel;
import com.openpayd.rate.model.dto.RateResultModel;
import com.openpayd.rate.model.entity.Conversion;
import com.openpayd.rate.repository.ConversionRepository;
import com.openpayd.rate.utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateServiceTests {

    private static final String SOURCE_CURRENCY = "EUR";
    private static final String TARGET_CURRENCY = "TRY";
    private static final String WRONG_TARGET_CURRENCY = "RUE";
    private static final Double SOURCE_AMOUNT = 20.0;

    private RateResultModel rateResultModel;
    private ConversionResultModel conversionResultModel;

    @Autowired
    private RateService rateService;

    @Autowired
    private RateClientService rateClientService;

    @Autowired
    private ConversionRepository conversionRepository;

    @Before
    public void init() {
        rateResultModel = rateClientService.getRate(SOURCE_CURRENCY, TARGET_CURRENCY);
        conversionResultModel = rateClientService.getConversion(SOURCE_CURRENCY, TARGET_CURRENCY, SOURCE_AMOUNT);
    }


    @Test
    public void Should_CheckExchangeRate_When_EnterCurrencies() {
        Double exchangeRate = rateService.getExchangeRate(SOURCE_CURRENCY, TARGET_CURRENCY);
        assertThat(exchangeRate.doubleValue()).isEqualTo(rateResultModel.getRates().get(TARGET_CURRENCY).doubleValue());
    }

    @Test
    public void Should_Conversion_When_EnterCurrencies() {
        double actualTargetAmount = rateService.calculateConversion(conversionResultModel, SOURCE_CURRENCY,
                TARGET_CURRENCY, SOURCE_AMOUNT).getTargetAmount();
        double expectedTargetAmount = Utils.multiply(rateResultModel.getRates().get(TARGET_CURRENCY), SOURCE_AMOUNT);
        assertThat(actualTargetAmount).isEqualTo(expectedTargetAmount);
    }

    @Test
    public void Should_Conversions_When_EnterTransactionIdOrTransactionDate() {
        Conversion conversion = conversionRepository.save(new Conversion(SOURCE_CURRENCY, TARGET_CURRENCY, SOURCE_AMOUNT, 120.0, LocalDate.now()));
        List<Conversion> conversions = rateService.getConversions(conversion.getTransactionId(), LocalDate.now());
        assertThat(conversions).isNotEmpty();
    }

    @Test(expected = RateException.class)
    public void Should_ThrowsError_When_CurrencyNotCorrect() {
        rateService.getExchangeRate(SOURCE_CURRENCY, WRONG_TARGET_CURRENCY);
    }

}
