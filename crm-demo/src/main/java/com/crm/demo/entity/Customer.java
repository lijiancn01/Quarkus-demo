package com.crm.demo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer extends PanacheEntity {
    
    @NotBlank(message = "姓名不能为空")
    @Size(max = 100, message = "姓名长度不能超过100")
    @Column(nullable = false, length = 100)
    public String name;
    
    @Email(message = "邮箱格式不正确")
    @Column(length = 100)
    public String email;
    
    @Size(max = 20, message = "电话长度不能超过20")
    @Column(length = 20)
    public String phone;
    
    @Size(max = 200, message = "公司名称长度不能超过200")
    @Column(length = 200)
    public String company;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    public CustomerStatus status = CustomerStatus.ACTIVE;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
