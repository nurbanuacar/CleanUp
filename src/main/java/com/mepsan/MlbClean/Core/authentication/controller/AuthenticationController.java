/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core.authentication.controller;

import com.mepsan.MlbClean.Core.authentication.dto.AuthenticationRequestDto;
import com.mepsan.MlbClean.Core.authentication.dto.AuthenticationResponseDto;
import com.mepsan.MlbClean.Core.authentication.service.AuthenticationService;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.UserDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nurbanu.acar
 */
@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("login")
    public DataResult<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }

    @PostMapping("create")
    public DataResult<String> createUser(@RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.getHashedPassword(requestDto.getPassword());
    }

    @PostMapping("update")
    public DataResult<UserDto> updatePassword(@RequestBody AuthenticationRequestDto requestDto, HttpServletRequest httpServletRequest) {
        String hashedPass = authenticationService.getHashedPassword(requestDto.getPassword()).getData();
        requestDto.setPassword(hashedPass);
        int updateId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return authenticationService.updatePassword(requestDto, updateId);
    }
}
