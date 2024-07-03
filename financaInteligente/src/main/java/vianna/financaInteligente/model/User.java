package vianna.financaInteligente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome,email,senha;

    @Column(unique = true, length = 25, nullable = false)
    private String login;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimoAcesso;
}
