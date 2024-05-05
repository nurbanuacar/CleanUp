/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.User.controller;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.UserDto;
import com.mepsan.MlbClean.User.business.UserService;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nurbanu.acar
 */
@RestController
@RequestMapping("/api/user/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("all")
    public List<UserDto> getAllUser() {
        String addr = request.getRemoteAddr();
        System.out.println("================ addr : " + addr + " ===========");
        DataResult<List<UserDto>> result = userService.getAllUser();
        return result.getData();
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable(name = "id") int id) {
        DataResult<UserDto> result = userService.getUserById(id);
        return result.getData();
    }

    @GetMapping("employee")
    public DataResult<List<UserDto>> getUserByIsAdmin() {
        return userService.getUserByIsAdmin(false);
    }

    @PostMapping("save")
    public UserDto save(@RequestBody UserEntity user) {
        return userService.save(user, 1).getData();
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        userService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public UserDto update(@RequestBody UserDto user, @PathVariable(name = "id") int id) {
        return userService.update(user, id, 1).getData();
    }

}
