package br.com.ifpe.monitoramento.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ifpe.monitoramento.dao.CidadeDao;
import br.com.ifpe.monitoramento.dao.SolicitarDiariaDao;
import br.com.ifpe.monitoramento.dao.SugestaoDiariaDao;
import br.com.ifpe.monitoramento.dao.UnidadeGestoraDao;
import br.com.ifpe.monitoramento.entidades.Cidade;
import br.com.ifpe.monitoramento.entidades.SolicitarDiaria;
import br.com.ifpe.monitoramento.entidades.SugestaoDiaria;
import br.com.ifpe.monitoramento.entidades.Usuario;

@Controller
public class SolicitarDiariaController {

	@RequestMapping("/formSolicitarDiaria")
	public String formSolicitarDiaria(Model model, String nome, String codigo) {
		CidadeDao dao = new CidadeDao();
		model.addAttribute("ListarEstados", dao.listar());
		UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
		model.addAttribute("buscarUnidadeGestora", dao2.listarUG(nome, codigo));
		return "SolicitarDiaria/FormCadastroSolicitarDiaria";
	}

	@RequestMapping("/listarCidade") // cidade origem ajax
	public @ResponseBody String exibirCidade(@RequestParam Integer cod_cidade, HttpServletResponse response) {

		CidadeDao dao = new CidadeDao();
		List<Cidade> listar = dao.listar2(cod_cidade);

		StringBuilder st = new StringBuilder();
		st.append("<label>");
		st.append("Cidade origem");
		st.append("</label>");
		st.append("<br>");
		st.append("<select name='CidOrigem' id='CidOrigem' required='true'>");
		st.append("<option value=''>Selecione a cidade</option>");
		for (Cidade cidade : listar) {
			st.append("<option value=" + cidade.getCod_cidade() + ">" + cidade.getNome() + "</option>");

		}
		st.append("</select>");
		response.setStatus(200);
		return st.toString();
	}

	@RequestMapping("/listarCidade2") // Cidade destino ajax
	public @ResponseBody String exibirCidade2(@RequestParam Integer cod_cidade, HttpServletResponse response) {

		CidadeDao dao = new CidadeDao();
		List<Cidade> listar = dao.listar2(cod_cidade);

		StringBuilder st = new StringBuilder();
		st.append("<label>");
		st.append("Cidade destino");
		st.append("</label>");
		st.append("<br>");
		st.append("<select name='CidDestino' id='CidDestino' required='true'>");
		st.append("<option value=''>Selecione a cidade</option>");
		for (Cidade cidade : listar) {
			st.append("<option value=" + cidade.getCod_cidade() + ">" + cidade.getNome() + "</option>");

		}
		st.append("</select>");
		response.setStatus(200);
		return st.toString();
	}

	@RequestMapping("/exibirValor") // Valor ajax
	public @ResponseBody String exibirValor(@RequestParam Integer origem, Integer destino, HttpServletResponse response,
			HttpServletRequest request) {
		Usuario us = (Usuario) request.getSession().getAttribute("usuarioLogado");
		int idUg = us.getuGestora().getCodigo();
		int idCargo = us.getCargo().getId();
		SugestaoDiariaDao dao = new SugestaoDiariaDao();
		List<SugestaoDiaria> listarValor = dao.listarValor(origem, destino, idUg, idCargo);

		StringBuilder st = new StringBuilder();
		st.append("<label>");
		st.append("Valor");
		st.append("</label>");
		st.append("<br>");

		for (SugestaoDiaria valor : listarValor) {

			st.append(
					"<input type='text' name='ValorDiaria' id='ValorDiaria' required='true' maxlength='8' minlength='2' value="
							+ valor.getValores() + ">");

			break;

		}
		if (listarValor.size() == 0) {
			st.append(
					"<input type='text' name='ValorDiaria' id='ValorDiaria' required='true' maxlength='8' minlength='2' value='0' >");
		}

		response.setStatus(200);
		return st.toString();
	}

	@RequestMapping("/cadastarSolicitacao")
	public String cadastarSolicitacao(@Valid SolicitarDiaria s, BindingResult rs, Model model) {
		if (rs.hasFieldErrors()) {
			return "forward:formSolicitarDiaria";
		}
		SolicitarDiariaDao dao = new SolicitarDiariaDao();
		dao.cadastrarSolicitarDiaria(s);
		model.addAttribute("msgSucesso", "Solicitação feita com sucesso! ! ");
		return "sucesso/sucesso";
	}

	@RequestMapping("/listarSolicitacao") // listar Solicitacao p/ ADM
	public String listarSolicitacao(Model model, String cpfU, String dataadm, String dataAdm, String nomeU,
			Integer uGestoraU, String codigo, String nome) {
		SolicitarDiariaDao dao = new SolicitarDiariaDao();
		model.addAttribute("listarSolicitacao", dao.listarSolicitacaoADM(cpfU, nomeU, dataadm, dataAdm, uGestoraU));
		UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
		model.addAttribute("listarUGestora", dao2.listarUG(nome, codigo));
		return "SolicitarDiaria/ListarSolicitacao";
	}

	@RequestMapping("/acompanharSolicitacao") // listar Solicitacao p/ USUARIO
	public String acompanharSolicitacao(Model model, HttpServletRequest request) {
		Usuario us = (Usuario) request.getSession().getAttribute("usuarioLogado");
		int IdUsuario = us.getIdUser();
		SolicitarDiariaDao dao = new SolicitarDiariaDao();
		model.addAttribute("acompanharSolicitacao", dao.listarSolicitacaoUsuario(IdUsuario));
		return "SolicitarDiaria/acompanharDiaria";
	}

	@RequestMapping("/acompanharSolicitacaoGestor") // listar Solicitacao p/
													// Gestor UG
	public String acompanharSolicitacaoGestor(Model model, HttpServletRequest request, String cpfU, String datagestor,
			String dataGestor, String nomeU, Integer uGestoraU, String nome, String codigo) {
		Usuario us = (Usuario) request.getSession().getAttribute("usuarioLogado");
		int UnidadeGestora = us.getuGestora().getCodigo();
		SolicitarDiariaDao dao = new SolicitarDiariaDao();
		model.addAttribute("ListarSolicitacaoGestor",
				dao.listarSolicitacaoUsuarioUG(UnidadeGestora, cpfU, nomeU, datagestor, dataGestor, uGestoraU));
		UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
		model.addAttribute("listarUGestora", dao2.listarUG(nome, codigo));
		return "SolicitarDiaria/acompanharDiaria";
	}

	@RequestMapping("/ExibiralterarSolicitacao")
	public String ExibiralterarSolicitacao(int idSolicitacao, Model model) {
		SolicitarDiariaDao dao = new SolicitarDiariaDao();
		model.addAttribute("exibirSolicitacao", dao.exibirSolicitacao(idSolicitacao));
		return "SolicitarDiaria/exibirSolicitacao";
	}

	@RequestMapping("/alterarSolicitacao")
	public String alterarSolicitacao(int codSD, int idGestor, String justificativaGestor, String def, Model model) {

		SolicitarDiariaDao dao = new SolicitarDiariaDao();
		dao.alterarSolicitacao(codSD, idGestor, justificativaGestor, def);

		model.addAttribute("msgSucesso", "Solicitação Alterada com sucesso ! !");
		return "sucesso/sucesso";
	}
}
