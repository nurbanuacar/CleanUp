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
@RequestMapping("/api/v2/user/")
@CrossOrigin
public class UserControllerDR {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("all")
    public DataResult<List<UserDto>> getAllUser() {
        String addr = request.getRemoteAddr();
        System.out.println("================ addr : " + addr + " ===========");
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public DataResult<UserDto> getUser(@PathVariable(name = "id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("employee")
    public DataResult<List<UserDto>> getEmployees() {
        return userService.getUserByIsAdmin(false);
    }

    @GetMapping("admin")
    public DataResult<List<UserDto>> getAdmins() {
        return userService.getUserByIsAdmin(true);
    }

    @PostMapping("save")
    public DataResult<UserDto> save(@RequestBody UserEntity user, HttpServletRequest httpServletRequest) {
        int processId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return userService.save(user, processId);
    }

    @GetMapping("delete/{id}")
    public DataResult delete(@PathVariable(name = "id") int id, HttpServletRequest httpServletRequest) {
        String tempId = (String) httpServletRequest.getAttribute("userId");
        int updateId = Integer.parseInt(tempId);

        return userService.deleteUser(id, updateId);
    }

    @PutMapping("update/{id}")
    public DataResult<UserDto> update(@RequestBody UserDto user, @PathVariable(name = "id") int id, HttpServletRequest httpServletRequest) {
        int updateId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return userService.update(user, id, updateId);
    }
}
