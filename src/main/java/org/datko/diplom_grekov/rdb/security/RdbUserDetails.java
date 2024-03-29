package org.datko.diplom_grekov.rdb.security;

import lombok.Getter;
import lombok.Setter;
import org.datko.diplom_grekov.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@Getter
@Setter
public class RdbUserDetails implements UserDetails {

    // выгруженный из БД пользователь
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections
                .<GrantedAuthority>singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
