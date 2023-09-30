package com.elms.auth.mapper;

import com.elms.auth.dto.career.CareerDto;
import com.elms.auth.dto.career.CareerPublicDto;
import com.elms.auth.form.career.CreateCareerForm;
import com.elms.auth.form.career.UpdateCareerByCustomerForm;
import com.elms.auth.form.career.UpdateCareerForm;
import com.elms.auth.model.Career;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {AccountMapper.class})
public interface CareerMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "careerName", target = "careerName")
    @Mapping(source = "careerPath", target = "careerPath")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "logoPath", target = "logoPath")
    @Mapping(source = "bannerPath", target = "bannerPath")
    @Mapping(source = "hotline", target = "hotline")
    @Mapping(source = "settings", target = "settings")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "dbConfig.name", target = "tenantId")
    @Mapping(source = "account", target = "accountDto", qualifiedByName = "fromAccountToDto")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCareerToDto")
    CareerDto fromCareerToDto(Career store);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "careerName", target = "careerName")
    @Mapping(source = "careerPath", target = "careerPath")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "logoPath", target = "logoPath")
    @Mapping(source = "bannerPath", target = "bannerPath")
    @Mapping(source = "hotline", target = "hotline")
    @Mapping(source = "settings", target = "settings")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Named("fromCareerToPublicDto")
    @BeanMapping(ignoreByDefault = true)
    CareerPublicDto fromCareerToPublicDto(Career store);


    @Mapping(source = "careerName", target = "careerName")
    @Mapping(source = "careerPath", target = "careerPath")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "logoPath", target = "logoPath")
    @Mapping(source = "bannerPath", target = "bannerPath")
    @Mapping(source = "hotline", target = "hotline")
    @Mapping(source = "settings", target = "settings")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @BeanMapping(ignoreByDefault = true)
    Career fromCreateFromToEntity(CreateCareerForm form);


    @Mapping(source = "careerName", target = "careerName")
    @Mapping(source = "careerPath", target = "careerPath")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "logoPath", target = "logoPath")
    @Mapping(source = "bannerPath", target = "bannerPath")
    @Mapping(source = "hotline", target = "hotline")
    @Mapping(source = "settings", target = "settings")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @BeanMapping(ignoreByDefault = true)
    void fromAdminUpdateFormToEntity(UpdateCareerForm updateStoreForm, @MappingTarget Career store);


    @Mapping(source = "careerName", target = "careerName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "logoPath", target = "logoPath")
    @Mapping(source = "bannerPath", target = "bannerPath")
    @Mapping(source = "hotline", target = "hotline")
    @Mapping(source = "settings", target = "settings")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @BeanMapping(ignoreByDefault = true)
    void fromCustomerUpdateFormToEntity(UpdateCareerByCustomerForm updateStoreForm, @MappingTarget Career store);

    @IterableMapping(elementTargetType = CareerDto.class, qualifiedByName = "fromCareerToDto")
    List<CareerDto> fromEntityToCustomerDtoList(List<Career> list);
}
