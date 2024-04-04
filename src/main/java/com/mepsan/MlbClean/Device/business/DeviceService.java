/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Device.business;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Dto.DeviceDto;
import java.util.List;

/**
 *
 * @author nurbanu.acar
 */
public interface DeviceService {

    public DataResult<List<DeviceDto>> getAllDevice();

    public DataResult<DeviceDto> getDeviceById(int id);

    public DataResult<DeviceDto> getDeviceByDeviceName(String name);

    public DataResult<DeviceDto> save(DeviceEntity device, int processId);

    public void deleteById(int id);

    public DataResult deleteDevice(int id, int processId);

    public DataResult<DeviceDto> update(DeviceDto deviceDto, int id, int updateId);
}
