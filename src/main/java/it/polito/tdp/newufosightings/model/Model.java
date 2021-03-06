package it.polito.tdp.newufosightings.model;

import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;

public class Model {

	NewUfoSightingsDAO dao = new NewUfoSightingsDAO();
	Graph<State, DefaultWeightedEdge> grafo;
	Simulator s = new Simulator();
	
	public List<String> shape(int anno) {
		return dao.shape(anno);
	}
	
	public Graph<State, DefaultWeightedEdge> creaGrafo(String shape, int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.loadAllStates());
		for(Adiacenza a : dao.archi(shape, anno)) {
			Graphs.addEdge(grafo, a.getS1(), a.getS2(), a.getPeso());
		}
	//	System.out.println(grafo);
		return grafo;
	}
	public List<Avvistamento> avvistamenti(String shape, int anno) {
		return dao.avvistamenti(shape, anno);
	}
	
	public Map<State, Double> getDEFCON(int giorni, double alfa, String shape, int anno) {
		s.simula(this, giorni, alfa, shape, anno);
		return s.getDEFCON();
	}
}
