/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.User.business;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.UserDto;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.List;

/**
 *
 * @author nurbanu.acar
 */
public interface UserService {

    public DataResult<List<UserDto>> getAllUser();

    public DataResult<UserDto> getUserById(int id);

    public DataResult<List<UserDto>> getUserByIsAdmin(boolean isAdmin);

    public DataResult<UserDto> save(UserEntity user, int processId);

    public UserEntity update(UserEntity user, int id);

    public DataResult deleteUser(int id, int updateId);

    public void deleteById(int id);
}
