/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Device.controller;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Device.business.DeviceService;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Dto.DeviceDto;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/api/v2/device/")
@CrossOrigin
public class DeviceControllerDR {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("all")
    public DataResult<List<DeviceDto>> getAllDevice() {
        return deviceService.getAllDevice();
    }

    @GetMapping("{id}")
    public DataResult<DeviceDto> getDevice(@PathVariable(name = "id") int id) {
        return deviceService.getDeviceById(id);
    }
    
    @GetMapping("name/{deviceName}")
    public DataResult<DeviceDto> getDeviceByDeviceName(@PathVariable(name = "deviceName") String deviceName) {
        return deviceService.getDeviceByDeviceName(deviceName);
    }

    @PostMapping("save")
    public DataResult<DeviceDto> save(@RequestBody DeviceEntity device, HttpServletRequest httpServletRequest) {
        int updateId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return deviceService.save(device, updateId);
    }

    @PutMapping("update/{id}")
    public DataResult<DeviceDto> update(@RequestBody DeviceDto device, @PathVariable(name = "id") int deviceId, HttpServletRequest httpServletRequest) {
        int updateId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        
        return deviceService.update(device, deviceId, updateId);
    }

    @GetMapping("delete/{id}")
    public DataResult<DeviceDto> deleteDevice(@PathVariable(name = "id") int id, HttpServletRequest httpServletRequest) {
        int updateId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return deviceService.deleteDevice(id, updateId);
    }
}
