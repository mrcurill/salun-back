package ru.sbrf.salun_service.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "E_ROLE")
@Data
@EqualsAndHashCode(callSuper = true)
public class ERole extends Auditable<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "E_ROLE_ID_SEQ")
    @SequenceGenerator(name = "E_ROLE_ID_SEQ", sequenceName = "E_ROLE_ID_SEQ", allocationSize = 1)
    private Long id;

    private String name;

}
