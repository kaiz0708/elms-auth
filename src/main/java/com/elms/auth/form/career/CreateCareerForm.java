package com.elms.auth.form.career;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class CreateCareerForm {

    @NotNull(message = "accountId cant not be null")
    @ApiModelProperty(name = "accountId", required = true)
    private Long  accountId;
    @NotEmpty(message = "careerName cant not be null")
    @ApiModelProperty(name = "careerName", required = true)
    private String careerName;
    @NotEmpty(message = "careerPath cant not be null")
    @ApiModelProperty(name = "careerPath", required = true)
    private String careerPath;

    @NotEmpty(message = "address cant not be null")
    @ApiModelProperty(name = "address", required = true)
    private String address;
    @NotEmpty(message = "logoPath cant not be null")
    @ApiModelProperty(name = "logoPath", required = true)
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
    @NotNull(message = "status cant not be null")
    @ApiModelProperty(name = "status", required = true)
    private Integer status;
    @ApiModelProperty(name = "latitude", required = false)
    private Double latitude;
    @ApiModelProperty(name = "longitude", required = false)
    private Double longitude;
}
