package com.mangagod.security;

import java.text.ParseException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.mangagod.exception.MangaGodAppException;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component // "JwtTokenProvider" este clase se va encargar de generar el TOKEN, obtener los CLAIMS, validar el TOKEN.
public class JwtTokenProvider {

	@Value("${app.jwt-secret}") // obtendre el valor definido en el archivo "application.porperties"
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}") // obtendre el valor definido en el archivo "application.porperties"
	private int jwtExpirationInMs;
	
	public String generateToken(Authentication authentication) {
		
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder()
							.setSubject(username)
							.setIssuedAt(new Date())
							.setExpiration(fechaExpiracion)
							.signWith(SignatureAlgorithm.HS512, jwtSecret)
							.compact();	
		return token;
	}
	
	public String getUsernameOfToken(String token) {
		// Claims: es un objeto que contiene los datos del token, EJM: user, rol, fecha de caducidad.
						// asignando la clave secreta la "clave secreta" que ira acompañada del "token"
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch (SignatureException ex) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida.");
		}
		catch (MalformedJwtException ex) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST,"Token JWT no valida.");
		}
		catch (ExpiredJwtException ex) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST,"Token JWT caducado.");
		}
		catch (UnsupportedJwtException ex) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible.");
		}
		catch (IllegalArgumentException ex) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia.");
		}
	}
	
	public String refreshToken(String token) throws ParseException {
		JWT jwt = JWTParser.parse(token);
        JWTClaimsSet claims = jwt.getJWTClaimsSet();
        String username = claims.getSubject();
        Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
      
        String tokenRefresed = Jwts.builder()
				                .setSubject(username)
				                .setIssuedAt(new Date())
				                .setExpiration(fechaExpiracion)
								.signWith(SignatureAlgorithm.HS512, jwtSecret)
								.compact();	
        return tokenRefresed;
	}

}