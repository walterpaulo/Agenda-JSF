package br.com.web.bean;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.web.dao.ContatoDAO;
import br.com.web.modelo.ContatoModel;

@ManagedBean(name = "contatoBean")
@SessionScoped
public class ContatoBean {
	private ContatoModel contato = new ContatoModel();
	private ContatoDAO dao = new ContatoDAO();

	/**
	 * @return the contato
	 */
	public ContatoModel getContato() {
		return contato;
	}

	/**
	 * @param contato the contato to set
	 */
	public void setContato(ContatoModel contato) {
		this.contato = contato;
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void limpaCampos() {
		contato = new ContatoModel();
	}

	public String salvar() {
		try {
			if (contato.getId() == null) {
				Date datacricao = new Date(System.currentTimeMillis());
				contato.setDataCadastro(datacricao);
				dao.salvar(contato);
				limpaCampos();
				exibirMensagem("Inserindo com sucesso!");
			} else {
				dao.alterar(contato);
				limpaCampos();
				exibirMensagem("Alterado com sucesso!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "cadastrarcontato";
	}

	public String excluir() throws ClassNotFoundException {
		try {
			if (contato.getId() == null) {
				exibirMensagem("Erro na excluir");
			} else {
				dao.excluir(contato);
				limpaCampos();
				exibirMensagem("Usuário excluido com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro na operação: " + e.getMessage());
		}
		return "listadecontato";
	}

	public String editar() {
		return "cadastrarcontato";
	}

	public List<ContatoModel> getList() {
		List<ContatoModel> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.list();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			exibirMensagem("Erro na operação: " + e.getMessage());
		}
		//Ordem
		Collections.sort(listaRetorno, (c1,c2) -> c1.getNome().compareTo(c2.getNome()));

		return listaRetorno;
	}

}
