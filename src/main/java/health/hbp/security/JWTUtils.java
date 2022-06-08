package health.hbp.security;

import health.hbp.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class JWTUtils {

    @Value("${security.token.secret}")
    private String secretKey;

    @Value("${security.token.exp.days}")
    private Integer expDays;

    private final UserService userService;

    /** Generacion de un nuevo token para el user logueado */
    public String buildToken() {
        return buildToken(userService.getLoggedUser().get().getUsername());
    }

    /** Generacion de un nuevo token para el username indicado */
    public String buildToken(String userName) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("JWTBuilder")
                .setSubject(userName)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expDays * 24 * 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }

    /** Validacion de token */
    public Claims validateToken(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
    }
}
