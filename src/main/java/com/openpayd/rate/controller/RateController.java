package com.openpayd.rate.controller;

import com.openpayd.rate.model.entity.Conversion;
import com.openpayd.rate.service.RateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Api(value = "Exchange Rate", description = "get  exchange rate")
public class RateController {

    @Autowired
    RateService rateService;


    @ApiOperation(value = "get exchange rate from source currency to target currency")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It is successful"),
            @ApiResponse(code = 404, message = "exchange rate not found")
    })
    @RequestMapping(value = "exchange-rate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Double getExchangeRate(@ApiParam(value = "source currency", required = true) @RequestParam("source") String source,
                                  @ApiParam(value = "target currency", required = true) @RequestParam("target") String target) {
        return rateService.getExchangeRate(source, target);
    }

    @ApiOperation(value = "get conversion ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It is successful"),
            @ApiResponse(code = 404, message = "conversion not found")
    })
    @RequestMapping(value = "conversion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Conversion getConversion(@ApiParam(value = "source currency", required = true) @RequestParam("source") String source,
                                    @ApiParam(value = "target currency", required = true) @RequestParam("target") String target,
                                    @ApiParam(value = "source amount", required = true) @RequestParam("amount") Double sourceAmount) {
        return rateService.getConversion(source, target, sourceAmount);
    }

    @ApiOperation(value = "get conversion list By Transaction Id or Transaction Date ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It is successful"),
            @ApiResponse(code = 404, message = "conversion list not found")
    })
    @RequestMapping(value = "conversion-list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Conversion> getConversions(@ApiParam(value = "transaction id") @RequestParam(value = "transactionId", required = false) Long transactionId,
                                           @ApiParam(value = "transaction date") @RequestParam(value = "transactionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate) {
        return rateService.getConversions(transactionId, transactionDate);
    }
}
