package com.pythonstrup.demo.security.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDto implements UserDetails {

    private int id;
    private String password;
    private String role;

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return String.valueOf(id);
    }

    @Override
    public boolean isEnabled(){
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
