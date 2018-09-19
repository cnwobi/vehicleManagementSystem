package com.chukanwobi.vehiclemanagementsystem.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Getter
@Data
@Entity
public class Staff implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @Setter(AccessLevel.NONE)
    private Calendar dateJoined;
    @Setter(AccessLevel.NONE)
    private Calendar dateFinished;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


          List <GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.toString())));
        return grantedAuthorities;

    }

    public Staff() {

        this.roles.add(Role.ROLE_STAFF);
        isAccountNonExpired= true;
        isAccountNonLocked=true;
        isCredentialsNonExpired=true;
        isEnabled= true;
        dateJoined= Calendar.getInstance();
    }


}
