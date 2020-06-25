package it.polito.tdp.newufosightings.model;

public class Adiacenza {
	
	private State s1;
	private State s2;
	private int peso;
	
	public Adiacenza(State s1, State s2, int peso) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.peso = peso;
	}

	public State getS1() {
		return s1;
	}

	public State getS2() {
		return s2;
	}

	public int getPeso() {
		return peso;
	}
	
	

}
