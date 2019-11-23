package com.openpayd.rate.service;

import com.openpayd.rate.model.dto.ConversionResultModel;
import com.openpayd.rate.model.dto.RateResultModel;
import com.openpayd.rate.model.entity.Conversion;
import com.openpayd.rate.repository.ConversionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class RateService {

    @Autowired
    private RateClientService rateClientService;

    @Autowired
    ConversionRepository conversionRepository;

    public Double getExchangeRate(String source, String target) {
        RateResultModel rateModel = rateClientService.getRate(source, target);
        log.info("exchange rate  result by fixer service : " + rateModel.toString());
        return rateModel.getRates().get(target);
    }

    public Conversion getConversion(String source, String target, Double sourceAmount) {
        ConversionResultModel conversionResultModel = rateClientService.getConversion(source, target, sourceAmount);
        log.info("conversion result by fixer service : " + conversionResultModel.toString());
        Conversion conversion;
        if (conversionResultModel.isSuccess())
            conversion = new Conversion(conversionResultModel.getQuery().getFrom(), conversionResultModel.getQuery().getTo(),
                    conversionResultModel.getQuery().getAmount(), conversionResultModel.getResult(), LocalDate.now());
        else {
            Double exchangeRate = 6.3;
            //Double exchangeRate = getExchangeRate(source, target);
            conversion = new Conversion(source, target, sourceAmount, sourceAmount * exchangeRate, LocalDate.now());
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