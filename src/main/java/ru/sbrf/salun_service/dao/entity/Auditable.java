package ru.sbrf.muza_service.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<Long> {

    @CreatedBy
    protected Long createdBy;

    @CreatedDate
    protected LocalDateTime createdAt;

    @LastModifiedBy
    protected Long updatedBy;

    @LastModifiedDate
    protected LocalDateTime updatedAt;

    private Boolean isDeleted;

    @PrePersist
    protected void prePersist() {
        createdAt = LocalDateTime.now();
        isDeleted = false;
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
