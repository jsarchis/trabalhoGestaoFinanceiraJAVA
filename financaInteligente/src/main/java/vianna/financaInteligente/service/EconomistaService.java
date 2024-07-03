package vianna.financaInteligente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vianna.financaInteligente.dao.EconomistaDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.dto.UserSecurityDetails;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;

import java.util.List;
import java.util.Optional;

@Service
public class EconomistaService implements UserDetailsService {

    @Autowired
    EconomistaDao ec;

    @Autowired
    PoupadorDao pp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Economista e = ec.findByLogin(username);
        if (e != null) {
            return new UserSecurityDetails(e);
        }else{
            throw new UsernameNotFoundException("usuário não encontrado");
        }
    }

    public Economista findEconomistaCompleto(Integer id) {
        Optional<Economista> econ = ec.findById(id);
        Economista e = new Economista();
        if (econ.isPresent()) {
            e = econ.get();
            e.setPoupadores(pp.findAllByEconomista(e));
            System.out.println(e.getPoupadores().size());
        }
        return e;

    }
}
