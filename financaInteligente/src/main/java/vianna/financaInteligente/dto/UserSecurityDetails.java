package vianna.financaInteligente.dto;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vianna.financaInteligente.model.Adm;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;
import vianna.financaInteligente.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {

    private User user;

    public UserSecurityDetails(User user){this.user = user;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> lista = new ArrayList<>();

        if (user instanceof Economista){
            lista.add(new SimpleGrantedAuthority("ROLE_ECONOMISTA"));
        }
        if (user instanceof Poupador){
            lista.add(new SimpleGrantedAuthority("ROLE_POUPADOR"));
        }
        if (user instanceof Adm){
            lista.add(new SimpleGrantedAuthority("ROLE_ADM"));
        }
        lista.add(new SimpleGrantedAuthority("ROLE_USER"));

        return lista;
    }


    @Override
    public String getPassword() {
        return user.getSenha();
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
