package vianna.financaInteligente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poupador extends User{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "economista_id")
    private Economista economista;

    private double rendaFixa, despesasFixas, metaDePoupanca, gastoMes, ganhoMes;

    private boolean needHelp;

    @OneToMany(mappedBy = "poupador")
    private List<MesFechado> mesesFechados;

    @OneToOne(mappedBy = "poupador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ComunicacaoPoupEcon mensagem;

    public MesFechado fechaMes(){
        MesFechado m = new MesFechado();
        m.setRendaFixaMes(this.rendaFixa);
        m.setDespesaFixaMes(this.despesasFixas);
        m.setGanhoTotal(this.ganhoMes);
        m.setGastoTotal(this.gastoMes);
        this.gastoMes = this.despesasFixas;
        this.ganhoMes = this.rendaFixa;
        m.setGastoFlutuante();
        m.setRendaFlutuante();
        m.setMetaMes(metaDePoupanca);
        m.setSaldoMes();
        m.setPoupador(this);
        m.setEconomista(economista);
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        m.setDataDeFechamento(date);
        mesesFechados.add(m);
        this.rendaFixa = 0;
        this.despesasFixas = 0;
        this.ganhoMes = 0;
        this.gastoMes = 0;
        return m;
    }
}
