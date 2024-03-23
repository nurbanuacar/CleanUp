/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core;

import com.mepsan.MlbClean.User.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

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

    public static String generateRefreshToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME);
        SecretKey sKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(String.valueOf(username))
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

}
