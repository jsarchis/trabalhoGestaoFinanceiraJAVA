package vianna.financaInteligente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesFechado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeFechamento;

    @ManyToOne
    private Poupador poupador;

    @ManyToOne
    private Economista economista;

    private double rendaFixaMes, despesaFixaMes, gastoTotal, ganhoTotal, rendaFlutuante, gastoFlutuante, saldoMes, metaMes;

    public void setRendaFlutuante() {
        this.rendaFlutuante = ganhoTotal - rendaFixaMes;
    }

    public void setGastoFlutuante() {
        this.gastoFlutuante = gastoTotal - despesaFixaMes;
    }

    public void setSaldoMes() {
        this.saldoMes = ganhoTotal - gastoTotal;
    }
}
