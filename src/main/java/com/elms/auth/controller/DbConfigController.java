package com.elms.auth.controller;


import com.elms.auth.constant.ElmsConstant;
import com.elms.auth.dto.ApiMessageDto;
import com.elms.auth.dto.ErrorCode;
import com.elms.auth.dto.db.config.DbConfigDto;
import com.elms.auth.form.db.config.CreateDbConfigForm;
import com.elms.auth.form.db.config.UpdateDbConfigForm;
import com.elms.auth.mapper.DbConfigMapper;
import com.elms.auth.model.Career;
import com.elms.auth.model.DbConfig;
import com.elms.auth.repository.CareerRepository;
import com.elms.auth.repository.DbConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/v1/db-config")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class DbConfigController extends ABasicController{
    @Autowired
    DbConfigRepository dbConfigRepository;

    @Autowired
    CareerRepository careerRepository;

    @Autowired
    DbConfigMapper dbConfigMapper;

    @Autowired
    DataSource dataSource;

    @GetMapping(value = "/get/{careerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DB_V_ST')")
    public ApiMessageDto<DbConfigDto> get(@PathVariable("careerId") Long careerId) {
        ApiMessageDto<DbConfigDto> result = new ApiMessageDto<>();
        if(!isSuperAdmin()){
            result.setResult(false);
            result.setCode(ErrorCode.DB_CONFIG_ERROR_UNAUTHORIZED);
            return result;
        }
        DbConfig dbConfig = dbConfigRepository.findByCareerId(careerId);
        if(dbConfig == null) {
            result.setResult(false);
            result.setCode(ErrorCode.DB_CONFIG_ERROR_NOT_FOUND);
            return result;
        }
        result.setData(dbConfigMapper.fromEntityToDto(dbConfig));
        result.setMessage("Get db config success");
        return result;
    }

    @GetMapping(value = "/get_by_name", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DB_V_NAME')")
    public ApiMessageDto<DbConfigDto> get(@RequestParam("name") String name) {
        ApiMessageDto<DbConfigDto> result = new ApiMessageDto<>();
        DbConfig dbConfig = dbConfigRepository.findByName(name);
        if(dbConfig == null) {
            result.setResult(false);
            result.setCode(ErrorCode.DB_CONFIG_ERROR_NOT_FOUND);
            return result;
        }
        result.setData(dbConfigMapper.fromEntityToDto(dbConfig));
        result.setMessage("Get db config success");
        return result;
    }

    private String parseDatabaseNameFromConnectionString(String url) {
        String cleanString = url.substring("jdbc:mysql://".length(), url.indexOf("?"));
        return cleanString.substring(cleanString.indexOf("/") + 1);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DB_C')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateDbConfigForm createDbConfigForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        if(!isSuperAdmin()){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.DB_CONFIG_ERROR_UNAUTHORIZED);
            return apiMessageDto;
        }
        DbConfig dbConfig = dbConfigRepository.findByCareerId(createDbConfigForm.getCareerId());
        if(dbConfig != null){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.DB_CONFIG_SHOP_EXISTED);
            return apiMessageDto;
        }
        Career store = careerRepository.findById(createDbConfigForm.getCareerId()).orElse(null);
        if (store == null || store.getStatus() != ElmsConstant.STATUS_ACTIVE) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.STORE_ERROR_NOT_FOUND);
            return apiMessageDto;
        }

        dbConfig = dbConfigMapper.fromCreateFormToEntity(createDbConfigForm);
        dbConfig.setName("tenant_id_" + store.getId()); //tenant_id_[id nha hang]

        String databaseName = parseDatabaseNameFromConnectionString(dbConfig.getUrl());
        // Open a connection
        try(
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("CREATE DATABASE " + databaseName  + " CHARACTER SET utf8;");
            statement.execute("CREATE USER '" + dbConfig.getUsername() + "' IDENTIFIED BY '" + dbConfig.getPassword() + "';");
            statement.execute("CREATE USER '" + dbConfig.getUsername() + "'@'localhost' IDENTIFIED BY '" + dbConfig.getPassword() + "';");
            statement.execute("GRANT ALL PRIVILEGES ON " + databaseName +".* TO '" + dbConfig.getUsername() + "';");
            statement.execute("FLUSH PRIVILEGES;");

            System.out.println("Tenant database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.DB_CONFIG_ERROR_CANNOT_CREATE_DB);
            return apiMessageDto;
        }

        dbConfigRepository.save(dbConfig);

        apiMessageDto.setMessage("Create db config success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DB_U')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateDbConfigForm updateDbConfigForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        if(!isSuperAdmin()){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.DB_CONFIG_ERROR_UNAUTHORIZED);
            return apiMessageDto;
        }
        DbConfig dbConfig = dbConfigRepository.findById(updateDbConfigForm.getId()).orElse(null);
        if(dbConfig == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.DB_CONFIG_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        dbConfigMapper.fromUpdateFormToEntity(updateDbConfigForm, dbConfig);
        dbConfigRepository.save(dbConfig);
        apiMessageDto.setMessage("Update db config success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('DB_D')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        if(!isSuperAdmin()){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.DB_CONFIG_ERROR_UNAUTHORIZED);
            return apiMessageDto;
        }

        DbConfig dbConfig = dbConfigRepository.findById(id).orElse(null);
        if(dbConfig == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.DB_CONFIG_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        dbConfigRepository.delete(dbConfig);
        apiMessageDto.setMessage("Delete db config success");
        return apiMessageDto;
    }
}
