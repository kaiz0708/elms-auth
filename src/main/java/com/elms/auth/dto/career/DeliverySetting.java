package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeliverySetting {

    @JsonProperty("map_key_direction")
    private String mapKey;
    @JsonProperty("region_google")
    private String regionGoogleSearch;

    @JsonProperty("distance")
    private List<DeliverDistanceSetting> distance;
}
