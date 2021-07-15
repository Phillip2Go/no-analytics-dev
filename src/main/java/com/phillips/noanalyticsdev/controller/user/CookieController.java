package com.phillips.noanalyticsdev.controller.user;

import com.phillips.noanalyticsdev.util.CookieUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/cookie")
public class CookieController {
    Logger log = LoggerFactory.getLogger(CookieController.class);
    private static final String COOKIE_KEY = "_taggUserId";
    private static final String COOKIE_DEF_VALUE = "c2FtLnNtaXRoQGV4YW1wbGUuY29t";

    @GetMapping("/create")
    public ResponseEntity setCookie(HttpServletRequest request) {
        ResponseCookie resCookie = ResponseCookie.from(COOKIE_KEY, COOKIE_DEF_VALUE)
                .httpOnly(true)
                .sameSite("None")
                //.secure(true)
                .path("/")
                .maxAge(1 * 24 * 60 * 60)
                .domain("." + request.getServerName())
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, resCookie.toString()).build();
    }

    @GetMapping("/read")
    public String readCookie(@CookieValue(name = COOKIE_KEY, defaultValue = "default-user-id") String cookieName) {
        return String.format("value of the cookie with name _taggUserId is: %s", cookieName);
    }
}
