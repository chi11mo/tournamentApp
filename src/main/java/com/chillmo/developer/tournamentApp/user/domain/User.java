package com.chillmo.developer.tournamentApp.user.domain;


import java.util.Collection;
import java.util.Collections;


import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
               //@UniqueConstraint(columnNames = "twitch"),
                @UniqueConstraint(columnNames = "eMail")
        })
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String twitch;
    private String eMail;
    private String discord;
    private String psn;
    private String steam;
    private String imgUrl;
    private Boolean locked = false;
    private Boolean enabled = false;


    @Lob
    @Column
    private String password;

    /**
     * @ManyToMany(fetch = FetchType.LAZY)
     * @JoinTable(name = "user_roles",
     * joinColumns = @JoinColumn(name = "user_id"),
     * inverseJoinColumns = @JoinColumn(name = "role_id"))
     * private List<Role> roles = new ArrayList<>();
     **/
    @Enumerated(EnumType.STRING)
    private Role role;


    public User() {

    }

    public User(String twitch, String eMail, String discord, String psn, String steam, Boolean locked, Boolean enabled, String password, Role role) {
        this.twitch = twitch;
        this.eMail = eMail;
        this.discord = discord;
        this.psn = psn;
        this.steam = steam;
        this.locked = false;
        this.enabled = false;
        this.password = password;
        this.role = Role.USER;
    }

    public User(String twitch, String eMail, String discord, String psn, String steam, String password) {
        this.twitch = twitch;
        this.eMail = eMail;
        this.discord = discord;
        this.psn = psn;
        this.steam = steam;
        this.password = password;
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return twitch;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
