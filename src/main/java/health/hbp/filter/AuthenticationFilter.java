package health.hbp.filter;

import health.hbp.model.User;
import health.hbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nullable;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTH_USER = "username";
    public static final String AUTH_PASS = "password";

    @Autowired
    UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = repository.findByUsername(request.getHeader(AUTH_USER));
        if (user==null || user.getPassword()==null || !user.getPassword().equals(request.getHeader(AUTH_PASS))) {
            response.setStatus(403);
            response.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().contains("login");
    }
}
