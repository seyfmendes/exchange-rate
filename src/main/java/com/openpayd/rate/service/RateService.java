package com.openpayd.rate.service;

import com.openpayd.rate.enums.RateError;
import com.openpayd.rate.exception.RateException;
import com.openpayd.rate.model.dto.ConversionResultModel;
import com.openpayd.rate.model.dto.RateResultModel;
import com.openpayd.rate.model.entity.Conversion;
import com.openpayd.rate.repository.ConversionRepository;
import com.openpayd.rate.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RateService {

    private static Logger logger = LoggerFactory.getLogger(RateService.class);

    private RateClientService rateClientService;

    private ConversionRepository conversionRepository;

    public RateService(RateClientService rateClientService, ConversionRepository conversionRepository) {
        this.rateClientService = rateClientService;
        this.conversionRepository = conversionRepository;
    }

    public Double getExchangeRate(String source, String target) {
        RateResultModel rateModel = rateClientService.getRate(source, target);
        logger.info("exchange rate  result by fixer service : " + rateModel.toString());
        if (!rateModel.isSuccess()) {
            throw new RateException(RateError.INVALID_CURRENCY_CODE);
        }
        return rateModel.getRates().get(target);
    }

    public Conversion getConversion(String source, String target, Double sourceAmount) {
        ConversionResultModel conversionResultModel = rateClientService.getConversion(source, target, sourceAmount);
        logger.info("conversion result by fixer service : " + conversionResultModel.toString());
        Conversion conversion = calculateConversion(conversionResultModel, source, target, sourceAmount);
        return conversion;
    }

    public Conversion calculateConversion(ConversionResultModel conversionResultModel, String source, String target, Double sourceAmount) {
        Conversion conversion;
        if (conversionResultModel.isSuccess())
            conversion = new Conversion(conversionResultModel.getQuery().getFrom(), conversionResultModel.getQuery().getTo(),
                    conversionResultModel.getQuery().getAmount(), conversionResultModel.getResult(), LocalDate.now());
        else {
            Double exchangeRate = getExchangeRate(source, target);
            double targetAmount = Utils.multiply(exchangeRate, sourceAmount);
            conversion = new Conversion(source, target, sourceAmount, targetAmount, LocalDate.now());
        }
        conversion = saveConversion(conversion);
        return conversion;
    }


    private Conversion saveConversion(Conversion conversion) {
        conversion = conversionRepository.save(conversion);
        return conversion;
    }

    public List<Conversion> getConversions(Long transactionId, LocalDate transactionDate) {
        List<Conversion> conversions = conversionRepository.findByIdOrDate(transactionId, transactionDate);
        return conversions;
    }


}
