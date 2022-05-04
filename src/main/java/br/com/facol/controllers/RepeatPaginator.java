package br.com.facol.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Veiculo;

@Named
@ViewScoped
public class RepeatPaginator implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private static final int DEFAULT_RECORDS_NUMBER = 6;
    private static final int DEFAULT_PAGE_INDEX = 1;
    @Inject
    private VeiculoController delegate;
    private int records;
    private int recordsTotal;
    private int pageIndex;
    private int pages;
    private List<Veiculo> model;
    
    public RepeatPaginator() {
        delegate = new VeiculoController();
        this.records = DEFAULT_RECORDS_NUMBER;
        this.pageIndex = DEFAULT_PAGE_INDEX;
        // Get Model
    }
    @PostConstruct
    public void init() {
    	 // Get Model
    	try {
        this.model = delegate.fetchCurrentList(getFirst(), getFirst()+records);
        this.recordsTotal = delegate.getListSize();

        if (records > 0) {
            pages = records <= 0 ? 1 : recordsTotal / records;

            if (recordsTotal % records > 0) {
                pages++;
            }

            if (pages == 0) {
                pages = 1;
            }
        } else {
            records = 1;
            pages = 1;
        }

        updateModel();
        
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void updateModel() {
        int fromIndex = getFirst();
        int toIndex = getFirst() + records;

        if(toIndex > this.recordsTotal) {
            toIndex = this.recordsTotal;
        }

         setModel(delegate.fetchCurrentList(fromIndex, toIndex));
    }

    public void next() {
        if(this.pageIndex < pages) {
            this.pageIndex++;
        }

        updateModel();
    }

    public void prev() {
        if(this.pageIndex > 1) {
            this.pageIndex--;
        }

        updateModel();
    }   

    public int getRecords() {
        return records;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPages() {
        return pages;
    }

    public int getFirst() {
        return (pageIndex * records) - records;
    }

    public List<Veiculo> getModel() {
        if(model==null)
            updateModel();
        return model;
    }

    public void setModel(List<Veiculo> model) {
        this.model = model;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}