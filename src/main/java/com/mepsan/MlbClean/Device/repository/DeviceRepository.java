/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Device.repository;

import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nurbanu.acar
 */
@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {

    public Optional<DeviceEntity> findByDeviceName(String deviceName);

    public List<DeviceEntity> findByDeleted(Boolean deleted);

    public List<DeviceEntity> findByDeletedOrderByUpdateTimeDesc(Boolean deleted);

}
