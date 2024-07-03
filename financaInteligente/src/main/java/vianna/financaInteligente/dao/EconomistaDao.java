package vianna.financaInteligente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vianna.financaInteligente.model.Economista;

import java.util.List;

public interface EconomistaDao extends JpaRepository<Economista, Integer> {
    public Economista findByNome(String nome);

    public List<Economista> findByValorConsultoria(double valorConsultoria);

    public Economista findByLogin(String login);


}
