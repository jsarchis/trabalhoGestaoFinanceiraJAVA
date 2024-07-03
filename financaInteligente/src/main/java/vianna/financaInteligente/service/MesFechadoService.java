package vianna.financaInteligente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vianna.financaInteligente.dao.MesFechadoDao;

@Service
public class MesFechadoService{
    @Autowired
    MesFechadoDao mes;

    public void carregaMesById(Integer i){

    }

}
