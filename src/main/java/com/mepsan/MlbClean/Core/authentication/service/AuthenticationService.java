/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core.authentication.service;

import com.mepsan.MlbClean.Core.AuthenticationUtil;
import com.mepsan.MlbClean.Core.JwtTokenUtil;
import com.mepsan.MlbClean.Core.authentication.dto.AuthenticationRequestDto;
import com.mepsan.MlbClean.Core.authentication.dto.AuthenticationResponseDto;
import com.mepsan.MlbClean.Core.authentication.repository.AuthenticationRepository;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Core.result.ErrorDataResult;
import com.mepsan.MlbClean.Core.result.SuccessDataResult;
import com.mepsan.MlbClean.Dto.UserDto;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author nurbanu.acar
 */
@Service
@CrossOrigin
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final AuthenticationUtil authenticationUtil;

    public AuthenticationService(AuthenticationRepository authenticationRepository,
            AuthenticationUtil authenticationUtil) {
        this.authenticationRepository = authenticationRepository;
        this.authenticationUtil = authenticationUtil;
    }

    public DataResult<AuthenticationResponseDto> login(AuthenticationRequestDto user) {
        Optional<UserEntity> optionalAuthenticationEntity = authenticationRepository
                .findByUsername(user.getUsername());
        if (optionalAuthenticationEntity.isPresent()) {
            System.out.println("====================== logindeki user bilgisi == " + optionalAuthenticationEntity.get().getName());
            UserEntity authenticationEntity = optionalAuthenticationEntity.get();
            boolean isTrue = authenticationUtil.isPasswordMatch(user.getPassword(),
                    authenticationEntity.getPassword());
            if (isTrue) {
                String token = JwtTokenUtil.generateToken(authenticationEntity);
                String refreshToken = JwtTokenUtil.generateRefreshToken(authenticationEntity);
                System.out.println("TOKEENNNN " + token);
                AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto(
                        authenticationEntity.getId(),
                        authenticationEntity.getName(),
                        token,
                        refreshToken,
                        authenticationEntity.isIsAdmin());
                return new SuccessDataResult<>("Giriş başarılı.", authenticationResponseDto);
            } else {
                return new ErrorDataResult<>("Kullanıcı adı veya şifre yanlış.");
            }
        } else {
            return new ErrorDataResult<>("Bir hata oluştu.");
        }
    }

    public DataResult<AuthenticationResponseDto> addUser(AuthenticationRequestDto requestDto) {
        Date date = new Date();
        UserEntity authenticationEntity = new UserEntity();
        authenticationEntity.setUsername(requestDto.getUsername());
        authenticationEntity.setPassword(authenticationUtil.getHashedPassword(requestDto.getPassword()));
        authenticationEntity.setCreateTime(date);
        authenticationEntity.setUpdateTime(date);
        authenticationEntity.setIsAdmin(false);
        authenticationRepository.save(authenticationEntity);
        return login(requestDto);
    }

    public DataResult<UserDto> updatePassword(AuthenticationRequestDto requestDto, int updateId) {
        Optional<UserEntity> existingUser = authenticationRepository.findByUsername(requestDto.getUsername());
        if (existingUser.isPresent()) {
            Date date = new Date();

            UserEntity user = new UserEntity(existingUser.get().getId(), 
                    existingUser.get().getName(),
                    existingUser.get().getSurname(),
                    existingUser.get().getUsername(),
//                    getHashedPassword(requestDto.getPassword()).getData(),
                    requestDto.getPassword(),
                    existingUser.get().isIsAdmin(),
                    existingUser.get().getCreateId(),
                    existingUser.get().getCreateTime(),
                    updateId,
                    date,
                    existingUser.get().getDeleteTime());
            UserEntity updatedUser = authenticationRepository.save(user);
            if (updatedUser != null) {
                UserDto userDto = new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getSurname(), updatedUser.getUsername(), updatedUser.isIsAdmin());
                return new SuccessDataResult<>("Kullanıcı Parola Güncelleme Başarılı.", userDto);
            } else {
                return new ErrorDataResult("Kullanıcı Parola Güncellenemedi.");
            }
        } else {
            return new ErrorDataResult<>("Kullanıcı Bulunamadı");
        }
    }

    public DataResult<String> getHashedPassword(String password) {
        return new SuccessDataResult<>("Şifre hashlandi şimdi DB içerisine yeni kullanıcıyı ekleyebilirsiniz.",
                authenticationUtil.getHashedPassword(password));
    }
}
