/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Device.business;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Core.result.ErrorDataResult;
import com.mepsan.MlbClean.Core.result.SuccessDataResult;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Device.repository.DeviceRepository;
import com.mepsan.MlbClean.Dto.DeviceDto;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nurbanu.acar
 */
@Service
public class DeviceManager implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public DataResult<List<DeviceDto>> getAllDevice() {

        List<DeviceEntity> devices = deviceRepository.findByDeletedOrderByUpdateTimeDesc(Boolean.FALSE);

        if (!devices.isEmpty()) {
            List<DeviceDto> deviceDtos = new ArrayList<>();
            for (DeviceEntity device : devices) {
                DeviceDto deviceDto = new DeviceDto(device.getId(), device.getName() + device.getFloor(), device.getIp(), device.getFloor(), device.getDeviceName());
                deviceDtos.add(deviceDto);
            }
            return new SuccessDataResult<>("Tüm Cihazlar Başarılı Şekilde Listelenmiştir.", deviceDtos);
        } else {
            return new ErrorDataResult<>("Hiçbir Görev Tanımı Bulunamadı");
        }
    }

    @Override
    public DataResult<DeviceDto> getDeviceById(int id) {

        Optional<DeviceEntity> device = deviceRepository.findById(id);
        DeviceDto deviceDto = new DeviceDto();

        if (device.isPresent()) {
            deviceDto.setId(device.get().getId());
            deviceDto.setName(device.get().getName());
            deviceDto.setIp(device.get().getIp());
            deviceDto.setFloor(device.get().getFloor());
            return new SuccessDataResult<>("Cihaz Bulundu.", deviceDto);
        } else {
            return new ErrorDataResult<>("Cihaz Bulunamadı Bulunamadı");
        }
    }

    @Override
    public DataResult<DeviceDto> getDeviceByDeviceName(String deviceName) {

        Optional<DeviceEntity> device = deviceRepository.findByDeviceName(deviceName);
        DeviceDto deviceDto = new DeviceDto();

        if (device.isPresent()) {
            deviceDto.setId(device.get().getId());
            deviceDto.setName(device.get().getName());
            deviceDto.setIp(device.get().getIp());
            deviceDto.setFloor(device.get().getFloor());
            deviceDto.setDeviceName(device.get().getDeviceName());
            return new SuccessDataResult<>("Cihaz Bulundu.", deviceDto);
        } else {
            return new ErrorDataResult<>("Cihaz Bulunamadı.");
        }
    }

    @Override
    public DataResult<DeviceDto> save(DeviceEntity device, int processId) {

        DeviceDto deviceDto = new DeviceDto();
        device.setCreateId(processId);
        device.setUpdateId(processId);
        device.setUpdateTime(new Date());
        Optional<DeviceEntity> existDevice = deviceRepository.findByDeviceName(device.getDeviceName());
        if (!existDevice.isPresent()) {
            DeviceEntity newDevice = deviceRepository.save(device);
            if (newDevice.getId() > 0) {
                deviceDto.setName(newDevice.getName());
                deviceDto.setId(newDevice.getId());
                deviceDto.setIp(newDevice.getIp());
                deviceDto.setFloor(newDevice.getFloor());
                deviceDto.setDeviceName(newDevice.getDeviceName());
                return new SuccessDataResult<>("Cihaz Oluşturuldu.", deviceDto);
            } else {
                return new ErrorDataResult<>("Cihaz Oluşturulamadı.");
            }
        } else {
            return new ErrorDataResult<>("Aynı İsimli Cihaz Bulunmuştur.");
        }
    }

    @Override
    public void deleteById(int id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public DataResult deleteDevice(int id, int processId) {
        Optional<DeviceEntity> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            device.get().setUpdateId(processId);
            device.get().setUpdateTime(new Date());
            deviceRepository.save(device.get());
            deviceRepository.deleteById(id);
            return new SuccessDataResult<>("Cihaz Başarılı Şekilde Silindi.");
        } else {
            return new ErrorDataResult<>("Cihaz Silinirken Bir Hata Oluştu.");
        }
    }

    @Override
    public DataResult<DeviceDto> update(DeviceDto deviceDto, int id, int updateId) {
        Optional<DeviceEntity> existDevice = deviceRepository.findById(id);
        if (existDevice.isPresent()) {
            DeviceEntity deviceToUpdate = existDevice.get();
            System.out.println("**************** id " + deviceToUpdate.getId());
            if (deviceToUpdate != null) {
                for (Field field : deviceDto.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(deviceDto);
                        if (value != null) {
                            if (field.getType().equals(Optional.class)) {
                                Optional optionalValue = (Optional) value;
                                if (optionalValue.isPresent()) {
                                    Field entityField = DeviceEntity.class.getDeclaredField(field.getName());
                                    entityField.setAccessible(true);
                                    entityField.set(deviceToUpdate, optionalValue.get());
                                }
                            } else {
                                Field entityField = DeviceEntity.class.getDeclaredField(field.getName());
                                entityField.setAccessible(true);
                                entityField.set(deviceToUpdate, value);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                deviceToUpdate.setUpdateId(updateId);
                deviceToUpdate.setUpdateTime(new Date());
                deviceToUpdate.setId(id);
                DeviceEntity updatedDevice = deviceRepository.save(deviceToUpdate);
                if (updatedDevice != null) {
                    DeviceDto newDeviceDto = new DeviceDto();
                    newDeviceDto.setId(updatedDevice.getId());
                    newDeviceDto.setIp(updatedDevice.getIp());
                    newDeviceDto.setName(updatedDevice.getName());
                    newDeviceDto.setFloor(updatedDevice.getFloor());
                    return new SuccessDataResult<>("Cihaz Güncellemesi Başarılı.", newDeviceDto);
                } else {
                    return new ErrorDataResult<>("Cihaz Güncellenemedi.");
                }
            } else {
                return new ErrorDataResult<>("Cihaz Bulunamadı.");
            }
        } else {
            return new ErrorDataResult<>("Cihaz Bulunamadı.");
        }
    }
}
