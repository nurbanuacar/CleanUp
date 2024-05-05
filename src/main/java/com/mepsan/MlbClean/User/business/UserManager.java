/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.User.business;

import com.mepsan.MlbClean.Core.authentication.service.AuthenticationService;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Core.result.ErrorDataResult;
import com.mepsan.MlbClean.Core.result.SuccessDataResult;
import com.mepsan.MlbClean.Dto.UserDto;
import com.mepsan.MlbClean.User.entity.UserEntity;
import com.mepsan.MlbClean.User.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nurbanu.acar
 */
@Service
public class UserManager implements UserService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DataResult<List<UserDto>> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        if (!userEntities.isEmpty()) {
            List<UserDto> userDtos = new ArrayList<>();
            for (UserEntity user : userEntities) {
                UserDto userDto = new UserDto(user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.isIsAdmin());
                userDtos.add(userDto);
            }
            return new SuccessDataResult<>("Tüm Kullanıcılar Başarılı Şekilde Listelenmiştir.", userDtos);
        } else {
            return new ErrorDataResult<>("Herhangi bir kullanıcı bulunamadı.");
        }
    }

    @Override
    public DataResult<UserDto> getUserById(int id) {
        Optional<UserEntity> user;
        user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity existUser = user.get();
            UserDto userDto = new UserDto(existUser.getUsername(), existUser.getName(), existUser.getSurname(), existUser.isIsAdmin());
            return new SuccessDataResult<>("Kullanıcı Bulundu.", userDto);
        } else {
            return new ErrorDataResult<>("Kullanıcı Bulunamadı");
        }
    }

    @Override
    public DataResult<List<UserDto>> getUserByIsAdmin(boolean isAdmin) {
        String type = isAdmin ? "Admin" : "Personel";
        List<UserEntity> users = userRepository.findByIsAdmin(isAdmin);
        if (!users.isEmpty()) {
            List<UserDto> userDtos = new ArrayList<>();
            for (UserEntity user : users) {
                UserDto userDto = new UserDto(user.getName(), user.getSurname(), user.getUsername(), user.isIsAdmin());
                userDtos.add(userDto);
            }
            return new SuccessDataResult<>(type + " Kullanıcıları Başarılı Listelenmiştir.", userDtos);
        } else {
            return new ErrorDataResult<>("Herhangi Admin Kullanıcı Bulunamadı.");
        }
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public DataResult deleteUser(int id, int updateId) {
        Optional<UserEntity> deleteUser = userRepository.findById(id);

        if (deleteUser.isPresent()) {
            deleteUser.get().setUpdateTime(new Date());
            deleteUser.get().setUpdateId(updateId);
            userRepository.save(deleteUser.get());
            userRepository.deleteById(id);
            return new SuccessDataResult("Kullanıcı Başarılı Şekilde Silindi");
        } else {
            return new ErrorDataResult("Kullanıcı Silinirken Bir Hata Meydana Geldi");
        }
    }

    @Override
    public DataResult<UserDto> save(UserEntity user, int processId) {
        DataResult<String> password = authenticationService.getHashedPassword(user.getPassword());

        user.setCreateId(processId);
        user.setUpdateId(processId);
        user.setPassword(password.getData());

        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        if (!existingUser.isPresent()) {
            UserEntity newUser = userRepository.save(user);
            if (newUser.getId() > 0) {
                UserDto userDto = new UserDto(newUser.getName(), newUser.getSurname(), newUser.getUsername(), newUser.isIsAdmin());
                return new SuccessDataResult<>("Kullanıcı Oluşturuldu.", userDto);
            } else {
                return new ErrorDataResult<>("Kullanıcı Oluşturulurken Hata Oluştu.");
            }
        } else {
            return new ErrorDataResult<>("Bu Kullanıcı Adı Zaten Alınmış.");
        }
    }

    @Override
    public DataResult<UserDto> update(UserDto user, int id, int processId) {
        Optional<UserEntity> existUser = userRepository.findById(id);

        if (existUser.isPresent()) {
            existUser.get().setName(user.getName());
            existUser.get().setSurname(user.getSurname());
            existUser.get().setUsername(user.getUsername());
            existUser.get().setIsAdmin(user.isIsAdmin());
            existUser.get().setUpdateId(processId);
            existUser.get().setUpdateTime(new Date());
            UserEntity updatedUser = userRepository.save(existUser.get());
            if (updatedUser != null) {
                UserDto userDto = new UserDto(updatedUser.getName(), updatedUser.getSurname(), updatedUser.getUsername(), updatedUser.isIsAdmin());
                return new SuccessDataResult<>("Kullanıcı Güncelleme Başarılı.", userDto);
            } else {
                return new ErrorDataResult("Kullanıcı Güncellenemedi.");
            }
        } else {
            return new ErrorDataResult<>("Kullanıcı Bulunamadı");
        }
    }

}
