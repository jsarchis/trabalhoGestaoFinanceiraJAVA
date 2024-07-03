package vianna.financaInteligente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosUserDTO {

    private int id;
    private String nome, email, login, senha, tipo;
}
