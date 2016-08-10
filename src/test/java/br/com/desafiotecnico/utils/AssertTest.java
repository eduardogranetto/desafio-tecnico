package br.com.desafiotecnico.utils;

import org.junit.Before;
import org.junit.Test;

public class AssertTest {

	private DoIt doit;
	
	@Before
	public void setup(){
		this.doit = new DoIt();
	}
	
	@Test(expected=AssertionError.class)
	public void shouldExecuteDo() {
		this.doit.doIt(false);
	}

}
