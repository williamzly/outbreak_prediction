package com.chatelain.outbreakprediction.controller;

import com.chatelain.outbreakprediction.forecast.OutBreakCalculator;
import com.chatelain.outbreakprediction.forecast.Person;
import com.chatelain.outbreakprediction.forecast.PersonPool;
import com.chatelain.outbreakprediction.forecast.params.Params;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

@RestController
public class ForecastController {


    @GetMapping("/forecast")
    public Object forecast(@Valid Params params) throws ParseException {
        OutBreakCalculator calculator = new OutBreakCalculator(params);
        calculator.initInfected();
        calculator.calculate();
        return calculator.result;
    }



}
