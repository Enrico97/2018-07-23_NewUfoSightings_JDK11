package it.polito.tdp.newufosightings;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;

import it.polito.tdp.newufosightings.model.Model;
import it.polito.tdp.newufosightings.model.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller turno A --> switchare al branch master_turnoB per turno B

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtAnno;

    @FXML
    private Button btnSelezionaAnno;

    @FXML
    private ComboBox<String> cmbBoxForma;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private TextField txtT1;

    @FXML
    private TextField txtAlfa;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	int y = 0;
    	if(cmbBoxForma.getValue()== null) {
    		txtResult.appendText("scegliere una forma");
    		return;
    	}
    	try {
    		int x = Integer.parseInt(txtAnno.getText());
    		model.creaGrafo(cmbBoxForma.getValue(), x);
    		for(State s : model.creaGrafo(cmbBoxForma.getValue(), x).vertexSet()) {
    			y=0;
    			for(State vicino : Graphs.neighborListOf(model.creaGrafo(cmbBoxForma.getValue(), x), s)) {
    				y+=model.creaGrafo(cmbBoxForma.getValue(), x).getEdgeWeight(model.creaGrafo(cmbBoxForma.getValue(), x).getEdge(s, vicino));
    		}
    			txtResult.appendText(s.getName()+" --> "+y+"\n");
    		}
    	} catch (NumberFormatException e) {
    		txtResult.appendText("inserire un anno corretto");
    		return;
    	}
    	
    }

    @FXML
    void doSelezionaAnno(ActionEvent event) {
    	txtResult.clear();
    	try {
    		int x = Integer.parseInt(txtAnno.getText());
    		if(x>=1910 && x<=2014) {
    			cmbBoxForma.getItems().addAll(model.shape(x));
    			txtResult.appendText("box riempita");
    		} else {
    			txtResult.appendText("inserire un anno compreso tra il 1910 e il 2014");
    		}
    	} catch (NumberFormatException e) {
    		txtResult.appendText("inserire un anno corretto");
    		return;
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText(model.getDEFCON(Integer.parseInt(txtT1.getText()), Double.parseDouble(txtAlfa.getText()), cmbBoxForma.getValue(), Integer.parseInt(txtAnno.getText()))+"");

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnSelezionaAnno != null : "fx:id=\"btnSelezionaAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert cmbBoxForma != null : "fx:id=\"cmbBoxForma\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtT1 != null : "fx:id=\"txtT1\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtAlfa != null : "fx:id=\"txtAlfa\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
