/**
 * 
 */
package org.sobakaisti.literogame.util;

/**
 * @author nemanja
 *
 */
public enum Gender {
	M("male", 'M'),
	F("female", (char) 0x017d),
	T("", 'T');
	
	private String name;
	private char rsInitial; 
	
	private Gender(String name, char rsInitial) {
		this.name = name;
		this.rsInitial = rsInitial;
	}
	
	public String getName() {
		return name;
	}

	public char getRsInitial() {
		return rsInitial;
	}
		
}