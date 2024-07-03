package vianna.financaInteligente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vianna.financaInteligente.dao.MesFechadoDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.dto.UserSecurityDetails;
import vianna.financaInteligente.model.Adm;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;

import java.util.Optional;

@Service
public class PoupadorService implements UserDetailsService {

    @Autowired
    PoupadorDao poup;

    @Autowired
    MesFechadoDao mes;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Poupador p = poup.findByLogin(username);
        if (p != null) {
            return new UserSecurityDetails(p);
        }else{
            throw new UsernameNotFoundException("usuário não encontrado");
        }
    }

    public Poupador findByLoginName(String username) throws UsernameNotFoundException {

        Poupador p = poup.findByLogin(username);
        return p;
    }

    public void fechaMes(Integer id, Poupador poupador){
        Optional<Poupador> pAtt = poup.findById(id);
        if(pAtt.isPresent()){
            Poupador p = pAtt.get();
            mes.save(p.fechaMes());
            poup.save(p);
            System.out.println("sucess mesfechado");
        } else {
            System.out.println("ErroMesFechado");
            poup.save(poupador);
        }
    }

    public void updatePoupador(Integer id, Poupador poupador){
        Optional<Poupador> pAtt = poup.findById(id);

        if(pAtt.isPresent()){
            Poupador p = pAtt.get();
            p.setDespesasFixas(poupador.getDespesasFixas());
            p.setMetaDePoupanca(poupador.getMetaDePoupanca());
            p.setRendaFixa(poupador.getRendaFixa());
            p.setGanhoMes(poupador.getGanhoMes());
            p.setGastoMes(poupador.getGastoMes());
            poup.save(p);
        } else {
            poup.save(poupador);
        }
    }








}
