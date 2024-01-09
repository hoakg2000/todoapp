package prototype.backend.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import prototype.backend.config.CustomerUserDetailsService;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtilities jwtUtilities ;
    private final CustomerUserDetailsService customerUserDetailsService ;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtUtilities.getToken(request) ;

        if (token!=null && jwtUtilities.validateToken(token)){
            String username = jwtUtilities.extractUsername(token);

            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,null , userDetails.getAuthorities());
                log.info("authenticated user with username :{}", username);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        response.setHeader("Access-Control-Allow-Origin", "*"); // *: cho phép tất cả các domain khác gọi vào
        response.setHeader("Access-Control-Allow-Methods", "GET"); // Cho phép các phương thức
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, X-Requested-With, remember-me"); // Cho phép dữ liệu gửi lên server có dạng json, form data
        response.setHeader("Access-Control-Max-Age", "3600"); // Thời gian cho phép truy cập
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Cho phép gửi cookie
        filterChain.doFilter(request, response); // Cho phép đi tiếp
    }

}
