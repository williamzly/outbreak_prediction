package com.chatelain.outbreakprediction.forecast.params;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Params {

    @NotNull
    private Integer ORIGINAL_COUNT;
    @NotNull
    private Float BROAD_RATE;
    @NotNull
    private Float SHADOW_TIME;
    @NotNull
    private Integer HOSPITAL_RECEIVE_TIME;
    @NotNull
    private Integer BED_COUNT;
    @NotNull
    private Float u;
    @NotNull
    private Integer CITY_PERSON_SIZE;

}
