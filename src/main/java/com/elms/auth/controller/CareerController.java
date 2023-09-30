package com.elms.auth.controller;

import com.elms.auth.dto.ApiMessageDto;
import com.elms.auth.dto.ErrorCode;
import com.elms.auth.dto.ResponseListDto;
import com.elms.auth.dto.career.CareerDto;
import com.elms.auth.exception.UnauthorizationException;
import com.elms.auth.form.career.CreateCareerForm;
import com.elms.auth.mapper.CareerMapper;
import com.elms.auth.model.Account;
import com.elms.auth.model.Career;
import com.elms.auth.model.criteria.CareerCriteria;
import com.elms.auth.repository.AccountRepository;
import com.elms.auth.repository.CareerRepository;
import com.elms.auth.repository.DbConfigRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/v1/career")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CareerController extends ABasicController{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CareerRepository careerRepository;

//    @Autowired
//    NailsApiService nailsApiService;

//    @Autowired
//     webhookService;
    @Autowired
    DbConfigRepository dbConfigRepository;

    @Autowired
    DataSource dataSource;

    @Autowired
    CareerMapper careerMapper;

    @Autowired
    ObjectMapper objectMapper;

//    @Autowired
//    DevicesRepository devicesRepository;
//
//    @Autowired
//    AuthenticationTokenRepository tokenRepository;

    @PostMapping(value = "/create",  produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CA_C')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCareerForm createstoreByCustomerForm, BindingResult bindingResult) throws IOException {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        if(!isSuperAdmin()){
            throw new UnauthorizationException("Not allowed create.");
        }

        Account account = accountRepository.findById(createstoreByCustomerForm.getAccountId()).orElse(null);
        if(account == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.GENERAL_ERROR_ACCOUNT_NOT_FOUND);
            return apiMessageDto;
        }
        //Check path exist
        if(careerRepository.findTopByCareerPath(createstoreByCustomerForm.getCareerPath()) != null){
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.STORE_ERROR_DUPLICATE_PATH);
            return apiMessageDto;
        }

        Career store = careerMapper.fromCreateFromToEntity(createstoreByCustomerForm);
        store.setAccount(account);
        store.setBannerPath(createstoreByCustomerForm.getBannerPath());
        store.setLogoPath(createstoreByCustomerForm.getLogoPath());
        careerRepository.save(store);
        apiMessageDto.setMessage("Create store success!");
        return apiMessageDto;
    }

//    @GetMapping(value = "/list_by_customer", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ST_L_CUS')")
//    public ApiMessageDto<ResponseListDto<StoreDto>> listByCustomer(StoreByCustomerCriteria storeByCustomerCriteria, Pageable pageable) {
//        ApiMessageDto<ResponseListDto<StoreDto>> apiMessageDto = new ApiMessageDto<>();
//        if(storeByCustomerCriteria == null){
//            storeByCustomerCriteria = new StoreByCustomerCriteria();
//        }
//        storeByCustomerCriteria.setAccountId(getCurrentUser());
//
//        if(!isShop()){
//            throw new UnauthorizationException("Not allowed to retrive.");
//        }
//        Page<Store> stores = storeRepository.findAll(StoreByCustomerCriteria.findStoreByCustomerByCriteria(storeByCustomerCriteria) , pageable);
//        ResponseListDto<StoreDto> responseListDto = new ResponseListDto(storeMapper.fromEntityToCustomerDtoList(stores.getContent()), stores.getTotalElements(), stores.getTotalPages());
//        apiMessageDto.setData(responseListDto);
//        apiMessageDto.setMessage("Get list store by accountId success");
//        return apiMessageDto;
//    }


    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CA_L')")
    public ApiMessageDto<ResponseListDto<CareerDto>> list(CareerCriteria storeCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<CareerDto>> apiMessageDto = new ApiMessageDto<>();

        if(!isSuperAdmin()){
            throw new UnauthorizationException("Not allowed for get list store.");
        }

        Page<Career> stores = careerRepository.findAll(CareerCriteria.findCareerByCriteria(storeCriteria), pageable);
        ResponseListDto<CareerDto> responseListDto = new ResponseListDto(careerMapper.fromEntityToCustomerDtoList(stores.getContent()),stores.getTotalElements(), stores.getTotalPages());
        apiMessageDto.setData(responseListDto);
        apiMessageDto.setMessage("Get list store success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CA_V')")
    public ApiMessageDto<CareerDto> get(@PathVariable("id") Long id) {
        var store = careerRepository.findById(id).orElse(null);
        ApiMessageDto<CareerDto> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setData(careerMapper.fromCareerToDto(store));
        apiMessageDto.setMessage("Get store by id success");
        return apiMessageDto;
    }

//    @GetMapping(value = "/detail/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiMessageDto<StorePublicDto> scanQRCode(@PathVariable("storeId") String storeId) {
//        ApiMessageDto<StorePublicDto> apiMessageDto = new ApiMessageDto<>();
//        Store store = storeRepository.findTopByStorePath(storeId);
//        if(store == null){
//            try {
//                store = storeRepository.findById(Long.parseLong(storeId)).orElse(null);
//            }catch (Exception e){
//                log.error(e.getMessage(),e);
//            }
//        }
//
//        if(store == null){
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.STORE_ERROR_NOT_FOUND);
//            return apiMessageDto;
//        }
//        DbConfig dbConfig = dbConfigRepository.findByStoreId(store.getId());
//        if(dbConfig == null){
//            apiMessageDto.setResult(false);
//            apiMessageDto.setMessage(ErrorCode.DB_CONFIG_ERROR_NOT_FOUND);
//            return apiMessageDto;
//        }
//        StorePublicDto storeDetailDto = storeMapper.fromStoreToPublicDto(store);
//        storeDetailDto.setServerTime(new Date());
//        //modify timezone offset
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//            StoreSettings settings =  objectMapper.readValue(storeDetailDto.getSettings(), StoreSettings.class);
//            TimeZoneClaxx claxx = settings.getTimezone();
//            claxx.setOffset(DateUtils.getOffset(TimeZone.getTimeZone(claxx.getName())));
//            settings.setTimezone(claxx);
//
//            if(settings.getPrinters()!=null){
//                for(Printer p : settings.getPrinters()){
//                    if(p.getGroupName() != null){
//                        p.setCode(p.getGroupName());
//                    }
//                }
//            }
//
//            //update to setting
//            storeDetailDto.setSettings(objectMapper.writeValueAsString(settings));
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//        }
//
//        //inject tenants
//        storeDetailDto.setTenantId(dbConfig.getName());
//
//        apiMessageDto.setData(storeDetailDto);
//        apiMessageDto.setMessage("Get store from scan success");
//        return apiMessageDto;
//    }


