package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Media {
    public static final int MEDIA_KIND_IMAGE = 1;
    public static final int MEDIA_KIND_VIDEO = 2;

    public static final String MEDIA_CODE_1 = "MEDIA_CODE_1";
    public static final String MEDIA_CODE_2 = "MEDIA_CODE_2";
    public static final String MEDIA_CODE_3 = "MEDIA_CODE_3";
    public static final String MEDIA_CODE_4 = "MEDIA_CODE_4";
    public static final String MEDIA_CODE_5 = "MEDIA_CODE_5";

    @JsonProperty("media_kind")
    private Integer mediaKind;
    @JsonProperty("media_url")
    private String mediaUrl;
    @JsonProperty("media_out_url")
    private String mediaUrlOut;
    @JsonProperty("media_code")
    private String mediaCode;
}
