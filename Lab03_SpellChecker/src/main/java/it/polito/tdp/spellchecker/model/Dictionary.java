package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List <RichWord> listaParoleEN;
	List <RichWord> listaParoleIN;
	
	public Dictionary() {
		listaParoleEN = new LinkedList <>();
		listaParoleIN = new LinkedList <>();
	}
	
	public void loadDictionary (String language) {
		if (language.equals("English")) {
		try {
			FileReader fr = new FileReader ("src/main/resources/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine())!=null) {
				RichWord rw = new RichWord(word);
				listaParoleEN.add(rw);
			}
			br.close();
		}catch (IOException e) {
			System.out.println("Errore nella lettura da file");
		}
	}
		else if (language.equals("Italian")) {
			try {
				FileReader fr = new FileReader ("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine())!=null) {
					
					RichWord rw = new RichWord(word);
					listaParoleIN.add(rw);
				}
				br.close();
			}catch (IOException e) {
				System.out.println("Errore nella lettura da file");
			}
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
			listaRw.add(rw);
			if (listaParoleEN.contains(rw) || listaParoleIN.contains(rw)) 
				rw.setCorretta(true);
			else
				rw.setCorretta(false);
		}
		return listaRw;
	}

}
