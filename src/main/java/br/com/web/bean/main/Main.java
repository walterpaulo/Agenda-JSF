package br.com.web.bean.main;

import java.sql.Date;
import java.sql.SQLException;

import br.com.web.dao.ContatoDAO;
import br.com.web.modelo.ContatoModel;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
			ContatoDAO dao = new ContatoDAO();
			ContatoModel walter = new ContatoModel();
			walter.setNome("Walter");
			walter.setTelefone("9999999");
			walter.setEmail("walter@teste.com");
			walter.setDataCadastro(new Date(System.currentTimeMillis()));
			walter.setObservacao("Novo Cliente");
			dao.salvar(walter);
			ContatoModel kely = new ContatoModel();
			kely.setNome("kely");
			kely.setTelefone("9999998");
			kely.setEmail("kely@teste.com");
			kely.setDataCadastro(new Date(System.currentTimeMillis()));
			kely.setObservacao("Novo Cliente -  possivel cliente");
			dao.salvar(kely);
			
			System.out.println("Contatos cadastrados: "+dao.list().size());
		
	}


}
