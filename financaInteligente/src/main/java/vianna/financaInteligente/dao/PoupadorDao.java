package vianna.financaInteligente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;

import java.util.List;

public interface PoupadorDao extends JpaRepository<Poupador, Integer> {

    public Poupador findByNome(String nome);

    public Poupador findByNeedHelp(boolean needHelp);

    public Poupador findByLogin(String login);

    public List<Poupador> findAllByEconomista(Economista economista);


}
