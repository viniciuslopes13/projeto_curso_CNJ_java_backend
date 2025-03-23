package br.cnj.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.cnj.projeto.models.CasoJudicial;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner{

	@Autowired
	private CasoJudicial casoJudicial;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		double custo = casoJudicial.finalizarCusto(100, "DF", 2018);
		System.out.println("Custo eh: "+custo);
	}

}
