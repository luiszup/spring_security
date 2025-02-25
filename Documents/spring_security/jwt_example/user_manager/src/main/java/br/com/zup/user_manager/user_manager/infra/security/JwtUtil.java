package br.com.zup.user_manager.user_manager.infra.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

public class JwtUtil {

    // Chave secreta usada para assinar e validar os tokens JWT
    private final String SECRET_KEY = "palavra_oculta_xablau";

    /**
     * Metodo para criar um token JWT.
     * @param claims - Informações adicionais (payload) que serão incluídas no token.
     * @param subject - Identificação do usuário (geralmente o username ou email).
     * @return Um token JWT assinado.
     */

    private String createToken (Map<String, Object> claims, String subject) {
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return token;
    }

    /**
     * Extrai todas as informações (claims) de um token JWT.
     * @param token - O token JWT.
     * @return As claims contidas no token.
     */

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
    }

    /**
     * Extrai uma claim específica do token JWT.
     * @param token - O token JWT.
     * @param claimsResolver - Função que define qual claim será extraída.
     * @return O valor da claim extraída.
     */

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrai a data de expiração do token JWT.
     * @param token - O token JWT.
     * @return A data de expiração do token.
     */

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica se o token JWT está expirado.
     * @param token - O token JWT.
     * @return true se o token estiver expirado, false caso contrário.
     */

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrai o username (subject) do token JWT.
     * @param token - O token JWT.
     * @return O username contido no token.
     */

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Valida o token JWT verificando o username e se ele não está expirado.
     * @param token - O token JWT.
     * @param username - O username esperado.
     * @return true se o token for válido, false caso contrário.
     */

    public boolean validateToken(String token, String username){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }


}
