package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeviceMedia {
    @JsonProperty("device_id")
    private Long deviceId;
    @JsonProperty("device_token")
    private String deviceToken;
    @JsonProperty("media_code")
    private String mediaCode;
}
