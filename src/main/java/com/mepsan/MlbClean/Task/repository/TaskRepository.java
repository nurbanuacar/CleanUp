/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Task.repository;

import com.mepsan.MlbClean.Task.entity.TaskEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nurbanu.acar
 */
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    public List<TaskEntity> findByDeleted(Boolean deleted);
    
}
