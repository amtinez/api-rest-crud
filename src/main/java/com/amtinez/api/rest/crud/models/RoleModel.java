package com.amtinez.api.rest.crud.models;

import com.amtinez.api.rest.crud.audits.AuditableModel;
import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Role;
import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.UsersRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Role.TABLE_NAME)
public class RoleModel extends AuditableModel<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Role.NAME_FIELD, length = Role.NAME_FIELD_LENGTH)
    private String name;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = UsersRoles.TABLE_NAME,
               joinColumns = {@JoinColumn(name = UsersRoles.ID_ROLE_FIELD)},
               inverseJoinColumns = {@JoinColumn(name = UsersRoles.ID_USER_FIELD)})
    private Set<UserModel> users = new HashSet<>(0);

}
