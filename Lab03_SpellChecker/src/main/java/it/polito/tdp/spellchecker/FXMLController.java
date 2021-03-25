/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtRisultato;
    
    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private Label lblErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTempo;
    
    @FXML
    void doSpellCheck(ActionEvent event) {
    	String lingua = boxLingua.getValue();
    	int cont =0;
    	model.loadDictionary(lingua);
    	
    	String testo = txtTesto.getText();
    	List <String> listaStringhe = model.creaListaStringhe(testo);
    	List <RichWord> lista = model.spellCheckText(listaStringhe);
    	
    	for (RichWord rw: lista) {
    		if (rw.isCorretta()==false) {
    			cont++;
    			txtRisultato.appendText(rw.getParola()+"\n");
    		}
    	}
    	lblErrori.setText("Il testo contiene "+ cont+ " errori");
    	
    }
    

    @FXML
    void doClearText(ActionEvent event) {
    	txtTesto.clear();
    	txtRisultato.clear();
    	lblErrori.setText("");
    }

    
    
    public void setModel(Dictionary m) {
    	this.model=m;
    	String language[] = {"English", "Italian"};
    	boxLingua.getItems().addAll(language);
    	
    }

    @FXML
    void initialize() {
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}


