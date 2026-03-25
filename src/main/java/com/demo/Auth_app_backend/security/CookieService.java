package com.demo.Auth_app_backend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@Getter//Using getter because we have to expose cookie  name to controller so controller can read it consistently
public class CookieService {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CookieService.class);

    private final String cookieName;
    private final boolean cookieHttpOnly;
    private final boolean cookieSecure;
    private final String cookieDomain;
    private final String cookieSameSite;

    public CookieService(
            @Value("${security.jwt.refresh-token-cookie-name}") String cookieName,
            @Value("${security.jwt.cookie-http-only}") boolean cookieHttpOnly,
            @Value("${security.jwt.cookie-secure}") boolean cookieSecure,
            @Value("${security.jwt.cookie-domain}") String cookieDomain,
            @Value("${security.jwt.cookie-same-site}") String cookieSameSite) {
        this.cookieName = cookieName;
        this.cookieHttpOnly = cookieHttpOnly;
        this.cookieSecure = cookieSecure;
        this.cookieDomain = cookieDomain;
        this.cookieSameSite = cookieSameSite;
    }

    //method to attach cookie to response
    public void attachRefreshCookie(HttpServletResponse response, String value,int maxAge) { //String value Contains cookie

        logger.info("Attaching Cookie With Name: {} and  value:{}", cookieName,value);
        ResponseCookie.ResponseCookieBuilder responseCookieBuilder = ResponseCookie.from(cookieName, value)
                .httpOnly(cookieHttpOnly)
                .secure(cookieSecure)
                .path("/")
                .sameSite(cookieSameSite)
                .maxAge(maxAge);

        if (cookieDomain != null && !cookieDomain.isBlank()) {
            responseCookieBuilder.domain(cookieDomain);//if domain in not null then set the domain
        }
        ResponseCookie responseCookie = responseCookieBuilder.build();//build the cookie
        response.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());//sending the cookie in response
    }

    //method to clear Refresh Cooke after logout
    public void clearRefreshCookie(HttpServletResponse response){
        ResponseCookie.ResponseCookieBuilder responseCookieBuilder = ResponseCookie.from(cookieName,"")
                .maxAge(0)
                .secure(cookieSecure)
                .path("/")
                .httpOnly(cookieHttpOnly)
                .sameSite(cookieSameSite);


        if (cookieDomain != null && !cookieDomain.isBlank()) {
            responseCookieBuilder.domain(cookieDomain);//if domain in not null then set the domain
        }

        ResponseCookie responseCookie = responseCookieBuilder.build();
        response.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());

    }

    public void addNoStoreHeaders(HttpServletResponse response) {
        response.setHeader(HttpHeaders.CACHE_CONTROL,"no-store");
        response.setHeader("pragma","no-cache");
    }
}
