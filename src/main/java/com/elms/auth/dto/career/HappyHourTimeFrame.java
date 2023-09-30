package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HappyHourTimeFrame {
    @JsonProperty("timeframe")
    public String timeframe;
    @JsonProperty("time_of_weeks")
    public List<Integer> timeOfWeeks;
    @JsonProperty("from")
    public TimePoint from;
    @JsonProperty("to")
    public TimePoint to;
    public Integer type;
}
