package com.elms.auth.mapper;

import com.elms.auth.dto.db.config.DbConfigDto;
import com.elms.auth.form.db.config.CreateDbConfigForm;
import com.elms.auth.form.db.config.UpdateDbConfigForm;
import com.elms.auth.model.DbConfig;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DbConfigMapper {
    @Mapping(source = "driverClassName", target = "driverClassName")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "initialize", target = "initialize")
    @Mapping(source = "careerId", target = "career.id")
    @Mapping(source = "maxConnection", target = "maxConnection")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    DbConfig fromCreateFormToEntity(CreateDbConfigForm createDbConfigForm);

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "driverClassName", target = "driverClassName")
//    @Mapping(source = "url", target = "url")
//    @Mapping(source = "username", target = "username")
//    @Mapping(source = "password", target = "password")
    @Mapping(source = "initialize", target = "initialize")
    @Mapping(source = "maxConnection", target = "maxConnection")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateMapping")
    void fromUpdateFormToEntity(UpdateDbConfigForm updateDbConfigForm, @MappingTarget DbConfig dbConfig);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "driverClassName", target = "driverClassName")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "initialize", target = "initialize")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminGetMapping")
    DbConfigDto fromEntityToDto(DbConfig dbConfig);

    @IterableMapping(elementTargetType = DbConfigDto.class, qualifiedByName = "adminGetMapping")
    List<DbConfigDto> fromEntityListToDtoList(List<DbConfig> dbConfigList);
}