//    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ST_D')")
//    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
//        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
//
//        if(!isSuperAdmin()){
//            throw new UnauthorizationException("Not allowed to delete store.");
//        }
//
//        Store store = storeRepository.findById(id).orElse(null);
//        if (store == null) {
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.STORE_ERROR_NOT_FOUND);
//            return apiMessageDto;
//
//        }
//        nailsApiService.deleteFile(store.getBannerPath());
//        nailsApiService.deleteFile(store.getLogoPath());
//        DbConfig dbConfig = dbConfigRepository.findByStoreId(store.getId());
//        if(dbConfig != null){
//            dbConfigRepository.delete(dbConfig);
//
//            String databaseName = TenantUtils.parseDatabaseNameFromConnectionString(dbConfig.getUrl());
//            // Open a connection
//            try(
//                    Connection connection = dataSource.getConnection();
//                    Statement statement = connection.createStatement();
//            ) {
//                statement.execute("DROP DATABASE " + databaseName);
//                statement.execute("DROP USER '" + dbConfig.getUsername() + "'@'localhost';");
//            } catch (SQLException e) {
//                e.printStackTrace();
//                apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.DB_CONFIG_ERROR_DROP);
//                return apiMessageDto;
//            }
//        }
//        List<Devices> list = devicesRepository.findAllDevicesByStoreId(store.getId());
//        List<AuthenticationToken> tokenList = tokenRepository.findAllByStoreId(store.getId());
//        devicesRepository.deleteAll(list);
//        tokenRepository.deleteAll(tokenList);
//        storeRepository.deleteById(id);
//        apiMessageDto.setMessage("Delete store success");
//        return apiMessageDto;
//    }
//
//
//    @PutMapping(value = "/update_by_customer", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiMessageDto<String> updateByCustomer(@Valid @RequestBody UpdateStoreByCustomerForm updateStoreByCustomerForm, BindingResult bindingResult) throws JsonProcessingException {
//        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
//        if(!isShop()){
//            throw new UnauthorizationException("Not allowed to update your shop.");
//        }
//        Store store = storeRepository.findById(updateStoreByCustomerForm.getId()).orElse(null);
//        if (store == null) {
//            apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.STORE_ERROR_NOT_FOUND);
//            return apiMessageDto;
//        }
//        long id = getCurrentUser();
//        if(!Objects.equals(store.getAccount().getId(),id)){
//            throw new UnauthorizationException("Not allowed to update store.");
//        }
//
//        if(StringUtils.isNoneBlank(updateStoreByCustomerForm.getBannerPath())
//                && !updateStoreByCustomerForm.getBannerPath().equals(store.getBannerPath())) {
//            //delete old image
//            nailsApiService.deleteFile(store.getBannerPath());
//        }
//        if(StringUtils.isNoneBlank(updateStoreByCustomerForm.getLogoPath())
//                && !updateStoreByCustomerForm.getLogoPath().equals(store.getLogoPath())) {
//            //delete old image
//            nailsApiService.deleteFile(store.getLogoPath());
//        }
//
//        storeMapper.fromCustomerUpdateFormToEntity(updateStoreByCustomerForm, store);
//        storeRepository.save(store);
//
//        // Push firebase for notification tablet sync
//        /*String idFirebase = "UPDATE_"+ rabbitMQService.getOTPFirebase();*/
//        BaseWebHookRequest request = new BaseWebHookRequest();
//        request.setCmd(CmdWebhook.UPDATE_STORE_SETTING);
//        String json = objectMapper.writeValueAsString(request);
//        webhookService.pushToAllDeviceRabbitMq(store, json);
//
//        apiMessageDto.setMessage("Update store by customer success");
//        return apiMessageDto;
//    }
//
//    // Stop service request from customer do qua tai
//    /**
//     * Type: qrlive, web
//     * */
//    @PutMapping(value = "/lock_store/{type}/{statusLock}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ST_LOCK')")
//    public ApiMessageDto<String> lockstore(@PathVariable("type") String type, @PathVariable("statusLock") Long statusLock) throws JsonProcessingException {
//        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
//        if(type == null){
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.GENERAL_ERROR_REQUIRE_PARAMS);
//            return apiMessageDto;
//        }
//
//        Store store = storeRepository.findById(getSessionFromToken().getStoreId()).orElse(null);
//        if (store == null) {
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.STORE_ERROR_NOT_FOUND);
//            return apiMessageDto;
//        }
//        if(Objects.equals(type,"qrlive")){
//            store.setIsReadyQrLive(statusLock == 0 ? false: true);
//        }else{
//            store.setIsReady(statusLock == 0 ? false: true);
//        }
//
//        storeRepository.save(store);
//        if(Objects.equals(type,"qrlive")){
//            BaseWebHookRequest request = new BaseWebHookRequest();
//            request.setCmd(CmdWebhook.LOCK_STORE);
//            if(statusLock == 1){
//                request.setCmd(CmdWebhook.UN_LOCK_STORE);
//            }
//            String json = objectMapper.writeValueAsString(request);
//            webhookService.pushToAllDeviceRabbitMqByKind(store,json, NailsConstant.TABLET_DEVICE_TYPE_ORDER_QRCODE_SHOW);
//        }
//        apiMessageDto.setMessage("Update store state ok");
//        return apiMessageDto;
//    }

