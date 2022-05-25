package br.com.facol.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Veiculo;

@Named("repeatPaginator")
@ViewScoped
public class PaginadorController implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_RECORDS_NUMBER = 6;
    private static final int DEFAULT_PAGE_INDEX = 1;
    
    @Inject
    private VeiculoController delegate;
    private int registros;
    private int totalRegistros;
    private int indicePagina;
    private int paginas;
    private List<Veiculo> modelo;
    
    public PaginadorController() {
        delegate = new VeiculoController();
        this.registros = DEFAULT_RECORDS_NUMBER;
        this.indicePagina = DEFAULT_PAGE_INDEX;
        
    }
    @PostConstruct
    public void init() {
    	 
    	try {
        this.modelo = delegate.fetchCurrentList(getPrimeiro(), getPrimeiro()+registros);
        this.totalRegistros = delegate.getListSize();

        if (registros > 0) {
            paginas = registros <= 0 ? 1 : totalRegistros / registros;

            if (totalRegistros % registros > 0) {
                paginas++;
            }

            if (paginas == 0) {
                paginas = 1;
            }
        } else {
            registros = 1;
            paginas = 1;
        }

        updateModel();
        
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
   
    public String converterPonto(Double valor) {
    	
    		return String.valueOf(valor).replace(".", ",");
    	
    }
    
    public void updateModel() {
        int fromIndex = getPrimeiro();
        int toIndex = getPrimeiro() + registros;

        if(toIndex > this.totalRegistros) {
            toIndex = this.totalRegistros;
        }

         setModelo(delegate.fetchCurrentList(fromIndex, toIndex));
    }

    public void proximo() {
        if(this.indicePagina < paginas) {
            this.indicePagina++;
        }

        updateModel();
    }

    public void prev() {
        if(this.indicePagina > 1) {
            this.indicePagina--;
        }

        updateModel();
    }   

    public int getRegistros() {
        return registros;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public int getIndicePagina() {
        return indicePagina;
    }

    public int getPaginas() {
        return paginas;
    }

    public int getPrimeiro() {
        return (indicePagina * registros) - registros;
    }

    public List<Veiculo> getModelo() {
        if(modelo==null)
            updateModel();
        return modelo;
    }

    public void setModelo(List<Veiculo> modelo) {
        this.modelo = modelo;
    }

    public void setIndicePagina(int indicePagina) {
        this.indicePagina = indicePagina;
    }
}