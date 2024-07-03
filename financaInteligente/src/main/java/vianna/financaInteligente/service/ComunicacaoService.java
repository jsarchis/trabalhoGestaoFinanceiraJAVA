package vianna.financaInteligente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vianna.financaInteligente.dao.ComunicacaoPoupEconDao;
import vianna.financaInteligente.dao.EconomistaDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.model.ComunicacaoPoupEcon;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;

import java.util.Optional;

@Service
public class ComunicacaoService implements UserDetailsService {

    @Autowired
    EconomistaDao ec;

    @Autowired
    PoupadorDao pp;

    @Autowired
    ComunicacaoPoupEconDao msg;

    public ComunicacaoPoupEcon criarMsg(Integer idEcon, Integer idPoup) {
        ComunicacaoPoupEcon newMsg = new ComunicacaoPoupEcon();
        Optional<Economista> e = ec.findById(idEcon);
        Optional<Poupador> p = pp.findById(idPoup);
        if(e.isPresent() && p.isPresent()){
            newMsg.setEconomista(e.get());
            newMsg.setPoupador(p.get());
        }
        return newMsg;
    }

    public void msgToEcon(ComunicacaoPoupEcon msgSend){
        msg.save(msgSend);
        System.out.println("SALVOU");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