//    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ST_U')")
//    public ApiMessageDto<String> updateB(@Valid @RequestBody UpdateStoreForm updateStoreForm, BindingResult bindingResult) throws IOException {
//        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
//        if(!isSuperAdmin()){
//            throw new UnauthorizationException("Not allowed update shop.");
//        }
//
//        Store store = storeRepository.findById(updateStoreForm.getId()).orElse(null);
//        if (store == null) {
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.STORE_ERROR_NOT_FOUND);
//            return apiMessageDto;
//        }
//
//        //Check path exist
//        if(updateStoreForm.getStorePath()!=null && !Objects.equals(updateStoreForm.getStorePath(), store.getStorePath())
//                && storeRepository.findTopByStorePath(updateStoreForm.getStorePath()) != null){
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.STORE_ERROR_DUPLICATE_PATH);
//            return apiMessageDto;
//        }
//        if(StringUtils.isNoneBlank(updateStoreForm.getBannerPath())
//                && !updateStoreForm.getBannerPath().equals(store.getBannerPath())) {
//            //delete old image
//            nailsApiService.deleteFile(store.getBannerPath());
//        }
//        if(StringUtils.isNoneBlank(updateStoreForm.getLogoPath())
//                && !updateStoreForm.getLogoPath().equals(store.getLogoPath())) {
//           //delete old image
//            nailsApiService.deleteFile(store.getLogoPath());
//        }
//        storeMapper.fromAdminUpdateFormToEntity(updateStoreForm, store);
//        storeRepository.save(store);
//        // Push firebase for notification tablet sync
////        String idFirebase = "UPDATE_"+firebaseService.getOTPFirebase();
////        String json ="{\"cmd\":\"UPDATE_store_SETTING\"}";
////        firebaseService.pushToAllDeviceFirebase(store,idFirebase, json);
//
//        apiMessageDto.setMessage("Update store success");
//        return apiMessageDto;
//    }


//    @GetMapping(value = "/get_info/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiMessageDto<StoreInfoDto> getInfo(@PathVariable("resId") Long resId) {
//        ApiMessageDto<StoreInfoDto> apiMessageDto = new ApiMessageDto<>();
//
//        if(resId == null){
//            throw new BadRequestException("Not allowed for get list store.");
//        }
//
//        Store store = storeRepository.findById(resId).orElse(null);
//        if(store ==null){
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.STORE_ERROR_NOT_FOUND);
//            return apiMessageDto;
//        }
//        ShopProfile shopProfile = shopProfileRepository.findById(store.getAccount().getId()).orElse(null);
//
//        StoreInfoDto storeInfoDto = new StoreInfoDto();
//        storeInfoDto.setId(shopProfile != null ? shopProfile.getId() : null);
//        storeInfoDto.setStorePath(store.getStorePath());
//
//        apiMessageDto.setData(storeInfoDto);
//        apiMessageDto.setMessage("Get store info success");
//        return apiMessageDto;
//    }
}
