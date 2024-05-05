/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core;

import com.mepsan.MlbClean.User.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.function.Function;

/**
 *
 * @author nurbanu.acar
 */
public class JwtTokenUtil {

    private static final String SECRET_KEY = "mPcLnvrOnepoWerfulsEcrEt1qdasqwfasfaasdacxfgsdvzxzxcasgbhbsd";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;//1 saat
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 10;//10 saat
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(UserEntity user) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);
        SecretKey sKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("id", user.getId())
                .claim("isAdmin", user.isIsAdmin())
                .setExpiration(expirationDate)
                .signWith(sKey)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | IllegalArgumentException e) {
            return false;
//            throw new RuntimeException(e);
        }
    }

    public static String generateRefreshToken(UserEntity user) {
        System.out.println("*-*-*-*- GENERATE REFRESH TOKEN GIRISS -*-*-*-*-*");
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME);
        SecretKey sKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(String.valueOf(user.getUsername()))
                .claim("id", user.getId())
                .claim("isAdmin", user.isIsAdmin())
                .setExpiration(expiryDate)
                .signWith(sKey)
                .compact();
    }

    public static boolean validateRefreshToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | IllegalArgumentException e) {
            return false;
//            throw new RuntimeException(e);
        }
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build().parseClaimsJws(token).getBody();
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static Boolean isExpired(String token) {
        Boolean result = extractExpiration(token).before(new Date());
        return result;
    }

//    public RefreshToken verifyExpiration(RefreshToken token) {
//        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
//        }
//
//        return token;
//    }

//    public static boolean isExpired(String token) {
//        try {
//            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//            Date expirationDate = claims.getExpiration();
//            return expirationDate.before(new Date());
//        } catch (Exception e) {
//            return true;
//        }
//    }
    public static String getIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public static String generateTokenFromRefreshToken(String refreshToken) {
        System.out.println("*-*-*-*- GENERATE TOKEN FROM !!!!! REFRESH TOKEN GIRISS -*-*-*-*-*");

        // Eğer refresh token null veya boş ise, geçersiz token hatası döndür
        if (refreshToken == null || refreshToken.isEmpty()) {
            return null;
        }

        // Eğer refresh token doğrulandıysa, yeni bir erişim tokenı oluştur
        String id = getIdFromToken(refreshToken);
//        List<String> authorities = (List<String>) claims.get("authorities");
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME);

        SecretKey sKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(id)
                .claim("id", id)
                .claim("isAdmin", claims.get("isAdmin", Boolean.class))
                .setExpiration(expiryDate)
                .signWith(sKey)
                .compact();
    }
}
