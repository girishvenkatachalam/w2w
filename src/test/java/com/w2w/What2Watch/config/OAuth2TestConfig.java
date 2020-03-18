package com.w2w.What2Watch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.*;

@Configuration
public class OAuth2TestConfig {

    @Bean
    public OAuth2Authentication oAuth2Authentication() {
        return new OAuth2Authentication(getStoredRequest(), getUserAuthentication());
    }

    @Bean
    public OAuth2AuthenticationDetails oAuth2AuthenticationDetails() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        return new OAuth2AuthenticationDetails(request);
    }

    private OAuth2Request getStoredRequest() {
        Set<String> scope = new HashSet<>();
        scope.add("read");
        scope.add("write");
        return new OAuth2Request(
                Collections.EMPTY_MAP,
                "clientId",
                getGrantedAuthorityCollection(),
                true,
                scope,
                Collections.EMPTY_SET,
                null,
                Collections.EMPTY_SET,
                Collections.EMPTY_MAP);
    }

    private Authentication getUserAuthentication() {
        String credentials = "PROTECTED";
        Authentication authentication = new TestingAuthenticationToken(getPrincipalMap(), credentials, getGrantedAuthorityAsList());
        return new OAuth2Authentication(getStoredRequest(), authentication);
    }

    private Map<String, String> getPrincipalMap() {
        Map<String, String> principalMap = new LinkedHashMap<>();
        principalMap.put("id", "5c49c98d3a0f3a23cd39a720");
        principalMap.put("username", "TestUserName");
        principalMap.put("password", "TestPassword");
        principalMap.put("createdAt", "2018-06-14 10:35:05");
        principalMap.put("userType", "USER");
        principalMap.put("authorities", getGrantedAuthorityCollectionAsMap().toString());
        principalMap.put("accountNonExpired", "true");
        principalMap.put("accountNonLocked", "true");
        principalMap.put("credentialsNonExpired", "true");
        principalMap.put("enabled", "true");
        principalMap.put("uniqueId", "null");
        principalMap.put("uniqueLink", "fc3552f4-0cdf-494d-bc46-9d1e6305400a");
        principalMap.put("uniqueLinkCreatedAt", "2019-09-06 10:44:36");
        principalMap.put("someId", "59b5a82c410df8000a83a1ff");
        principalMap.put("otherId", "59b5a82c410df8000a83a1ff");
        principalMap.put("name", "TestName");
        return principalMap;
    }

    private Collection<GrantedAuthority> getGrantedAuthorityCollection() {
        return Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    private List<GrantedAuthority> getGrantedAuthorityAsList() {
        return new ArrayList<>(getGrantedAuthorityCollection());
    }

    private LinkedHashMap<String, GrantedAuthority> getGrantedAuthorityCollectionAsMap() {
        LinkedHashMap<String, GrantedAuthority> map = new LinkedHashMap<>();
        for (GrantedAuthority authority : getGrantedAuthorityCollection()) {
            map.put("authority", authority);
        }
        return map;
    }
}