package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Printer {
    private int number;// vị trí máy in từ 1 -> 10
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("group_name")
    private String groupName = "0";
    @JsonProperty("is_white")
    private Boolean isWhite = false;
    @JsonProperty("is_check")
    private Boolean isCheck = false;
    @JsonProperty("is_print_time")
    private Boolean isPrintTime = false;
    @JsonProperty("is_print_next_course")
    private Boolean isPrintNextCourse = false;
    @JsonProperty("is_beep")
    private Boolean isBeep = false;
    @JsonProperty("is_print_separator")
    private Boolean isPrintSeparator = false;
}