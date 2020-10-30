package ru.sbrf.muza_service.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "E_USER")
@Data
@EqualsAndHashCode(callSuper = true)
public class EUser extends Auditable<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "E_USER_ID_SEQ")
    @SequenceGenerator(name = "E_USER_ID_SEQ", sequenceName = "E_USER_ID_SEQ", allocationSize = 1)
    private Long  id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String midName;
    private String ip;
    private Date lastSign;

    private Boolean isTech;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "E_USER_ROLE",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
    )
    private List<ERole> roles;
}
