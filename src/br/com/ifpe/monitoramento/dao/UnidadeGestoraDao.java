package br.com.ifpe.monitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.ifpe.monitoramento.entidades.Situacao;
import br.com.ifpe.monitoramento.entidades.UnidadeGestora;
import br.com.ifpe.monitoramento.util.ConnectionFactory;

public class UnidadeGestoraDao {
	private Connection connection;

	public UnidadeGestoraDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void cadastrarUG(UnidadeGestora ug) throws ChaveduplicadaException {

		try {
			String sql = "INSERT INTO unidade_gestora (nome_unidade, codigo_unidade) VALUES (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, ug.getNome());
			stmt.setInt(2, ug.getCodigo());
			stmt.execute();
			connection.close();

		} catch (SQLException e) {
			if (e instanceof MySQLIntegrityConstraintViolationException) {
				throw new ChaveduplicadaException(e);
			}
			throw new RuntimeException(e);
		}
	}

	public List<UnidadeGestora> listarUG(String nome, String codigo) {
		try {
			List<UnidadeGestora> listarUG = new ArrayList<UnidadeGestora>();

			String sql;
			PreparedStatement stmt = null;

			if ((nome != null && !nome.equals("")) && (codigo == null || codigo.equals(""))) {

				sql = "SELECT * FROM unidade_gestora WHERE nome_unidade LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nome + "%");

			} else if ((nome == null || nome.equals("")) && (codigo != null && !codigo.equals(""))) {

				sql = "SELECT * FROM unidade_gestora WHERE codigo_unidade LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + codigo + "%");

			} else if ((nome != null && !nome.equals("")) && (codigo != null && !codigo.equals(""))) {

				sql = "SELECT * FROM unidade_gestora WHERE nome_unidade LIKE ? AND codigo_unidade LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nome + "%");
				stmt.setString(2, "%" + codigo + "%");

			} else {

				sql = "SELECT * FROM unidade_gestora ORDER BY codigo_unidade desc";
				stmt = this.connection.prepareStatement(sql);
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UnidadeGestora ug = new UnidadeGestora();
				ug.setNome(rs.getString("nome_unidade"));
				ug.setCodigo(rs.getInt("codigo_unidade"));
				ug.setData(rs.getDate("data_cadastro"));

				Situacao st = Situacao.valueOf(rs.getString("Status"));
				ug.setSituacao(st);

				listarUG.add(ug);
			}
			rs.close();
			stmt.close();
			connection.close();
			return listarUG;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public UnidadeGestora exibirUG(int codigo) {

		try {
			UnidadeGestora ug = null;

			String sql = "SELECT * FROM unidade_gestora WHERE codigo_unidade = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, codigo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ug = new UnidadeGestora();
				ug.setCodigo(rs.getInt("codigo_unidade"));
				ug.setNome(rs.getString("nome_unidade"));

				Situacao st = Situacao.valueOf(rs.getString("Status"));
				ug.setSituacao(st);
			}

			rs.close();
			stmt.close();
			connection.close();

			return ug;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterarUG(UnidadeGestora ug) {

		try {

			String sql = "UPDATE unidade_gestora SET nome_unidade=? ,STATUS = ? WHERE codigo_unidade=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, ug.getNome());
			stmt.setString(2, ug.getSituacao().name());
			stmt.setInt(3, ug.getCodigo());
			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void historicoalteracaoUG(UnidadeGestora ug) {

		try {

			String sql = "INSERT INTO historico (IdUsuarioAutor , Campo) VALUES (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, ug.getUsuarioId());
			ug.setCampo(ug.toString());
			stmt.setString(2, ug.getCampo());
			stmt.execute();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
