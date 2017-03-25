package br.com.ifpe.monitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifpe.monitoramento.entidades.Cidade;
import br.com.ifpe.monitoramento.entidades.Deferimento;
import br.com.ifpe.monitoramento.entidades.SolicitarDiaria;
import br.com.ifpe.monitoramento.entidades.TipoDiaria;
import br.com.ifpe.monitoramento.entidades.UnidadeGestora;
import br.com.ifpe.monitoramento.entidades.Usuario;
import br.com.ifpe.monitoramento.util.ConnectionFactory;

public class SolicitarDiariaDao {

	private Connection connection;

	public SolicitarDiariaDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void cadastrarSolicitarDiaria(SolicitarDiaria s) {
		try {
			String sql = "INSERT INTO solicitardiaria (Justificativa,CidOrigem,CidDestino,DataIda,DataVolta,TipoDiaria,ValorDiaria,IdUsuario,uGestora)"
					+ " VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, s.getJustificativa());
			stmt.setInt(2, s.getCidOrigem().getCod_cidade());
			stmt.setInt(3, s.getCidDestino().getCod_cidade());
			stmt.setDate(4, new java.sql.Date(s.getDataIda().getTime()));
			stmt.setDate(5, new java.sql.Date(s.getDataVolta().getTime()));
			stmt.setString(6, s.getTipoDiaria().name());
			stmt.setString(7, s.getValorDiaria());
			stmt.setInt(8, s.getUsuarioId());
			stmt.setInt(9, s.getUnidadeGestora().getCodigo());

			stmt.execute();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<SolicitarDiaria> listarSolicitacaoADM(String cpfU, String nomeU, String dataadm, String dataAdm,
			Integer uGestoraU) {
		try {

			List<SolicitarDiaria> listar = new ArrayList<SolicitarDiaria>();
			String sql;
			PreparedStatement stmt = null;
			SolicitarDiaria sd;

			// Filtro apenas por cpf
			if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals(""))
					&& (dataadm == null || dataadm.equals("")) && (dataAdm == null || dataAdm.equals(""))

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
			}
			// filtro por cpf e nome
			else if (cpfU != null && !cpfU.equals("") && nomeU != null && !nomeU.equals("")
					&& (dataadm == null || dataadm.equals("")) && (dataAdm == null || dataAdm.equals(""))

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND u.nome_usuario LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, "%" + nomeU + "%");
			}
			// filtro por cpf data
			else if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals("")) && dataadm != null
					&& !dataadm.equals("") && dataAdm != null && !dataAdm.equals("")

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND s.DataIda = ? AND s.DataVolta = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, dataadm);
				stmt.setString(3, dataAdm);
			}
			// filtro por cpf e ug
			else if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals(""))
					&& (dataadm == null || dataadm.equals("")) && (dataAdm == null || dataAdm.equals(""))
					&& uGestoraU != null && !uGestoraU.equals("-1")) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND u.ug_pertence = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setInt(2, uGestoraU);
			}
			// filtro por tudo
			else if (cpfU != null && !cpfU.equals("") && (nomeU != null && !nomeU.equals(""))
					&& (dataadm != null && !dataadm.equals("")) && (dataAdm != null && !dataAdm.equals(""))

					&& (uGestoraU != null && !uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND u.nome_usuario LIKE ? AND u.ug_pertence = ? AND s.DataIda = ? AND s.DataVolta = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, "%" + nomeU + "%");
				stmt.setInt(3, uGestoraU);
				stmt.setString(4, dataadm);
				stmt.setString(5, dataAdm);
			}
			// filtro por nome
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals(""))
					&& (dataadm == null || dataadm.equals("")) && (dataAdm == null || dataAdm.equals(""))

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.nome_usuario LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
			}
			// filtro por nome data
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals("")) && dataadm != null
					&& !dataadm.equals("") && dataAdm != null && !dataAdm.equals("")

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.nome_usuario LIKE ? AND s.DataIda = ? AND s.DataVolta = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setString(2, dataadm);
				stmt.setString(3, dataAdm);
			}

			// filtro por nome e ug
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals(""))
					&& (dataadm == null || dataadm.equals("")) && (dataAdm == null || dataAdm.equals(""))
					&& uGestoraU != null && !uGestoraU.equals("-1")) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.nome_usuario LIKE ? AND u.ug_pertence = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setInt(2, uGestoraU);
			}
			// filtro por unidade gestora
			else if (uGestoraU != null && !uGestoraU.equals("-1") && (cpfU == null || cpfU.equals(""))
					&& (dataadm == null || dataadm.equals("")) && (dataAdm == null || dataAdm.equals(""))
					&& (nomeU == null || nomeU.equals(""))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.ug_pertence = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, uGestoraU);

			} // filtro por ug e data

			else if (dataadm != null && !dataadm.equals("") && dataAdm != null && !dataAdm.equals("")
					&& (uGestoraU != null && !uGestoraU.equals("-1")) && (cpfU == null || cpfU.equals(""))
					&& (nomeU == null || nomeU.equals(""))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.ug_pertence = ? AND s.DataIda= ? AND s.DataVolta = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, uGestoraU);
				stmt.setString(2, dataadm);
				stmt.setString(3, dataAdm);

			}
			// FIM OBJETO

			// COMEÇO DATA

			// filtro apenas por perido data alteração
			else if (dataadm != null && !dataadm.equals("") && dataAdm != null && !dataAdm.equals("")
					&& (uGestoraU == null || uGestoraU.equals("-1")) && (cpfU == null || cpfU.equals(""))
					&& (nomeU == null || nomeU.equals(""))) {
				sql = "select * from solicitardiaria  WHERE DataIda = ? AND DataVolta = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, dataadm);
				stmt.setString(2, dataAdm);

			}

			else {
				sql = "SELECT * FROM solicitardiaria";
				stmt = this.connection.prepareStatement(sql);
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sd = new SolicitarDiaria();
				sd.setCodSD(rs.getInt("CodSD"));
				sd.setJustificativa(rs.getString("Justificativa"));

				CidadeDao daocid = new CidadeDao();
				Cidade origem = daocid.buscarCidade(rs.getInt("CidOrigem"));
				sd.setCidOrigem(origem);

				Cidade destino = daocid.buscarCidade(rs.getInt("CidDestino"));
				sd.setCidDestino(destino);

				
				sd.setDataIda(rs.getDate("DataIda"));
				sd.setDataVolta(rs.getDate("DataVolta"));

				UsuarioDao daous = new UsuarioDao();
				Usuario us = daous.exibir(rs.getInt("IdUsuario"));
				sd.setIdUsuario(us);

				TipoDiaria tp = TipoDiaria.valueOf(rs.getString("TipoDiaria"));
				sd.setTipoDiaria(tp);

				sd.setValorDiaria(rs.getString("ValorDiaria"));

				Deferimento df = Deferimento.valueOf(rs.getString("Deferimento"));
				sd.setDef(df);

				UsuarioDao dao2 = new UsuarioDao();
				Usuario us2 = dao2.exibir(rs.getInt("IdUsuarioGestor"));
				sd.setIdGestor(us2);
				sd.setJustificativaGestor(rs.getString("JustificativaADM"));

				UnidadeGestoraDao dao4 = new UnidadeGestoraDao();
				UnidadeGestora ug = dao4.exibirUG(rs.getInt("uGestora"));
				sd.setUnidadeGestora(ug);

				listar.add(sd);

			}

			return listar;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<SolicitarDiaria> listarSolicitacaoUsuario(int IdUsuario) {
		try {

			List<SolicitarDiaria> listar = new ArrayList<SolicitarDiaria>();
			String sql = "SELECT * FROM solicitardiaria WHERE IdUsuario = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			SolicitarDiaria sd;
			stmt.setInt(1, IdUsuario);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sd = new SolicitarDiaria();
				sd.setCodSD(rs.getInt("CodSD"));
				sd.setJustificativa(rs.getString("Justificativa"));
				CidadeDao daocid = new CidadeDao();
				Cidade origem = daocid.buscarCidade(rs.getInt("CidOrigem"));
				sd.setCidOrigem(origem);

				Cidade destino = daocid.buscarCidade(rs.getInt("CidDestino"));
				sd.setCidDestino(destino);
				sd.setDataIda(rs.getDate("DataIda"));
				sd.setDataVolta(rs.getDate("DataVolta"));

				TipoDiaria tp = TipoDiaria.valueOf(rs.getString("TipoDiaria"));
				sd.setTipoDiaria(tp);

				sd.setValorDiaria(rs.getString("ValorDiaria"));

				Deferimento df = Deferimento.valueOf(rs.getString("Deferimento"));
				sd.setDef(df);

				UsuarioDao dao = new UsuarioDao();
				Usuario us = dao.exibir(rs.getInt("IdUsuarioGestor"));
				sd.setIdGestor(us);

				sd.setJustificativaGestor(rs.getString("JustificativaADM"));

				UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
				UnidadeGestora ug = dao2.exibirUG(rs.getInt("uGestora"));
				sd.setUnidadeGestora(ug);

				listar.add(sd);

			}

			return listar;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<SolicitarDiaria> listarSolicitacaoUsuarioUG(int UnidadeGestora, String cpfU, String nomeU,
			String datagestor, String dataGestor, Integer uGestoraU) {
		try {

			List<SolicitarDiaria> listar = new ArrayList<SolicitarDiaria>();

			String sql;
			PreparedStatement stmt = null;
			SolicitarDiaria sd;

			// Filtro apenas por cpf
			if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals(""))
					&& (datagestor == null || datagestor.equals("")) && (dataGestor == null || dataGestor.equals(""))

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setInt(2, UnidadeGestora);
			}
			// filtro por cpf e nome
			else if (cpfU != null && !cpfU.equals("") && nomeU != null && !nomeU.equals("")
					&& (datagestor == null || datagestor.equals("")) && (dataGestor == null || dataGestor.equals(""))

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND u.nome_usuario LIKE ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, "%" + nomeU + "%");
				stmt.setInt(3, UnidadeGestora);
			}
			// filtro por cpf data
			else if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals("")) && datagestor != null
					&& !datagestor.equals("") && dataGestor != null && !dataGestor.equals("")

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND s.DataIda = ? AND s.DataVolta = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, datagestor);
				stmt.setString(3, dataGestor);
				stmt.setInt(4, UnidadeGestora);
			}
			// filtro por cpf e ug
			else if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals(""))
					&& (datagestor == null || datagestor.equals("")) && (dataGestor == null || dataGestor.equals(""))
					&& uGestoraU != null && !uGestoraU.equals("-1")) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND u.ug_pertence = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setInt(2, uGestoraU);
				stmt.setInt(3, UnidadeGestora);
			}
			// filtro por tudo
			else if (cpfU != null && !cpfU.equals("") && (nomeU != null && !nomeU.equals(""))
					&& (datagestor != null && !datagestor.equals("")) && (dataGestor != null && !dataGestor.equals(""))

					&& (uGestoraU != null && !uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.cpf_usuario = ? AND u.nome_usuario LIKE ? AND u.ug_pertence = ? AND s.DataIda = ? AND s.DataVolta = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, "%" + nomeU + "%");
				stmt.setInt(3, uGestoraU);
				stmt.setString(4, datagestor);
				stmt.setString(5, dataGestor);
				stmt.setInt(6, UnidadeGestora);
			}
			// filtro por nome
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals(""))
					&& (datagestor == null || datagestor.equals("")) && (dataGestor == null || dataGestor.equals(""))

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.nome_usuario LIKE ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setInt(2, UnidadeGestora);
			}
			// filtro por nome data
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals("")) && datagestor != null
					&& !datagestor.equals("") && dataGestor != null && !dataGestor.equals("")

					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.nome_usuario LIKE ? AND s.DataIda = ? AND s.DataVolta = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setString(2, datagestor);
				stmt.setString(3, dataGestor);
				stmt.setInt(4, UnidadeGestora);
			}

			// filtro por nome e ug
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals(""))
					&& (datagestor == null || datagestor.equals("")) && (dataGestor == null || dataGestor.equals(""))
					&& uGestoraU != null && !uGestoraU.equals("-1")) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.nome_usuario LIKE ? AND u.ug_pertence = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setInt(2, uGestoraU);
				stmt.setInt(3, UnidadeGestora);
			}
			// filtro por unidade gestora
			else if (uGestoraU != null && !uGestoraU.equals("-1") && (cpfU == null || cpfU.equals(""))
					&& (datagestor == null || datagestor.equals("")) && (dataGestor == null || dataGestor.equals(""))
					&& (nomeU == null || nomeU.equals(""))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.ug_pertence = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, uGestoraU);
				stmt.setInt(2, UnidadeGestora);

			} // filtro por ug e data

			else if (datagestor != null && !datagestor.equals("") && dataGestor != null && !dataGestor.equals("")
					&& (uGestoraU != null && !uGestoraU.equals("-1")) && (cpfU == null || cpfU.equals(""))
					&& (nomeU == null || nomeU.equals(""))) {
				sql = "select * from solicitardiaria s inner join usuario u on s.IdUsuario = u.id_usuario where u.ug_pertence = ? AND s.DataIda= ? AND s.DataVolta = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, uGestoraU);
				stmt.setString(2, datagestor);
				stmt.setString(3, dataGestor);
				stmt.setInt(4, UnidadeGestora);

			}
			// FIM OBJETO

			// COMEÇO DATA

			// filtro apenas por perido data alteração
			else if (datagestor != null && !datagestor.equals("") && dataGestor != null && !dataGestor.equals("")
					&& (uGestoraU == null || uGestoraU.equals("-1")) && (cpfU == null || cpfU.equals(""))
					&& (nomeU == null || nomeU.equals(""))) {
				sql = "select * from solicitardiaria  WHERE DataIda = ? AND DataVolta = ? AND uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, datagestor);
				stmt.setString(2, dataGestor);
				stmt.setInt(3, UnidadeGestora);

			}

			else {
				sql = "SELECT * FROM solicitardiaria WHERE uGestora = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1, UnidadeGestora);
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sd = new SolicitarDiaria();
				sd.setCodSD(rs.getInt("CodSD"));
				sd.setJustificativa(rs.getString("Justificativa"));
				CidadeDao daocid = new CidadeDao();
				Cidade origem = daocid.buscarCidade(rs.getInt("CidOrigem"));
				sd.setCidOrigem(origem);

				Cidade destino = daocid.buscarCidade(rs.getInt("CidDestino"));
				sd.setCidDestino(destino);
				sd.setDataIda(rs.getDate("DataIda"));
				sd.setDataVolta(rs.getDate("DataVolta"));

				TipoDiaria tp = TipoDiaria.valueOf(rs.getString("TipoDiaria"));
				sd.setTipoDiaria(tp);

				sd.setValorDiaria(rs.getString("ValorDiaria"));

				Deferimento df = Deferimento.valueOf(rs.getString("Deferimento"));
				sd.setDef(df);

				UsuarioDao dao0 = new UsuarioDao();
				Usuario us0 = dao0.exibir(rs.getInt("IdUsuario"));
				sd.setIdUsuario(us0);

				UsuarioDao dao = new UsuarioDao();
				Usuario us = dao.exibir(rs.getInt("IdUsuarioGestor"));
				sd.setIdGestor(us);

				sd.setJustificativaGestor(rs.getString("JustificativaADM"));

				UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
				UnidadeGestora ug = dao2.exibirUG(rs.getInt("uGestora"));
				sd.setUnidadeGestora(ug);

				listar.add(sd);

			}

			return listar;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public SolicitarDiaria exibirSolicitacao(int idSolicitacao) { // exibir
																	// solicitacao
		try {
			String sql = "SELECT * FROM solicitardiaria WHERE CodSD = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			SolicitarDiaria SolicitarDiaria = null;
			stmt.setInt(1, idSolicitacao);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SolicitarDiaria = new SolicitarDiaria();

				SolicitarDiaria.setCodSD(rs.getInt("CodSD"));

			}

			return SolicitarDiaria;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterarSolicitacao(int codSD, int idGestor, String justificativaGestor, String def) {
		try {

			String sql = "UPDATE solicitardiaria SET Deferimento = ? , IdUsuarioGestor = ? , JustificativaADM = ? WHERE CodSD = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, def);
			stmt.setInt(2, idGestor);
			stmt.setString(3, justificativaGestor);
			stmt.setInt(4, codSD);

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void historicoAlteracaoSolicitacao(SolicitarDiaria diaria) {
		try {

			String sql = "INSERT INTO historico (IdUsuarioAutor,Campo) VALUES(?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, diaria.getIdGestor().getIdUser());
			diaria.setCampo(diaria.toString());
			stmt.setString(2, diaria.getCampo());

			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
