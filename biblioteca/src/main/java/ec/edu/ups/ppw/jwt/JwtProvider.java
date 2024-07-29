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

/**
 * Proveedor de JWT (JSON Web Token) que maneja la generación, resolución y validación de tokens.
 */
@ApplicationScoped
public class JwtProvider {

    private final String secretKey = "Miclave12341111AleMiclave12341111Ale123213322Miclave12341111AleMiclave12341111Ale123213322";
    private final long validityInMilliseconds = 3600000; // 1 hora

    /**
     * Genera la clave secreta para firmar el token.
     * @return La clave secreta generada.
     */
    private SecretKey generateKey() {
        byte[] encodeKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
    }

    /**
     * Crea un token JWT para un usuario con roles específicos.
     * @param username El nombre de usuario.
     * @param roles Los roles del usuario.
     * @return El token JWT generado.
     */
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

    /**
     * Resuelve el token JWT desde el encabezado de la solicitud HTTP.
     * @param request La solicitud HTTP.
     * @return El token JWT resuelto o null si no se encuentra.
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Valida un token JWT.
     * @param token El token JWT a validar.
     * @return true si el token es válido, false en caso contrario.
     */
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
