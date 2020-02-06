package com.chatelain.outbreakprediction.forecast;

import lombok.Data;

@Data
public class PeopleStatistics {

    private Integer normal;

    private Integer shadow;

    private Integer confirmed;

    private Integer freeze;

    public PeopleStatistics(Integer normal, Integer shadow, Integer confirmed, Integer freeze) {
        this.normal = normal;
        this.shadow = shadow;
        this.confirmed = confirmed;
        this.freeze = freeze;
    }
}
