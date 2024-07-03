package vianna.financaInteligente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vianna.financaInteligente.dao.AdmDao;
import vianna.financaInteligente.dao.EconomistaDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.dto.DadosUserDTO;
import vianna.financaInteligente.dto.UserSecurityDetails;
import vianna.financaInteligente.model.Adm;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    EconomistaDao ec;

    @Autowired
    PoupadorDao poup;

    @Autowired
    AdmDao ad;

    @Autowired
    PasswordEncoder pass;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Economista e = ec.findByLogin(username);

        if (e != null){
            return new UserSecurityDetails(e);
        }else{
            Poupador p = poup.findByLogin(username);
            if (p != null){
                return new UserSecurityDetails(p);
            }else{
                Adm a = ad.findByLogin(username);
                if(a != null){
                    return new UserSecurityDetails(a);
                }else{
                throw new UsernameNotFoundException("usuário não encontrado");
            }
        }

        }
    }

    public void salvarUser(DadosUserDTO dados) throws Exception{
        if(dados.getTipo().equals("poupador")){
            Poupador p = new Poupador();
            p.setNome(dados.getNome());
            p.setEmail(dados.getEmail());
            p.setLogin(dados.getLogin());
            p.setSenha(pass.encode(dados.getSenha()));
            System.out.println("TENTOU POUPADOR");
            poup.save(p);
        }else if(dados.getTipo().equals("economista")){
            Economista e = new Economista();
            e.setNome(dados.getNome());
            e.setEmail(dados.getEmail());
            e.setLogin(dados.getLogin());
            e.setSenha(pass.encode(dados.getSenha()));
            System.out.println("TENTOU ECONOMISTA");
            ec.save(e);
        } else{
            throw new Exception("tipo user invalido");
        }
        System.out.println("NAO TENHTOU NADA");
    }
}
