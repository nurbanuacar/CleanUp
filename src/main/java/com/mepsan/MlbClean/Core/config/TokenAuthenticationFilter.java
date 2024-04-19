/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core.config;

import com.mepsan.MlbClean.Core.JwtTokenUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author nurbanu.acar
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("*************** *************");
        String token = extractTokenFromRequest(request);
        String refreshToken = extractRefreshTokenFromRequest(request);
        if (token != null && JwtTokenUtil.validateToken(token)) {
            String id = JwtTokenUtil.getIdFromToken(token);
            request.setAttribute("userId", id);
            System.out.println("*** token auth filter **** " + id);
            filterChain.doFilter(request, response);
        } else {
            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else if (!JwtTokenUtil.isExpired(refreshToken)) {
                System.out.println("==== ELSE IFE GIRDI ==========");
                // JWT süresi dolduğunda refresh token kontrolü yap
                if (refreshToken != null && JwtTokenUtil.validateRefreshToken(refreshToken)) {
                    // Refresh token geçerli ise, yeni bir JWT oluştur ve istemciye gönder
                    String newToken = JwtTokenUtil.generateTokenFromRefreshToken(refreshToken);
                    response.setHeader("Authorization", "Bearer " + newToken);
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        // Header'dan Bearer token'ı al
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // "Bearer " kısmını kaldırarak token'ı al
            return bearerToken.substring(7);
        }
        return null;
    }

    private String extractRefreshTokenFromRequest(HttpServletRequest request) {
        System.out.println("************ EXTRACT REFRESH TOKEN **************");
        // Header'dan Refresh-Token'ı al
        return request.getHeader("Refresh-Token");
    }
}
