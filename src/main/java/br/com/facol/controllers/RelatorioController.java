package br.com.facol.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import br.com.facol.model.dao.ReservaRepositorio;
import br.com.facol.model.entidades.Reserva;
import br.com.facol.model.util.ConexaoJasper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


@Named
@RequestScoped
public class RelatorioController implements Serializable {

	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private FacesContext context;
	private ByteArrayOutputStream baos;


	public String pegarId() {
		String nid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idRelatorio");
		return nid;
	}

	public void gerarRelatorio() {
		try {
			this.context = FacesContext.getCurrentInstance();
			this.response = (HttpServletResponse) context.getExternalContext().getResponse();
			String id = pegarId();
			HashMap<String, Object> dados = new HashMap();
			dados.put("id", id);
			this.baos = new ByteArrayOutputStream();
			FacesContext context = FacesContext.getCurrentInstance();
			String fonte = context.getExternalContext().getRealPath("WEB-INF/Reserva.jrxml");
			JasperReport relatorioCompilado = JasperCompileManager.compileReport(fonte);
			JasperPrint relatorioPrenchido = JasperFillManager.fillReport(relatorioCompilado, dados,new ConexaoJasper().getConnection());
			JasperExportManager.exportReportToPdfStream(relatorioPrenchido, baos);
			this.response.reset();
			this.response.setContentType("application/pdf");
			this.response.setContentLength(baos.size());
			this.response.setHeader("content-disposition", "inline: filename=relatorio.pdf");
			this.response.getOutputStream().write(baos.toByteArray());
			this.response.getOutputStream().flush();
			this.response.getOutputStream().close();
			
			context.responseComplete();
		} catch (IOException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
