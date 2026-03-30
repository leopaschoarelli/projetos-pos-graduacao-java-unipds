package br.com.leopaschoarelli.authapi.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class TokenUtil {

    public static Authentication decode(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println("Request Header: " + header);
        if (header != null) {
            String token = header.replace("Bearer ", "");
            if (token.equals("security123")) {
                return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
            }
        }
        return null;
    }

}
