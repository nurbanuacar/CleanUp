/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.User.repository;

import com.mepsan.MlbClean.Dto.UserDto;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nurbanu.acar
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    public List<UserEntity> findByIsAdmin(boolean isAdmin);

//    public UserDto save(UserDto user); 
    
    
}
