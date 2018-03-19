package br.com.alex.prooffirstvowel.init;

public class StreamImpl implements Stream {
	
	private String entranceChar;

	private int lastIndex = -1;

	public StreamImpl(String entranceChar) {
		this.entranceChar = entranceChar;
	}

	public char getNext() {
		char next = entranceChar.charAt(++lastIndex);		
		return next;
	}

	public boolean hasNext() {
		try {
			entranceChar.charAt(lastIndex + 1);
			return true;
		} catch (IndexOutOfBoundsException ex) {
		}
		return false;
	}
}
