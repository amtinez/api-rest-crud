package com.amtinez.api.rest.crud.models;

import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.User;
import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.UsersAuthorities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import static com.amtinez.api.rest.crud.constants.SecurityConstants.INACTIVE_LIFETIME_MONTHS;
import static com.amtinez.api.rest.crud.constants.SecurityConstants.PASSWORD_LIFETIME_MONTHS;

/**
 * @author amartinezcerro@gmail.com
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = User.TABLE_NAME)
public class UserModel extends AuditableModel<String> implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = User.FIRST_NAME_FIELD, length = User.FIRST_NAME_FIELD_LENGTH, nullable = false)
    private String firstName;

    @Column(name = User.LAST_NAME_FIELD, length = User.LAST_NAME_FIELD_LENGTH, nullable = false)
    private String lastName;

    @Column(name = User.EMAIL_FIELD, length = User.EMAIL_FIELD_LENGTH, nullable = false, unique = true)
    private String email;

    @Column(name = User.PASSWORD_FIELD, length = User.PASSWORD_FIELD_LENGTH, nullable = false)
    private String password;

    @Column(name = User.BIRTHDAY_DATE_FIELD, nullable = false)
    private LocalDateTime birthdayDate;

    //TODO: IMPLEMENT THE BLOCKING REASON AND WHO BLOCKED IT
    @Column(name = User.LOCKED_AT_FIELD)
    private LocalDateTime lockedDate;

    @Column(name = User.LAST_ACCESS_DATE_FIELD)
    private LocalDateTime lastAccessDate;

    @Column(name = User.LAST_PASSWORD_UPDATE_DATE_FIELD)
    private LocalDateTime lastPasswordUpdateDate;

    @Column(name = User.ENABLED_FIELD, nullable = false)
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = UsersAuthorities.TABLE_NAME,
               joinColumns = {@JoinColumn(name = UsersAuthorities.ID_USER_FIELD)},
               inverseJoinColumns = {@JoinColumn(name = UsersAuthorities.ID_AUTHORITY_FIELD)})
    private Set<AuthorityModel> authorities = new HashSet<>(0);

    @Override
    public Set<AuthorityModel> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return ChronoUnit.MONTHS.between(this.lastAccessDate, LocalDateTime.now()) < INACTIVE_LIFETIME_MONTHS;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.lockedDate == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return ChronoUnit.MONTHS.between(this.lastPasswordUpdateDate, LocalDateTime.now()) < PASSWORD_LIFETIME_MONTHS;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
