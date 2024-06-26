package shop.shopping.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.getTokenFromHeader((HttpServletRequest) request);
        log.info("헤더 가져온 토큰 값 : {} " ,token);
        // 유효한 토큰인지 확인
        if (token != null && jwtTokenProvider.validateTokenExpiration(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아온다
            log.error("토큰이 인증 실패");
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장
            log.info("auth : {}" , auth);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }
}
