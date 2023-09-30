package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TabletRandomSetting {
    @JsonProperty("time_work")
    private TimeWork timeWork;
    @JsonProperty("skip_method")
    private Boolean skipMethod = false;

    @JsonProperty("medias")
    private List<Media> medias;

    @JsonProperty("device_medias")
    private List<DeviceMedia> deviceMedia;
}