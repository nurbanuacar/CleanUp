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
        List<UserEntity> userEntities = new ArrayList<>();
        List<UserDto> userDtos = new ArrayList<>();
        UserDto userDto = new UserDto();

        userEntities = userRepository.findAll();
        if (!userEntities.isEmpty()) {
            for (UserEntity user : userEntities) {
                userDto.setName(user.getName());
                userDto.setSurname(user.getSurname());
                userDto.setUsername(user.getUsername());
                userDtos.add(userDto);
            }
            return new SuccessDataResult<>(userDtos);
        } else {
            return new ErrorDataResult<>("Herhangi bir kullanıcı bulunamadı.");
        }
    }

    @Override
    public DataResult<UserDto> getUserById(int id) {
        Optional<UserEntity> user;
        UserDto userDto = new UserDto();
        user = userRepository.findById(id);
        if (user.isPresent()) {
            userDto.setUsername(user.get().getUsername());
            userDto.setName(user.get().getName());
            userDto.setSurname(user.get().getSurname());
            userDto.setIsAdmin(user.get().isIsAdmin());
            return new SuccessDataResult<>(userDto);
        } else {
            return new ErrorDataResult<>("Kullanıcı Bulunamadı");
        }
    }

    @Override
    public DataResult<List<UserDto>> getUserByIsAdmin(boolean isAdmin) {

        List<UserEntity> users = userRepository.findByIsAdmin(isAdmin);
        List<UserDto> userDtos = new ArrayList<>();

        return new SuccessDataResult(userDtos);
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

        UserEntity newUser = userRepository.save(user);
        if (newUser.getId() > 0) {
            UserDto userDto = new UserDto();
            userDto.setName(newUser.getName());
            userDto.setSurname(newUser.getSurname());
            userDto.setUsername(newUser.getUsername());
            userDto.setIsAdmin(newUser.isIsAdmin());
            return new SuccessDataResult<>("Kullanıcı Oluşturuldu.", userDto);
        }else{
            return new ErrorDataResult<>("Kullanıcı Oluşturulurken Hata Oluştu.");
        }
    }

    @Override
    public UserEntity update(UserEntity user, int id) {

        return userRepository.save(user);
    }

}
