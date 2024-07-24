package ec.edu.ups.ppw.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ec.edu.ups.ppw.biblioteca.Rol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;

@ApplicationScoped
public class JwtProvider {

    private final String secretKey = "Miclave12341111AleMiclave12341111Ale123213322Miclave12341111AleMiclave12341111Ale123213322";
    private final long validityInMilliseconds = 3600000; // 1 hora

    private SecretKey generateKey() {
        byte[] encodeKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
    }

    public String createToken(String username, Set<Rol> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles.stream().map(Rol::getRolNombre).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    


    

}