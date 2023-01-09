package com.pythonstrup.demo.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        HttpSession session = request.getSession();
        // session 최대 유효시간 설정 - 단위는 초(sec)
        session.setMaxInactiveInterval(60 * 30);
        response.sendRedirect("/v1/auth/login/success");

        // 물론 아래와 같이 처리해줘도 괜찮다.
        // response.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
