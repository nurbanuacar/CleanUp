/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Task.repository;

import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nurbanu.acar
 */
@Repository
public interface TaskDeviceConRepository extends JpaRepository<TaskDeviceConEntity, Integer> {

    @Override
    public List<TaskDeviceConEntity> findAll();

    public List<TaskDeviceConEntity> findByDeviceId(int deviceId);
    
    public List<TaskDeviceConEntity> findByTaskIdAndDeviceId(int taskId, int deviceId);


//    @Query("SELECT tdc FROM general.task_device_con tdc WHERE tdc.completedate BETWEEN ?1 AND ?2")
//    public List<TaskDeviceConEntity> findAllByTaskDeviceConCompleteDateBetween(Date startDate, Date endDate);
}
