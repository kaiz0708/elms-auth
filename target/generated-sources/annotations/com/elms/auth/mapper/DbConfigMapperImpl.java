package com.elms.auth.mapper;

import com.elms.auth.dto.db.config.DbConfigDto;
import com.elms.auth.form.db.config.CreateDbConfigForm;
import com.elms.auth.form.db.config.UpdateDbConfigForm;
import com.elms.auth.model.Career;
import com.elms.auth.model.DbConfig;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-29T11:37:32+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class DbConfigMapperImpl implements DbConfigMapper {

    @Override
    public DbConfig fromCreateFormToEntity(CreateDbConfigForm createDbConfigForm) {
        if ( createDbConfigForm == null ) {
            return null;
        }

        DbConfig dbConfig = new DbConfig();

        dbConfig.setCareer( createDbConfigFormToCareer( createDbConfigForm ) );
        dbConfig.setMaxConnection( createDbConfigForm.getMaxConnection() );
        dbConfig.setPassword( createDbConfigForm.getPassword() );
        dbConfig.setDriverClassName( createDbConfigForm.getDriverClassName() );
        dbConfig.setInitialize( createDbConfigForm.isInitialize() );
        dbConfig.setUrl( createDbConfigForm.getUrl() );
        dbConfig.setUsername( createDbConfigForm.getUsername() );

        return dbConfig;
    }

    @Override
    public void fromUpdateFormToEntity(UpdateDbConfigForm updateDbConfigForm, DbConfig dbConfig) {
        if ( updateDbConfigForm == null ) {
            return;
        }

        if ( updateDbConfigForm.getMaxConnection() != null ) {
            dbConfig.setMaxConnection( updateDbConfigForm.getMaxConnection() );
        }
        dbConfig.setInitialize( updateDbConfigForm.isInitialize() );
    }

    @Override
    public DbConfigDto fromEntityToDto(DbConfig dbConfig) {
        if ( dbConfig == null ) {
            return null;
        }

        DbConfigDto dbConfigDto = new DbConfigDto();

        dbConfigDto.setPassword( dbConfig.getPassword() );
        dbConfigDto.setName( dbConfig.getName() );
        dbConfigDto.setDriverClassName( dbConfig.getDriverClassName() );
        dbConfigDto.setId( dbConfig.getId() );
        dbConfigDto.setInitialize( dbConfig.isInitialize() );
        dbConfigDto.setUrl( dbConfig.getUrl() );
        dbConfigDto.setUsername( dbConfig.getUsername() );

        return dbConfigDto;
    }

    @Override
    public List<DbConfigDto> fromEntityListToDtoList(List<DbConfig> dbConfigList) {
        if ( dbConfigList == null ) {
            return null;
        }

        List<DbConfigDto> list = new ArrayList<DbConfigDto>( dbConfigList.size() );
        for ( DbConfig dbConfig : dbConfigList ) {
            list.add( fromEntityToDto( dbConfig ) );
        }

        return list;
    }

    protected Career createDbConfigFormToCareer(CreateDbConfigForm createDbConfigForm) {
        if ( createDbConfigForm == null ) {
            return null;
        }

        Career career = new Career();

        career.setId( createDbConfigForm.getCareerId() );

        return career;
    }
}
