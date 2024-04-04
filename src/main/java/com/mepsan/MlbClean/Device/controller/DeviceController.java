/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Device.controller;

import com.mepsan.MlbClean.Device.business.DeviceService;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Dto.DeviceDto;
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
    public List<DeviceDto> getAllDevice() {
        return deviceService.getAllDevice().getData();
    }

    @GetMapping("{id}")
    public DeviceDto getDevice(@PathVariable(name = "id") int id) {
        return deviceService.getDeviceById(id).getData();
    }

    @PostMapping("save")
    public DeviceDto save(@RequestBody DeviceEntity device) {
        return deviceService.save(device, 1).getData();
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        deviceService.deleteById(id);
    }

}
