package br.com.facol.controllers;

import java.io.Serializable;


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

	@Inject
	private UsuarioService serviceUsuario;

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

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Long pegarParametroId() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVeiculo");
		return Long.parseLong(id);
	}

	@PostConstruct
	public void iniciar() {
		this.veiculo = serviceVeiculo.listarPorId(pegarParametroId());
		

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

	public Usuario listarPorCpf() {
		return this.serviceUsuario.buscarUsuarioPorCpf(usuario.getCpf());

	}

	public String proxima() {
		this.usuario = serviceUsuario.buscarUsuarioPorCpf(usuario.getCpf());
		if (usuario == null) {
			Feedback.warning("Usuário não encontrado");
			return null;
		} else {
			conversation.begin();
			
			 int deferenca = (int) (this.reserva.getDataFinal().getTime() - this.reserva.getDataInicial().getTime());
			 
			 int totalDias = deferenca/1000/60/60/24;
			
			this.reserva.setTotalDias(totalDias);
			this.reserva.setValorTotal(this.veiculo.getValorDiaria() * totalDias);
			return "TelaConfirmarReserva?faces-redirect=true";

		}
	}

	public String salvarReserva() throws VeiculoException {
		try {
			Reserva reserva = this.serviceReserva.listarReservaPorPlaca(veiculo.getPlaca());
			if(reserva != null) {
				 Feedback.erro("Veículo já foi alugado");
				 return null;
			}else {
				this.usuario = listarPorCpf();
				this.veiculo.setSituacao("Indisponível");
				this.reserva.setUsuario(usuario);
				this.reserva.setVeiculo(veiculo);
				this.serviceVeiculo.salvar(veiculo);
				this.serviceReserva.salvarReserva(this.reserva);
				return "TelaFinalizacao?faces-redirect=true";
			}
		} catch (ReservaException e) {
			Feedback.erro(e.getMessage());
	
			return null;

		}
	}

}
