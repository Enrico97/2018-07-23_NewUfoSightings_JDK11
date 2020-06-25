package it.polito.tdp.newufosightings.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;

import it.polito.tdp.newufosightings.model.Event.eventType;

public class Simulator {
	
	Model model;
	int giorni;
	double alfa;
	PriorityQueue <Event> queue = new PriorityQueue<>();
	Map<State, Double> DEFCON;
	
	public void simula(Model model, int giorni, double alfa, String shape, int anno) {
		this.model=model;
		this.giorni=giorni;
		this.alfa=alfa;
		queue.clear();
		DEFCON = new HashMap<>();
		for(State s : model.creaGrafo(shape, anno).vertexSet()) {
			DEFCON.put(s, 5.0);
		}
		for(Avvistamento a : model.avvistamenti(shape, anno)) {
			if(model.creaGrafo(shape, anno).vertexSet().contains(a.getStato())) {
				Event e = new Event(a.getStato(), eventType.avvistamento, a.getTempo());
				System.out.println(e);
				queue.add(e);
		}}
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e, shape, anno);
	}
	}

	private void processEvent(Event e, String shape, int anno) {
		switch(e.getTipo()) {
		
		case avvistamento:
			if(DEFCON.get(e.getStato())>=2.0)
				DEFCON.put(e.getStato(), DEFCON.get(e.getStato())-1.0);
			if(Math.random()>=alfa/100) {
				for(State s : Graphs.neighborListOf(model.creaGrafo(shape, anno), e.getStato())) {
					if(DEFCON.get(s)>=1.5)
						DEFCON.put(s, DEFCON.get(s)-0.5);
					Event ev = new Event(s, eventType.avvistamentoVicino, e.getTempo().plusDays(giorni));
					System.out.println(ev);
					queue.add(ev);
					}
			}
			Event evento = new Event(e.getStato(), eventType.cessataAllerta, e.getTempo().plusDays(giorni));
			System.out.println(evento);
			queue.add(evento);
			break;
		
		case cessataAllerta:
			if(DEFCON.get(e.getStato())<=4.0)
				DEFCON.put(e.getStato(), DEFCON.get(e.getStato())+1.0);
			break;
			
		case avvistamentoVicino:
			if(DEFCON.get(e.getStato())<=4.5)
				DEFCON.put(e.getStato(), DEFCON.get(e.getStato())+0.5);
			break;
		}
		
	}

	public Map<State, Double> getDEFCON() {
		return DEFCON;
	}
	
	

}
