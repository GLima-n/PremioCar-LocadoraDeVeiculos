package br.com.facol.model.util;

import java.io.File;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.file.UploadedFile;

public class UploadImagem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private File file;
	private UploadedFile uploadedFile;
	

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public void upload() {
		  try {
		   this.file = new File(uploadedFile.getFileName());
		  } catch(Exception e) {
		    Feedback.warning("Selecione uma imagem!");
		  }
		 
		}

}
