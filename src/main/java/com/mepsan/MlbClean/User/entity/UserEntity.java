/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.User.entity;

import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "user", schema = "general")
@SQLDelete(sql = "UPDATE general.user SET deleted = true, d_time=now() WHERE id=?")
@Where(clause = "deleted=false")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(100)")
    private String name;
    @Column(columnDefinition = "varchar(100)")
    private String surname;
    @Column(columnDefinition = "varchar(20)")
    private String username;
    @Column
    private String password;
    @Column(name = "is_admin", columnDefinition = "boolean default false")
    private boolean isAdmin = Boolean.FALSE;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskDeviceConEntity> taskDeviceCons;

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String username, String password, boolean isAdmin, int createId, Date createTime, int updateId, Date updateTime, boolean deleted, Date deleteTime) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.createId = createId;
        this.createTime = new Date();
        this.updateId = updateId;
        this.updateTime = new Date();
        this.deleted = deleted;
        this.deleteTime = new Date();
    }
    
        public UserEntity(String name, String surname, String username, String password, boolean isAdmin, int updateId, Date updateTime) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.updateId = updateId;
        this.updateTime = new Date();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String fullName(String name, String surname) {
        return name + " " + surname;
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
