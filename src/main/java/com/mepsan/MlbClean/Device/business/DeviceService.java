/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Device.business;

import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nurbanu.acar
 */
public interface DeviceService {

    public List<DeviceEntity> getAllDevice();

    public Optional<DeviceEntity> getDeviceById(int id);

    public DeviceEntity save(DeviceEntity device);

    public void deleteById(int id);
}
