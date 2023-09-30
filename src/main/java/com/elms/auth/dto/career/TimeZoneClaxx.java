package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeZoneClaxx {

    @JsonProperty("name")
    private String name;
    @JsonProperty("offset")
    private String offset;

}