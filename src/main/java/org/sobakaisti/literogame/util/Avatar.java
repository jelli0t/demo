/**
 * 
 */
package org.sobakaisti.literogame.util;

/**
 * @author nemanja
 *
 */
public enum Avatar {
	PINSCHER("Pinscher"),
	BULLDOG("Bulldog"),
	SHIBA_INU("Shiba Inu"),
	LABRADOR("Labrador"),
	POODLE("Poodle");
	
	private String name;

	private Avatar(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toLower() {
		return this.name.toLowerCase();
	}
}