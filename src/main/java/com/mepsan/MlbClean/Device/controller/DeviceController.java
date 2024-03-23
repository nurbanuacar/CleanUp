/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Device.controller;

import com.mepsan.MlbClean.Device.business.DeviceService;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nurbanu.acar
 */
@RestController
@RequestMapping("/api/device/")
@CrossOrigin
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("all")
    public List<DeviceEntity> getAllDevice() {
        return deviceService.getAllDevice();
    }

    @GetMapping("{id}")
    public Optional<DeviceEntity> getDevice(@PathVariable(name = "id") int id) {
        return deviceService.getDeviceById(id);
    }

    @PostMapping("save")
    public DeviceEntity save(@RequestBody DeviceEntity device) {
        return deviceService.save(device);
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        deviceService.deleteById(id);
    }

}
