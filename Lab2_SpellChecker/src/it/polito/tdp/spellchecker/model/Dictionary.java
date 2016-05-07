package it.polito.tdp.spellchecker.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Dictionary {
	
	List <String> dizionario;
	// List <RichWord> parole;
	Set<RichWord> parole;
	private String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";

	
	public Dictionary(){
		dizionario = new LinkedList();
		//parole = new LinkedList();
		parole = new LinkedHashSet<RichWord>(500);
		


	}
	
	
	public void loadDictionary() {
		
	}

	
	public Set<RichWord> spellCheckText(List<String> inputTextList) {
		parole.clear();
		for(String s : inputTextList){
			RichWord rw= new RichWord(s.toLowerCase());

			//if (binarySearch(s.toLowerCase())== false){ rw.setIscorretta(false);}
			//parole.add(rw);}

			try {
				Connection conn = DriverManager.getConnection(jdbcURL);
				
				Statement st = conn.createStatement();
				
				String sql = "select nome from parola where nome=\"" + s + "\"";			
				ResultSet res = st.executeQuery(sql);

				if (res.next()) {
					// found
					rw.setIscorretta(true);
					res.close();
					conn.close();

				} else {
					// not found
					rw.setIscorretta(false);
					res.close();
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		//	if (!dizionario.contains(s.toLowerCase()))rw.setIscorretta(false); 
				parole.add(rw);}
		
		
		return parole;
	}
	
	public boolean binarySearch(String key) 
	    {
	       int low = 0;
	       int high = dizionario.size() - 1;
	       int middle = (high+low) / 2; 
	        while(low <= high) {
             
	            if(key.compareToIgnoreCase(dizionario.get(middle))== 0) {
	                return true;
	            }
            if(key.compareToIgnoreCase(dizionario.get(middle)) > 0) {
	                 low = middle +1;
	             }
            else{
	                 high = middle - 1;
	            }
	             middle = (high+low) / 2;
	       }
	        return false;
   }

}
