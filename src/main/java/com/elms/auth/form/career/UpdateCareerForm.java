package com.elms.auth.form.career;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel
public class UpdateCareerForm {
    @NotNull(message = "id cant not be null")
    @ApiModelProperty(name = "id", required = true)
    private Long  id;
    @NotEmpty(message = "careerName cant not be null")
    @ApiModelProperty(name = "careerName", required = true)
    private String careerName;
    private String careerPath;
    @ApiModelProperty(name = "address")
    private String address;
    @ApiModelProperty(name = "logoPath")
    private String logoPath;
    @ApiModelProperty(name = "bannerPath")
    private String bannerPath;
    @ApiModelProperty(name = "hotline")
    private String hotline;
    @ApiModelProperty(name = "settings")
    private String settings="{}";
    

    @NotEmpty(message = "lang cant not be null")
    @ApiModelProperty(name = "lang", required = true)
    private String lang;
    @NotNull(message = "expiredDate cant not be null")
    @NotNull(message = "status cant not be null")
    @ApiModelProperty(name = "status", required = true)
    private Integer status;

    @ApiModelProperty(name = "latitude", required = false)
    private Double latitude;

    @ApiModelProperty(name = "longitude", required = false)
    private Double longitude;
}
