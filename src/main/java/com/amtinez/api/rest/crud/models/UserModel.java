package com.amtinez.api.rest.crud.models;

import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.User;
import com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.UsersAuthorities;
import lombok.Getter;
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

import static com.amtinez.api.rest.crud.constants.DatabaseConstants.DATABASE_NAME;
import static com.amtinez.api.rest.crud.constants.SecurityConstants.INACTIVE_LIFETIME_MONTHS;
import static com.amtinez.api.rest.crud.constants.SecurityConstants.PASSWORD_LIFETIME_MONTHS;

/**
 * @author amartinezcerro@gmail.com
 */
@Getter
@Setter
@Entity
@Table(name = User.TABLE_NAME,
       catalog = DATABASE_NAME)
public class UserModel implements UserDetails {

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

    @Column(name = User.REGISTER_DATE_FIELD, nullable = false)
    private LocalDateTime registerDate;

    @Column(name = User.DELETE_DATE_FIELD)
    private LocalDateTime deleteDate;

    @Column(name = User.LAST_ACCESS_DATE_FIELD, nullable = false)
    private LocalDateTime lastAccessDate;

    @Column(name = User.LAST_UPDATE_DATE_FIELD)
    private LocalDateTime lastUpdateDate;

    @Column(name = User.PASSWORD_UPDATE_DATE_FIELD, nullable = false)
    private LocalDateTime passwordUpdateDate;

    @Column(name = User.ENABLED_FIELD, nullable = false)
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = UsersAuthorities.TABLE_NAME, catalog = DATABASE_NAME, joinColumns = {
        @JoinColumn(name = UsersAuthorities.ID_USER_FIELD, nullable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = UsersAuthorities.ID_AUTHORITY_FIELD, nullable = false, updatable = false)})
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
        return this.deleteDate == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return ChronoUnit.MONTHS.between(this.passwordUpdateDate, LocalDateTime.now()) < PASSWORD_LIFETIME_MONTHS;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
