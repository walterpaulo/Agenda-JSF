package br.com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.web.crudjdbc.ConectarPostgre;
import br.com.web.modelo.ContatoModel;

public class ContatoDAO {
		
	public void salvar(ContatoModel contato) throws ClassNotFoundException, SQLException {
		
		Connection conexao1 = ConectarPostgre.conectarNoBancoDeDados();
		PreparedStatement ps = null;
		String sql = "INSERT INTO public.td_contato(\n"
				+ "	nome, telefone, email, dat_cad, obs)\n"
				+ "	VALUES (?, ?, ?, ?, ?)";
		try {
			ps = conexao1.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getTelefone());
			ps.setString(3, contato.getEmail());
			ps.setDate(4, contato.getDataCadastro());
			ps.setString(5, contato.getObservacao());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
	System.out.println("Erro ao incluir contato. Mensagem: "+e.getMessage());
		}finally {
			try {
				ps.close();
				conexao1.close();
			} catch(Throwable e) {
				// TODO: handle exception
				System.out.println("Erro ao fechar opereções de inserção: "+e.getMessage());
			}
		}
		
	}

	public void alterar(ContatoModel contato) throws ClassNotFoundException, SQLException {
		Connection conexao1 = ConectarPostgre.conectarNoBancoDeDados();
		PreparedStatement ps = null;
		String sql = "UPDATE public.td_contato\n"
				+ "	SET nome=?, telefone=?, email=?, obs=?\n"
				+ "	WHERE id=?";
		try {
			ps = conexao1.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getTelefone());
			ps.setString(3, contato.getEmail());
			ps.setString(4, contato.getObservacao());
			ps.setInt(5, contato.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
	System.out.println("Erro ao alterar contato. Mensagem: "+e.getMessage());
		}finally {
			try {
				ps.close();
				conexao1.close();
			} catch(Throwable e) {
				// TODO: handle exception
				System.out.println("Erro ao fechar opereções de inserção: "+e.getMessage());
			}
		}
	}

	public void excluir(ContatoModel contato) throws ClassNotFoundException, SQLException {
		Connection conexao1 = ConectarPostgre.conectarNoBancoDeDados();
		PreparedStatement ps = null;
		String sql = "DELETE FROM public.td_contato\n"
				+ "	WHERE id=?";
		try {
			ps = conexao1.prepareStatement(sql);
			ps.setInt(1, contato.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
	System.out.println("Erro ao excluir contato. Mensagem: "+e.getMessage());
		}finally {
			try {
				ps.close();
				conexao1.close();
			} catch(Throwable e) {
				// TODO: handle exception
				System.out.println("Erro ao fechar opereções de inserção: "+e.getMessage());
			}
		}
	}

	public List<ContatoModel> list() throws ClassNotFoundException, SQLException {
		Connection conexao = ConectarPostgre.conectarNoBancoDeDados();
		List<ContatoModel> contatos = new ArrayList<>(); 
		Statement consulta = null;
		ResultSet resultado = null;
		ContatoModel contato = null;
		String sql = "SELECT id, nome, telefone, email, dat_cad, obs\n"
				+ "	FROM public.td_contato;";
		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while(resultado.next()) {
				contato = new ContatoModel();
				contato.setId(resultado.getInt("id"));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dat_cad"));
				contato.setObservacao(resultado.getString("obs"));
				contatos.add(contato);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
	System.out.println("Erro ao busca contato. Mensagem: "+e.getMessage());
		}finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch(Throwable e) {
				// TODO: handle exception
				System.out.println("Erro ao fechar opereções de inserção: "+e.getMessage());
			}
		}
		return contatos;
	}

}
