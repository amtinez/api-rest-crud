package com.amtinez.api.rest.crud.models;

import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Authority;
import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.UsersAuthorities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

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
 * @author amartinezcerro@gmail.com
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Authority.TABLE_NAME)
public class AuthorityModel extends AuditableModel<String> implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Authority.NAME_FIELD, length = Authority.NAME_FIELD_LENGTH)
    private String name;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = UsersAuthorities.TABLE_NAME,
               joinColumns = {@JoinColumn(name = UsersAuthorities.ID_AUTHORITY_FIELD)},
               inverseJoinColumns = {@JoinColumn(name = UsersAuthorities.ID_USER_FIELD)})
    private Set<UserModel> users = new HashSet<>(0);

    @Override
    public String getAuthority() {
        return this.name;
    }

}
