package vianna.financaInteligente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vianna.financaInteligente.model.ComunicacaoPoupEcon;

public interface ComunicacaoPoupEconDao extends JpaRepository<ComunicacaoPoupEcon, Integer> {
}
