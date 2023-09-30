package com.elms.auth.mapper;

import com.elms.auth.dto.career.CareerDto;
import com.elms.auth.dto.career.CareerPublicDto;
import com.elms.auth.form.career.CreateCareerForm;
import com.elms.auth.form.career.UpdateCareerByCustomerForm;
import com.elms.auth.form.career.UpdateCareerForm;
import com.elms.auth.model.Career;
import com.elms.auth.model.DbConfig;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-29T11:37:32+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CareerMapperImpl implements CareerMapper {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public CareerDto fromCareerToDto(Career store) {
        if ( store == null ) {
            return null;
        }

        CareerDto careerDto = new CareerDto();

        careerDto.setSettings( store.getSettings() );
        careerDto.setAddress( store.getAddress() );
        careerDto.setHotline( store.getHotline() );
        careerDto.setLogoPath( store.getLogoPath() );
        careerDto.setLatitude( store.getLatitude() );
        careerDto.setBannerPath( store.getBannerPath() );
        careerDto.setAccountDto( accountMapper.fromAccountToDto( store.getAccount() ) );
        careerDto.setCareerPath( store.getCareerPath() );
        careerDto.setTenantId( storeDbConfigName( store ) );
        careerDto.setId( store.getId() );
        careerDto.setCareerName( store.getCareerName() );
        careerDto.setLang( store.getLang() );
        careerDto.setStatus( store.getStatus() );
        careerDto.setLongitude( store.getLongitude() );

        return careerDto;
    }

    @Override
    public CareerPublicDto fromCareerToPublicDto(Career store) {
        if ( store == null ) {
            return null;
        }

        CareerPublicDto careerPublicDto = new CareerPublicDto();

        careerPublicDto.setSettings( store.getSettings() );
        careerPublicDto.setAddress( store.getAddress() );
        careerPublicDto.setHotline( store.getHotline() );
        careerPublicDto.setLogoPath( store.getLogoPath() );
        careerPublicDto.setLatitude( store.getLatitude() );
        careerPublicDto.setBannerPath( store.getBannerPath() );
        careerPublicDto.setCareerPath( store.getCareerPath() );
        careerPublicDto.setId( store.getId() );
        careerPublicDto.setCareerName( store.getCareerName() );
        careerPublicDto.setLang( store.getLang() );
        careerPublicDto.setLongitude( store.getLongitude() );

        return careerPublicDto;
    }

    @Override
    public Career fromCreateFromToEntity(CreateCareerForm form) {
        if ( form == null ) {
            return null;
        }

        Career career = new Career();

        career.setSettings( form.getSettings() );
        career.setAddress( form.getAddress() );
        career.setHotline( form.getHotline() );
        career.setLogoPath( form.getLogoPath() );
        career.setLatitude( form.getLatitude() );
        career.setBannerPath( form.getBannerPath() );
        career.setCareerPath( form.getCareerPath() );
        career.setCareerName( form.getCareerName() );
        career.setLang( form.getLang() );
        if ( form.getStatus() != null ) {
            career.setStatus( form.getStatus() );
        }
        career.setLongitude( form.getLongitude() );

        return career;
    }

    @Override
    public void fromAdminUpdateFormToEntity(UpdateCareerForm updateStoreForm, Career store) {
        if ( updateStoreForm == null ) {
            return;
        }

        if ( updateStoreForm.getSettings() != null ) {
            store.setSettings( updateStoreForm.getSettings() );
        }
        if ( updateStoreForm.getAddress() != null ) {
            store.setAddress( updateStoreForm.getAddress() );
        }
        if ( updateStoreForm.getHotline() != null ) {
            store.setHotline( updateStoreForm.getHotline() );
        }
        if ( updateStoreForm.getLogoPath() != null ) {
            store.setLogoPath( updateStoreForm.getLogoPath() );
        }
        if ( updateStoreForm.getLatitude() != null ) {
            store.setLatitude( updateStoreForm.getLatitude() );
        }
        if ( updateStoreForm.getBannerPath() != null ) {
            store.setBannerPath( updateStoreForm.getBannerPath() );
        }
        if ( updateStoreForm.getCareerPath() != null ) {
            store.setCareerPath( updateStoreForm.getCareerPath() );
        }
        if ( updateStoreForm.getCareerName() != null ) {
            store.setCareerName( updateStoreForm.getCareerName() );
        }
        if ( updateStoreForm.getLang() != null ) {
            store.setLang( updateStoreForm.getLang() );
        }
        if ( updateStoreForm.getStatus() != null ) {
            store.setStatus( updateStoreForm.getStatus() );
        }
        if ( updateStoreForm.getLongitude() != null ) {
            store.setLongitude( updateStoreForm.getLongitude() );
        }
    }

    @Override
    public void fromCustomerUpdateFormToEntity(UpdateCareerByCustomerForm updateStoreForm, Career store) {
        if ( updateStoreForm == null ) {
            return;
        }

        if ( updateStoreForm.getSettings() != null ) {
            store.setSettings( updateStoreForm.getSettings() );
        }
        if ( updateStoreForm.getAddress() != null ) {
            store.setAddress( updateStoreForm.getAddress() );
        }
        if ( updateStoreForm.getHotline() != null ) {
            store.setHotline( updateStoreForm.getHotline() );
        }
        if ( updateStoreForm.getLogoPath() != null ) {
            store.setLogoPath( updateStoreForm.getLogoPath() );
        }
        if ( updateStoreForm.getLatitude() != null ) {
            store.setLatitude( updateStoreForm.getLatitude() );
        }
        if ( updateStoreForm.getBannerPath() != null ) {
            store.setBannerPath( updateStoreForm.getBannerPath() );
        }
        if ( updateStoreForm.getCareerName() != null ) {
            store.setCareerName( updateStoreForm.getCareerName() );
        }
        if ( updateStoreForm.getLongitude() != null ) {
            store.setLongitude( updateStoreForm.getLongitude() );
        }
    }

    @Override
    public List<CareerDto> fromEntityToCustomerDtoList(List<Career> list) {
        if ( list == null ) {
            return null;
        }

        List<CareerDto> list1 = new ArrayList<CareerDto>( list.size() );
        for ( Career career : list ) {
            list1.add( fromCareerToDto( career ) );
        }

        return list1;
    }

    private String storeDbConfigName(Career career) {
        if ( career == null ) {
            return null;
        }
        DbConfig dbConfig = career.getDbConfig();
        if ( dbConfig == null ) {
            return null;
        }
        String name = dbConfig.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
