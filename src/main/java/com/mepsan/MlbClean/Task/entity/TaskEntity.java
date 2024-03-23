/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.entity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author nurbanu.acar
 */
@Entity
@Table(name = "task", schema = "general")
@SQLDelete(sql = "UPDATE general.task SET deleted = true, d_time=now() WHERE id=?")
@Where(clause = "deleted=false")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(100)")
    private String name;
    @Column(name = "c_id")
    private Integer createId;
    @Column(name = "c_time", columnDefinition = "timestamp default now()")
    private Date createTime;
    @Column(name = "u_id")
    private Integer updateId;
    @Column(name = "u_time", columnDefinition = "timestamp default now()")
    private Date updateTime;
    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted = Boolean.FALSE;
    @Column(name = "d_time")
    private Date deleteTime;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskDeviceConEntity> taskDeviceCons;

    public TaskEntity() {
    }

    public TaskEntity(String name, int createId, Date createTime, int updateId, Date updateTime, boolean deleted, Date deleteTime) {
        this.name = name;
        this.createId = createId;
        this.createTime = new Date();
        this.updateId = updateId;
        this.updateTime = new Date();
        this.deleted = deleted;
        this.deleteTime = new Date();
    }

    @PrePersist
    protected void onCreate() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional getCreateId() {
        return Optional.ofNullable(createId);
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Optional getUpdateId() {
        return Optional.ofNullable(updateId);
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

}
