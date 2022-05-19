package br.com.facol.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Reserva;
import br.com.facol.model.entidades.Usuario;
import br.com.facol.model.entidades.Veiculo;
import br.com.facol.model.exceptions.ReservaException;
import br.com.facol.model.exceptions.VeiculoException;
import br.com.facol.model.servicos.ReservaService;
import br.com.facol.model.servicos.UsuarioService;
import br.com.facol.model.servicos.VeiculoService;
import br.com.facol.model.util.Feedback;

@Named("reservaBean")
@ConversationScoped
public class ReservaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;
	@Inject
	private Veiculo veiculo;
	private List<Reserva> reservas;
	@Inject
	private UsuarioService serviceUsuario;
	@Inject
	private AutentificaoLoginController autentifica;
	@Inject
	private VeiculoService serviceVeiculo;
	@Inject
	private ReservaService serviceReserva;
	@Inject
	private Reserva reserva;
	
	@Inject
	private Conversation conversation;

	public ReservaController() {

	}
	
	public Long pegarParametroId() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVeiculo");
		return Long.parseLong(id);
	}
	
	
	public Long pegarParametroReserva() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idReserva");
		return Long.parseLong(id);
	}
	
	
	public AutentificaoLoginController getAutentifica() {
		return autentifica;
	}

	public void setAutentifica(AutentificaoLoginController autentifica) {
		this.autentifica = autentifica;
	}
	
	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	@PostConstruct
	public void iniciar() {
		try {
		this.veiculo = serviceVeiculo.listarPorId(pegarParametroId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.reservas = serviceReserva.listarTudo();
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public UsuarioService getServiceUsuario() {
		return serviceUsuario;
	}

	public void setServiceUsuario(UsuarioService serviceUsuario) {
		this.serviceUsuario = serviceUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public Reserva listarReserva() {
		return this.serviceReserva.listarReservaPorId(pegarParametroReserva());
	}

	public Usuario listarPorCpf() {
		return this.serviceUsuario.buscarUsuarioPorCpf(autentifica.getUsuario().getCpf());

	}

	public String proxima() {
		this.usuario = serviceUsuario.buscarUsuarioPorCpf(autentifica.getUsuario().getCpf());
		if (usuario == null) {
			Feedback.warning("Usuário não encontrado");
			return null;
		} else {
			conversation.begin();
			 int diferenca = (int) (this.reserva.getDataFinal().getTime() - this.reserva.getDataInicial().getTime());
			 int totalDias = diferenca/1000/60/60/24;
			this.reserva.setTotalDias(totalDias);
			this.reserva.setValorTotal(this.veiculo.getValorDiaria() * totalDias);
			return "TelaConfirmarReserva?faces-redirect=true";

		}
	}

	public String salvarReserva() throws VeiculoException {
		try {
				this.veiculo.setSituacao("Indisponível");
				this.reserva.setUsuario(usuario);
				this.reserva.setVeiculo(veiculo);
				this.serviceVeiculo.salvar(veiculo);
				this.serviceReserva.salvarReserva(this.reserva);
				return "TelaFinalizacao?faces-redirect=true";
			} catch (ReservaException e) {
			Feedback.erro(e.getMessage());
		}
		return null;
	}
}
