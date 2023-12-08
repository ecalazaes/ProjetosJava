package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contato {
	private int id;
	private String nome;
	private String email;
	private String telefone;

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean cadastrarContato(int numId, String nome, String email, String telefone) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			
			String sql = "insert into contatos set id=?, nome=?, email=?, telefone=?;";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, numId); // Substitui o primeiro parâmetro da consulta pela id informado
			ps.setString(2, nome); // Substitui o segundo parâmetro da consulta pela nome informado
			ps.setString(3, email); // Substitui o terceiro parâmetro da consulta pelo email informado
			ps.setString(4, telefone); // Substitui o quarto parâmetro da consulta pelo telefone informado
			
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar a contato: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarContato(int numId) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "select * from contatos where id=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numId); 
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				System.out.println("Contato não cadastrado!");
				return false;
			} else {
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nome = rs.getString("nome");
					this.email = rs.getString("email");
					this.telefone = rs.getString("telefone");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a contato: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarContato(int numId, String nome, String email, String telefone) {
		if (!consultarContato(numId))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				
				String sql = "update contatos set nome=?, email=?, telefone=? where id=?";
				
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				ps.setString(1, nome);
				ps.setString(2, email);
				ps.setString(3, telefone);
				ps.setInt(4, numId);
				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar a contato: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
	public boolean deletarContato(int numId) {
	    Connection conexao = null;
	    try {
	        conexao = Conexao.conectaBanco();
	        String sql = "DELETE FROM contatos WHERE id=?";
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, numId);
	        
	        int linhasAfetadas = ps.executeUpdate();
	        if (linhasAfetadas == 0) {
	            System.out.println("Contato não encontrado para exclusão.");
	            return false; // Contato não encontrado para exclusão
	        } else {
	            System.out.println("Contato excluído com sucesso!");
	            return true; // Contato excluído com sucesso
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao excluir a contato: " + erro.toString());
	        return false;
	    } finally {
	        Conexao.fechaConexao(conexao);
	    }
	}
}