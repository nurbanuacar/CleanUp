/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author nurbanu.acar
 */

@Component
public class AuthenticationUtil {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String getHashedPassword(String password){
        return passwordEncoder.encode(password);
    }
    public boolean isPasswordMatch(String password,String hashedPassword){
        return passwordEncoder.matches(password,hashedPassword);
    }
}
