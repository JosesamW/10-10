package br.com.ifpe.monitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifpe.monitoramento.entidades.Cidade;
import br.com.ifpe.monitoramento.util.ConnectionFactory;

public class CidadeDao {

	private Connection connection;

	public CidadeDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cidade> listar() {
		try {
			List<Cidade> listarCidade = new ArrayList<Cidade>();
			String sql = "SELECT * FROM estado";
			PreparedStatement stmt = connection.prepareStatement(sql);
			Cidade cidade;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cidade = new Cidade();
				cidade.setUF(rs.getString("uf"));
				
				cidade.setCod_cidade(rs.getInt("id"));
				listarCidade.add(cidade);
			}

			return listarCidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cidade> listar2(int cod_cidade) {
		try {
			List<Cidade> listarCidade = new ArrayList<Cidade>();
			String sql = "SELECT * FROM cidade WHERE estado = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cod_cidade);
			Cidade cidade;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cidade = new Cidade();
				cidade.setNome(rs.getString("nome"));
				cidade.setCod_cidade(rs.getInt("id"));
				listarCidade.add(cidade);
			}

			return listarCidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cidade buscarCidade(int idCidade) {
		try {
			String sql = "SELECT * FROM cidade WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idCidade);
			Cidade cidade = null;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cidade = new Cidade();
				cidade.setNome(rs.getString("nome"));
				cidade.setCod_cidade(rs.getInt("id"));

			}

			return cidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
