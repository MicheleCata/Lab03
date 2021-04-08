package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private List <String> dizionario;
	private String lingua;
	
	public boolean loadDictionary (String language) {
		if (dizionario!=null && this.lingua.equals(language)) {
			return true;
		}
		dizionario= new LinkedList <>();
		this.lingua=language;
		try {
			FileReader fr = new FileReader ("src/main/resources/" + language +".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine())!=null) {
				
				dizionario.add(word);
			}
			Collections.sort(dizionario);
			br.close();
			return true;
		}catch (IOException e) {
			System.out.println("Errore nella lettura da file");
			return false;
		}
	}
	
	public List<String> creaListaStringhe(String s) {
		String[] campi = s.split(" ");
		List <String> listaS = new LinkedList<>();
		for (int i=0; i<campi.length; i++) {
			listaS.add(campi[i]);
		}
		return listaS;
	}
	
	public List<RichWord> spellCheckText (List <String> inputTextList) {
		RichWord rw= null;
		List <RichWord> listaRw = new LinkedList<RichWord>();
		for (String s: inputTextList) {
			String ss= s.replaceAll("[.,\\/#!?$%\\*;:{}=\\-_`~()\\[\\]\"]","").toLowerCase();
			rw = new RichWord(ss);
			
			if (dizionario.contains(ss)) 
				rw.setCorretta(true);
			else
				rw.setCorretta(false);
			
			listaRw.add(rw);
		}
		return listaRw;
	}

}
