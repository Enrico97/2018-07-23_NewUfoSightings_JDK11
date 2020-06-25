package it.polito.tdp.newufosightings.model;

import java.time.LocalDateTime;

public class Avvistamento {
	
	private State stato;
	private LocalDateTime tempo;
	
	public Avvistamento(State stato, LocalDateTime tempo) {
		super();
		this.stato = stato;
		this.tempo = tempo;
	}

	public State getStato() {
		return stato;
	}

	public LocalDateTime getTempo() {
		return tempo;
	}
	
	

}
