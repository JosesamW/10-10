package br.com.ifpe.monitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifpe.monitoramento.entidades.Cargo;
import br.com.ifpe.monitoramento.entidades.Cidade;
import br.com.ifpe.monitoramento.entidades.SugestaoDiaria;
import br.com.ifpe.monitoramento.entidades.UnidadeGestora;
import br.com.ifpe.monitoramento.util.ConnectionFactory;

public class SugestaoDiariaDao {
	private Connection connection;

	public SugestaoDiariaDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void cadastrarSD(SugestaoDiaria sd) {
		try {
			String sql = "INSERT INTO sugestaovalordiaria (IdCidadeOrigem,IdCidadeDestino,IdUg,Valor,IdCargo)"
					+ " VALUES (?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, sd.getOrigem().getCod_cidade());
			stmt.setInt(2, sd.getDestino().getCod_cidade());
			stmt.setInt(3, sd.getUg().getCodigo());
			stmt.setString(4, sd.getValores());
			stmt.setInt(5, sd.getCargo().getId());
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<SugestaoDiaria> listar(String Valor) {
		try {

			List<SugestaoDiaria> listar = new ArrayList<SugestaoDiaria>();
			String sql;
			PreparedStatement stmt = null;
			SugestaoDiaria sd;

			if (Valor != null && !Valor.equals("")) {
				sql = "SELECT * FROM sugestaovalordiaria WHERE Valor LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + Valor + "%");

			} else {
				sql = "SELECT * FROM sugestaovalordiaria";
				stmt = this.connection.prepareStatement(sql);
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sd = new SugestaoDiaria();
				sd.setIdSD(rs.getInt("IdSugestao"));

				CidadeDao dao3 = new CidadeDao();
				Cidade origem = dao3.buscarCidade(rs.getInt("idCidadeOrigem"));
				sd.setOrigem(origem);

				Cidade destino = dao3.buscarCidade(rs.getInt("idCidadeDestino"));
				sd.setDestino(destino);
				sd.setValores(rs.getString("Valor"));

				CargoDao dao = new CargoDao();
				Cargo cargo = dao.exibirCargo(rs.getInt("IdCargo"));
				sd.setCargo(cargo);

				UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
				UnidadeGestora ug = dao2.exibirUG(rs.getInt("IdUg"));
				sd.setUg(ug);

				listar.add(sd);

			}

			return listar;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<SugestaoDiaria> listarValor(int origem, int destino, int idUg, int idCargo) { // Listar
		// valor
		// sugestao
		// em
		// Solicitar
		// diaria
		try {
			List<SugestaoDiaria> listarValor = new ArrayList<SugestaoDiaria>();
			String sql = "SELECT Valor FROM sugestaovalordiaria WHERE IdCidadeOrigem = ? AND IdCidadeDestino = ? AND IdUg = ? AND IdCargo = ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, origem);
			stmt.setInt(2, destino);
			stmt.setInt(3, idUg);
			stmt.setInt(4, idCargo);
			SugestaoDiaria SugestaoDiaria;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SugestaoDiaria = new SugestaoDiaria();
				SugestaoDiaria.setValores(rs.getString("Valor"));
				listarValor.add(SugestaoDiaria);
			}

			return listarValor;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public SugestaoDiaria exibirSugestao(int idSugestao) {
		try {
			String sql = "SELECT * FROM sugestaovalordiaria WHERE IdSugestao = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			SugestaoDiaria sugestao = null;
			stmt.setInt(1, idSugestao);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sugestao = new SugestaoDiaria();

				CargoDao dao = new CargoDao();
				Cargo cargo = dao.exibirCargo(rs.getInt("IdCargo"));
				sugestao.setCargo(cargo);

				UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
				UnidadeGestora ug = dao2.exibirUG(rs.getInt("IdUg"));
				sugestao.setUg(ug);

				sugestao.setValores(rs.getString("Valor"));
				sugestao.setIdSD(rs.getInt("IdSugestao"));
			}

			return sugestao;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterarSugestao(SugestaoDiaria sugestaoD) {
		try {

			String sql = "UPDATE sugestaovalordiaria SET IdCargo = ? , IdUg = ? , Valor = ? WHERE IdSugestao = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, sugestaoD.getCargo().getId());
			stmt.setInt(2, sugestaoD.getUg().getCodigo());
			stmt.setString(3, sugestaoD.getValores());
			stmt.setInt(4, sugestaoD.getIdSD());
			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void historicoAlteracaoSugestao(SugestaoDiaria sugestaoD) {
		try {

			String sql = "INSERT INTO historico (IdUsuarioAutor , Campo) VALUES (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, sugestaoD.getIdUsuario());
			sugestaoD.setCampo(sugestaoD.toString());
			stmt.setString(2, sugestaoD.getCampo());
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
