package it.polito.tdp.spellchecker.model;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItalianDictionary extends Dictionary{
	private String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";

public void loadDictionary() {
	
	try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		
		Statement st = conn.createStatement();
		
		String sql = "select nome from parola";  

		
		ResultSet res = st.executeQuery(sql);
	
		while( res.next() )
		{ 
			dizionario.add(res.getString("nome"));
		}
		res.close();
		conn.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	}
	


