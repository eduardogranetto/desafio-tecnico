package br.com.desafiotecnico.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AssertTest {

	private DoIt doit;
	
	@Before
	public void setup(){
		this.doit = new DoIt();
	}
	
	@Test
	public void shouldExecuteDo() {
		this.doit.doIt(false);
	}

}
