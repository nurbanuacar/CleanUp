/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Task.repository;

import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
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

    public List<TaskDeviceConEntity> findByFrequency(int frequency);

    public List<TaskDeviceConEntity> findByIsCheck(boolean isCheck);

    public List<TaskDeviceConEntity> findByBeginDateBetween(Date startDate, Date endDate);

    //BURAYA CALISACAK BİR SORGU OLUSTURAMADIM
//    @Query(value = "SELECT new com.mepsan.MlbClean.Dto.TaskDeviceConDto(tdc.id, tdc.completedate, tdc.device_id, tdc.is_check, tdc.task_id, tdc.user_id, tdc.frequency, tdc.frequency_array) FROM general.task_device_con tdc WHERE tdc.completedate BETWEEN ?1 AND ?2", nativeQuery = true)
//    public List<TaskDeviceConDto> findAllByTaskDeviceConCompleteDateBetween(Date startDate, Date endDate);
}
