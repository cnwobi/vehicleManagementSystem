package com.chukanwobi.vehiclemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    private Role role;
    private Calendar dateJoined;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    @OneToOne
    private Vehicle currentlyHiring;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HireTransaction> hireTransactions = new HashSet<>();

    public Customer() {
        this.role = Role.ROLE_CUSTOMER;
        isAccountNonExpired= true;
        isAccountNonLocked=true;
        isCredentialsNonExpired=true;
        isEnabled= true;

    }

    public Customer(String firstName, String lastName, String email,String username) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = Role.ROLE_CUSTOMER;
        this.username=username;
        dateJoined= Calendar.getInstance();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return AuthorityUtils.createAuthorityList(role.toString());
    }


}
