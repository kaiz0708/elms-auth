package com.elms.auth.dto.career;

import com.elms.auth.dto.ABasicAdminDto;
import com.elms.auth.dto.account.AccountDto;
import lombok.Data;

@Data
public class CareerDto extends ABasicAdminDto {
    private String careerName;
    private String careerPath;
    private String address;
    private String logoPath;
    private String bannerPath;
    private String hotline;
    private String settings;
    private String lang;
    private Double latitude;
    private Double longitude;
    private Boolean isReady = true;
    private Boolean isReadyQrLive = true;

//    private List<DeviceDto> deviceDtoList;
    private AccountDto accountDto;

    private String tenantId;
}
