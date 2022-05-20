package br.com.facol.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJasper {
	
	
	public Connection getConnection() {
		
		   try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bd_premiocar", "root", "");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	            return null;
	        }

	    }
		
	}

