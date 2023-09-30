package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimePoint {
    @JsonProperty("hour")
    public String hour;
    @JsonProperty("minute")
    public String minute;

}
