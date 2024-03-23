/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Device.business;

import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Device.repository.DeviceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nurbanu.acar
 */
@Service
public class DeviceManager implements DeviceService{
    
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<DeviceEntity> getAllDevice() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<DeviceEntity> getDeviceById(int id) {
        return deviceRepository.findById(id);
    }

    @Override
    public DeviceEntity save(DeviceEntity device) {
        return deviceRepository.save(device);
    }

    @Override
    public void deleteById(int id) {
        deviceRepository.deleteById(id);
    }
    
}
