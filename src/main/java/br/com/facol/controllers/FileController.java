package br.com.facol.controllers;

import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class FileController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* private StreamedContent file;

	    public FileController() {        
	    	 file = DefaultStreamedContent.builder()
	                 .name("downloaded_boromir.jpg")
	                 .contentType("image/jpg")
	                 .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/boromir.jpg"))
	                 .build();
	    }

	    public StreamedContent getFile() {
	        return file;
	    }*/
}
