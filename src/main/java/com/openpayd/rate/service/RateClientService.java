package com.openpayd.rate.service;

import com.openpayd.rate.configuration.FixerConfiguration;
import com.openpayd.rate.model.dto.ConversionResultModel;
import com.openpayd.rate.model.dto.RateResultModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RateClientService {

    private RestTemplate restTemplate;

    private FixerConfiguration fixerConfiguration;

    public RateClientService(RestTemplate restTemplate, FixerConfiguration fixerConfiguration) {
        this.restTemplate = restTemplate;
        this.fixerConfiguration = fixerConfiguration;
    }

    @Cacheable(value = "rate", key = "{#source, #target}")
    public RateResultModel getRate(String source, String target) {
        String url = fixerConfiguration.getUrl() + "latest" + "?access_key=" + fixerConfiguration.getApiKey() + "&base=" + source + "&symbols=" + target;
        RateResultModel rateModel = restTemplate.getForObject(url, RateResultModel.class);
        return rateModel;
    }


    @Cacheable(value = "rate", key = "{#source, #target, #sourceAmount}")
    public ConversionResultModel getConversion(String source, String target, Double sourceAmount) {
        String url = fixerConfiguration.getUrl() + "convert" + "?access_key=" + fixerConfiguration.getApiKey() + "&from=" + source + "&to=" + target + "&amount=" + sourceAmount;
        ConversionResultModel conversionResultModel = restTemplate.getForObject(url, ConversionResultModel.class);
        return conversionResultModel;
    }

}
