package vianna.financaInteligente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComunicacaoPoupEcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mensagemEconomista, mensagemPoupador;

    @ManyToOne
    private Economista economista;

    @OneToOne
    private Poupador poupador;
}
