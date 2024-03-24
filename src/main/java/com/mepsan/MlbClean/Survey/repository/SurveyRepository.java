/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Survey.repository;

import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nurbanu.acar
 */
@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {
    public List<SurveyEntity> findByDeviceId(int deviceId);
   
    public List<SurveyEntity> findAllBySurveyDateBetween(Date startDate, Date endDate);
    
    @Query("SELECT s FROM Survey s WHERE s.starRating <= :starRating")
    public List<SurveyEntity> findByRatingIn(@Param("starRating") int starRating);
}
