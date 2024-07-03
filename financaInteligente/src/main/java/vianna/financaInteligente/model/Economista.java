package vianna.financaInteligente.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Economista extends User{

    private double valorConsultoria;

    @OneToMany(mappedBy = "economista", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Poupador> poupadores;

    @OneToMany(mappedBy = "economista")
    private List<MesFechado> mesesFechados;

    private int avalPositivas, avalNegativas;

    @OneToMany(mappedBy = "economista", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ComunicacaoPoupEcon> mensagens;
}
