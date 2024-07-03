package vianna.financaInteligente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vianna.financaInteligente.model.Adm;

public interface AdmDao extends JpaRepository<Adm, Integer> {

    public Adm findByLogin(String login);
}
