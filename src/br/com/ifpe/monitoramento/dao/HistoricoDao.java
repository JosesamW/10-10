package br.com.ifpe.monitoramento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifpe.monitoramento.entidades.Historico;
import br.com.ifpe.monitoramento.entidades.Usuario;
import br.com.ifpe.monitoramento.util.ConnectionFactory;

public class HistoricoDao {

	private Connection connection;

	public HistoricoDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Historico> listarHistorico(String cpfU, String nomeU, String datah, String dataH, String objetoAlterado,
			Integer uGestoraU) {
		try {
			String sql;
			PreparedStatement stmt = null;
			List<Historico> listarHistorico = new ArrayList<Historico>();

			// COMEÇO CPF

			// Filtro apenas por cpf
			if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (objetoAlterado == null || objetoAlterado.equals(""))
					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.cpf_usuario = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
			}
			// filtro por cpf e nome
			else if (cpfU != null && !cpfU.equals("") && nomeU != null && !nomeU.equals("")
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (objetoAlterado == null || objetoAlterado.equals(""))
					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.cpf_usuario = ? AND u.nome_usuario LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, "%" + nomeU + "%");
			}
			// filtro por cpf data
			else if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals("")) && datah != null
					&& !datah.equals("") && dataH != null && !dataH.equals("")
					&& (objetoAlterado == null || objetoAlterado.equals(""))
					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.cpf_usuario = ? AND h.DataAlteracao >= ? AND h.DataAlteracao <= ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, datah);
				stmt.setString(3, dataH);
			}
			// filtro por cpf objeto
			else if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& objetoAlterado != null && !objetoAlterado.equals("")
					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.cpf_usuario = ? AND h.Campo LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, "%" + objetoAlterado + "%");
			}
			// filtro por cpf e ug
			else if (cpfU != null && !cpfU.equals("") && (nomeU == null || nomeU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (objetoAlterado == null || objetoAlterado.equals("")) && uGestoraU != null
					&& !uGestoraU.equals("-1")) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.cpf_usuario = ? AND u.ug_pertence = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setInt(2, uGestoraU);
			}
			// filtro por tudo
			else if (cpfU != null && !cpfU.equals("") && (nomeU != null && !nomeU.equals(""))
					&& (datah != null && !datah.equals("")) && (dataH != null && !dataH.equals(""))
					&& (objetoAlterado != null && !objetoAlterado.equals(""))
					&& (uGestoraU != null && !uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.cpf_usuario = ? AND u.nome_usuario LIKE ? AND h.Campo LIKE ? AND u.ug_pertence = ? AND h.DataAlteracao >= ? AND h.DataAlteracao <= ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cpfU);
				stmt.setString(2, "%" + nomeU + "%");
				stmt.setString(3, "%" + objetoAlterado + "%");
				stmt.setInt(4, uGestoraU);
				stmt.setString(5, datah);
				stmt.setString(6, dataH);
			}

			// FIM CPF

			// COMEÇO NOME

			// Filtro apenas por nome usuario
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (objetoAlterado == null || objetoAlterado.equals(""))
					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.nome_usuario LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
			}
			// filtro por nome data
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals("")) && datah != null
					&& !datah.equals("") && dataH != null && !dataH.equals("")
					&& (objetoAlterado == null || objetoAlterado.equals(""))
					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.nome_usuario LIKE ? AND h.DataAlteracao >= ? AND h.DataAlteracao <= ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setString(2, datah);
				stmt.setString(3, dataH);
			}
			// filtro por nome e objeto
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& objetoAlterado != null && !objetoAlterado.equals("")
					&& (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.nome_usuario LIKE ? AND h.Campo LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setString(2, "%" + objetoAlterado + "%");
			}
			// filtro por nome e ug
			else if (nomeU != null && !nomeU.equals("") && (cpfU == null || cpfU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (objetoAlterado == null || objetoAlterado.equals("")) && uGestoraU != null
					&& !uGestoraU.equals("-1")) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.nome_usuario LIKE ? AND u.ug_pertence = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + nomeU + "%");
				stmt.setInt(2, uGestoraU);
			}

			// FIM NOME

			// COMEÇO OBJETO ALTERADO

			// Filtro por Objeto alterado ex : usuario , cargo ,
			// etc..
			else if (objetoAlterado != null && !objetoAlterado.equals("") && (cpfU == null || cpfU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (nomeU == null || nomeU.equals("")) && (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico where Campo LIKE ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + objetoAlterado + "%");

			} // filtro por Objeto e data
			else if (objetoAlterado != null && !objetoAlterado.equals("") && (nomeU == null || nomeU.equals(""))
					&& datah != null && !datah.equals("") && dataH != null && !dataH.equals("")
					&& (cpfU == null || cpfU.equals("")) && (uGestoraU == null || uGestoraU.equals("-1"))) {
				sql = "select * from historico WHERE Campo LIKE ? AND h.DataAlteracao >= ? AND h.DataAlteracao <= ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + objetoAlterado + "%");
				stmt.setString(2, datah);
				stmt.setString(3, dataH);
			}

			// filtro por Objeto e ug
			else if (objetoAlterado != null && !objetoAlterado.equals("") && (nomeU == null || nomeU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (cpfU == null || cpfU.equals("")) && uGestoraU != null && !uGestoraU.equals("-1")) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario WHERE h.Campo LIKE ? AND u.ug_pertence = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, "%" + objetoAlterado + "%");
				stmt.setInt(2, uGestoraU);
			}

			// FIM OBJETO

			// COMEÇO UNIDADE GESTORA

			// filtro por unidade gestora
			else if (uGestoraU != null && !uGestoraU.equals("-1") && (cpfU == null || cpfU.equals(""))
					&& (datah == null || datah.equals("")) && (dataH == null || dataH.equals(""))
					&& (nomeU == null || nomeU.equals("")) && (objetoAlterado == null || objetoAlterado.equals(""))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.ug_pertence = ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1,  uGestoraU );

			} // filtro por ug e data

			else if (datah != null && !datah.equals("") && dataH != null && !dataH.equals("")
					&& (uGestoraU != null && !uGestoraU.equals("-1")) && (cpfU == null || cpfU.equals(""))
					&& (nomeU == null || nomeU.equals("")) && (objetoAlterado == null || objetoAlterado.equals(""))) {
				sql = "select * from historico h inner join usuario u on h.IdUsuarioAutor = u.id_usuario where u.ug_pertence = ? AND h.DataAlteracao >= ? AND h.DataAlteracao <= ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setInt(1,uGestoraU );
				stmt.setString(2, datah);
				stmt.setString(3, dataH);

			}
			// FIM OBJETO

			// COMEÇO DATA

			// filtro apenas por perido data alteração
			else if (datah != null && !datah.equals("") && dataH != null && !dataH.equals("")
					&& (uGestoraU == null || uGestoraU.equals("-1")) && (cpfU == null || cpfU.equals(""))
					&& (nomeU == null || nomeU.equals("")) && (objetoAlterado == null || objetoAlterado.equals(""))) {
				sql = "select * from historico WHERE DataAlteracao >= ? AND DataAlteracao <= ?";
				stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, datah);
				stmt.setString(2, dataH);

			}

			else {
				sql = "SELECT * FROM historico ORDER BY Id";
				stmt = this.connection.prepareStatement(sql);
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Historico ht = new Historico();
				ht.setIdAuto(rs.getInt("Id"));
				UsuarioDao dao = new UsuarioDao();
				Usuario us = dao.exibir(rs.getInt("IdUsuarioAutor"));
				ht.setIdUsuarioAutor(us);
				ht.setCampo(rs.getString("Campo"));
				ht.setDataAlteracao(rs.getDate("DataAlteracao"));
				listarHistorico.add(ht);
			}

			rs.close();
			stmt.close();
			connection.close();
			return listarHistorico;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}