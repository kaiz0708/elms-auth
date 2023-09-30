package com.elms.auth.config;

import com.elms.auth.dto.AccountForTokenDto;
import com.elms.auth.utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {
    private JdbcTemplate jdbcTemplate;

    public CustomTokenEnhancer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CustomTokenEnhancer() {
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        String grantType = authentication.getOAuth2Request().getRequestParameters().get("grantType");
        if (Objects.equals(SecurityConstant.GRANT_TYPE_ANONYMOUS, grantType)) {
            return hanldeGrantTypeAnonymous(accessToken, authentication);
        } else if (Objects.equals(SecurityConstant.GRANT_TYPE_QRLIVE_CLIENT, grantType)) {
            return hanldeGrantTypeQRLiveEndUser(accessToken, authentication);
        } else if (Objects.equals(SecurityConstant.GRANT_TYPE_TABLE_QR_CLIENT, grantType)) {
            return hanldeGrantTypeTableQREndUser(accessToken, authentication);
        } else {
            String username = authentication.getName();
            AccountForTokenDto a = getAccountByUsername(username);

            if (a != null) {
                Long accountId = a.getId();
                Long storeId = -1L;
                String kind = a.getKind() + "";//token kind
                Long deviceId = -1L;// id cua thiet bi, lưu ở table device để get firebase url..
                String pemission = "<>";//empty string
                Integer userKind = a.getKind(); //loại user là admin hay là gì
                Integer tabletKind = -1;
                Long orderId = -1L;
                Boolean isSuperAdmin = a.getIsSuperAdmin();
                String tenantId = getTenantByAccountId(a.getId());
                additionalInfo.put("user_id", a.getId());
                additionalInfo.put("user_kind", a.getKind());
                additionalInfo.put("grant_type", SecurityConstant.GRANT_TYPE_PASSWORD);
                additionalInfo.put("tenant_info", tenantId);
                String DELIM = "|";
                String additionalInfoStr = ZipUtils.zipString(accountId + DELIM
                        + storeId + DELIM
                        + kind + DELIM
                        + pemission + DELIM
                        + deviceId + DELIM
                        + userKind + DELIM
                        + username + DELIM
                        + tabletKind + DELIM
                        + orderId + DELIM
                        + isSuperAdmin + DELIM
                        + tenantId);
                additionalInfo.put("additional_info", additionalInfoStr);
            }

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        }
    }

    private OAuth2AccessToken hanldeGrantTypeQRLiveEndUser(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
        Map<String, Object> additionalInfo = new HashMap<>();
        String username = authentication.getName();
        AccountForTokenDto a = getAccountByUsername(username);
        String tenant = authentication.getOAuth2Request().getRequestParameters().get("tenant");
        String orderCode = authentication.getOAuth2Request().getRequestParameters().get("orderCode");
        String resId = authentication.getOAuth2Request().getRequestParameters().get("resId");
        String deviceIdRq = authentication.getOAuth2Request().getRequestParameters().get("deviceId");
        log.error("------> grantype qrlive client: "+tenant+", orderCode:"+orderCode+", resId: "+resId+", deviceIdRq: "+deviceIdRq);
        if (a != null) {
            Long accountId = a.getId();
            Long storeId = Long.valueOf(resId);
            String kind = a.getKind()+"";//token kind
            Long deviceId = Long.valueOf(deviceIdRq);// id cua thiet bi, lưu ở table device để get firebase url..
            String pemission = orderCode;//empty string
            Integer userKind = a.getKind(); //loại user là admin hay là gì
            Integer tabletKind = -1;
            Long orderId = -1L;
            Boolean isSuperAdmin = false;
            additionalInfo.put("user_id", a.getId());
            additionalInfo.put("user_kind", a.getKind());
            additionalInfo.put("grant_type", SecurityConstant.GRANT_TYPE_QRLIVE_CLIENT);
            additionalInfo.put("tenant_info", tenant);
            String DELIM = "|";
            String additionalInfoStr = ZipUtils.zipString(accountId+DELIM
                    +storeId+DELIM
                    +kind+DELIM
                    +pemission+DELIM
                    +deviceId+DELIM
                    +userKind+DELIM
                    +username+DELIM
                    +tabletKind+DELIM
                    +orderId+DELIM
                    +isSuperAdmin+DELIM
                    +tenant) ;
            additionalInfo.put("additional_info", additionalInfoStr);
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

    private OAuth2AccessToken hanldeGrantTypeAnonymous(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
        Map<String, Object> additionalInfo = new HashMap<>();
        String username = authentication.getName();
        AccountForTokenDto a = getAccountByUsername(username);
        String tenant = authentication.getOAuth2Request().getRequestParameters().get("tenant");
        if (a != null) {
            Long accountId = a.getId();
            Long storeId = -1L;
            String kind = a.getKind()+"";//token kind
            Long deviceId =  -1L;// id cua thiet bi, lưu ở table device để get firebase url..
            String pemission = "<>";//empty string
            Integer userKind = a.getKind(); //loại user là admin hay là gì
            Integer tabletKind = -1;
            Long orderId = -1L;
            Boolean isSuperAdmin = false;

            additionalInfo.put("user_id", a.getId());
            additionalInfo.put("user_kind", a.getKind());
            additionalInfo.put("grant_type", SecurityConstant.GRANT_TYPE_ANONYMOUS);
            additionalInfo.put("tenant_info", tenant);
            String DELIM = "|";
            String additionalInfoStr = ZipUtils.zipString(accountId+DELIM
                    +storeId+DELIM
                    +kind+DELIM
                    +pemission+DELIM
                    +deviceId+DELIM
                    +userKind+DELIM
                    +username+DELIM
                    +tabletKind+DELIM
                    +orderId+DELIM
                    +isSuperAdmin+DELIM
                    +tenant) ;
            additionalInfo.put("additional_info", additionalInfoStr);
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

    public AccountForTokenDto getAccountByUsername(String username) {
        try {
            String query = "SELECT id, kind, username, email, full_name, is_super_admin " +
                    "FROM db_elms_account WHERE username = ? and status = 1 limit 1";
            log.debug(query);
            List<AccountForTokenDto> dto = jdbcTemplate.query(query, new Object[]{username},  new BeanPropertyRowMapper<>(AccountForTokenDto.class));
            if (dto.size() > 0)return dto.get(0);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTenantByAccountId(Long accountId){
        try{
            String query = "select distinct coalesce(GROUP_CONCAT(CONCAT(d.name, \"&\", d.career_id) SEPARATOR ':'), '') " +
                    "from db_elms_career r " +
                    "join db_elms_db_config d on r.id = d.career_id " +
                    "where account_id = ? and status = 1 ";
            return jdbcTemplate.queryForObject(query, String.class, accountId);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private OAuth2AccessToken hanldeGrantTypeTableQREndUser(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
        Map<String, Object> additionalInfo = new HashMap<>();
        String username = authentication.getName();
        AccountForTokenDto a = getAccountByUsername(username);
        String tenant = authentication.getOAuth2Request().getRequestParameters().get("tenant");
        String orderIdRq = authentication.getOAuth2Request().getRequestParameters().get("orderId");
        String resId = authentication.getOAuth2Request().getRequestParameters().get("resId");
        String fingerId = authentication.getOAuth2Request().getRequestParameters().get("fingerId");
        log.error("------> grantype table qr client: "+tenant+", orderId:"+orderIdRq+", resId: "+resId+", fingerId: "+fingerId);
        if (a != null) {
            Long accountId = a.getId();
            Long storeId = Long.valueOf(resId);
            String kind = a.getKind()+"";//token kind
            Long deviceId = Long.valueOf(orderIdRq);// id cua thiet bi, lưu ở table device để get firebase url..
            String pemission = orderIdRq;//empty string
            Integer userKind = a.getKind(); //loại user là admin hay là gì
            Integer tabletKind = -1;
            Long orderId = Long.valueOf(orderIdRq);
            Boolean isSuperAdmin = false;
            additionalInfo.put("user_id", a.getId());
            additionalInfo.put("user_kind", a.getKind());
            additionalInfo.put("grant_type", SecurityConstant.GRANT_TYPE_TABLE_QR_CLIENT);
            additionalInfo.put("tenant_info", (tenant));
            String DELIM = "|";
            String additionalInfoStr = ZipUtils.zipString(accountId+DELIM
                    +storeId+DELIM
                    +kind+DELIM
                    +pemission+DELIM
                    +deviceId+DELIM
                    +userKind+DELIM
                    +fingerId+DELIM
                    +tabletKind+DELIM
                    +orderId+DELIM
                    +isSuperAdmin+DELIM
                    +(tenant)) ;
            additionalInfo.put("additional_info", additionalInfoStr);
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
