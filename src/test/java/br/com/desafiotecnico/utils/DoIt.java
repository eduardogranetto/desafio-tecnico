package br.com.desafiotecnico.utils;

public class DoIt {

	public void doIt(boolean force){
		assert force : "It's not possible";
		System.out.println(force);
	}
	
}