package vianna.financaInteligente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vianna.financaInteligente.dao.AdmDao;
import vianna.financaInteligente.dao.EconomistaDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.model.Adm;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;

import java.util.Date;

@SpringBootApplication
public class FinancaInteligenteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FinancaInteligenteApplication.class, args);
	}

	@Autowired
	AdmDao adm;

	@Autowired
	PoupadorDao poup;

	@Autowired
	EconomistaDao ec;

	@Bean
	public PasswordEncoder pass(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("#### SERVER STARTER ####");
//
//		Adm a = new Adm();
//
//		a.setNome("Administrador");
//		a.setLogin("adm123");
//		a.setSenha(pass().encode("adm123"));
//
//		adm.save(a);

//		Economista e = new Economista();
//		e.setNome("Econ");
//		e.setLogin("Econ");
//		e.setSenha(pass().encode("123"));
//
//		ec.save(e);
//
//		Poupador p = new Poupador();
//		p.setNome("ZezinPoupado");
//		p.setLogin("zezin");
//		p.setSenha(pass().encode("123"));


//
//		ec.save(e);
//		poup.save(p);

	}

}
