package br.com.alex.prooffirstvowel.test;


import org.junit.Test;

import br.com.alex.prooffirstvowel.init.Initial;

import static org.junit.Assert.assertEquals;
public class VowelTest {
	
	 @Test
	 public void proofCase() {
	     assertEquals('e', Initial.initialCall("aAbBABacafe"));
	 }
	 
	 
	 @Test
	 public void anotherCase() {
	     assertEquals('e', Initial.initialCall("aAbBABaafeccca"));
	 }
	 
	 
	 @Test
	 public void anotherCase2() {
	     assertEquals('u', Initial.initialCall("aAbBABabuacg"));
	 }
	 
	 

}
