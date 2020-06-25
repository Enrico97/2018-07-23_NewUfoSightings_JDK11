package it.polito.tdp.newufosightings.model;

import java.time.LocalDateTime;

public class Event implements Comparable <Event>{
	
	public enum eventType {
		avvistamento, cessataAllerta, avvistamentoVicino;
	}
	
	private State stato;
	private eventType tipo;
	private LocalDateTime tempo;
	
	public Event(State stato, eventType tipo, LocalDateTime tempo) {
		super();
		this.stato = stato;
		this.tipo = tipo;
		this.tempo = tempo;
	}

	public State getStato() {
		return stato;
	}

	public eventType getTipo() {
		return tipo;
	}

	public LocalDateTime getTempo() {
		return tempo;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.getTempo().compareTo(o.getTempo());
	}

	@Override
	public String toString() {
		return stato + " " + tipo + " " + tempo;
	}
	
	
	

}
