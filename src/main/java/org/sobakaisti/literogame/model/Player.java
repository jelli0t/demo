package org.sobakaisti.literogame.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.sobakaisti.literogame.util.Avatar;
import org.sobakaisti.literogame.util.Gender;

public class Player {
	public static final String PLAYER_USERNAME_REGEX = "^[\\w\\p{L}-_]{2,16}$"; //"^[\\w-]{3,16}$"

	private long id;
	
	@Size(min=2, max=16, message="Molimo unesite korisnicko ime")
	@Pattern(regexp = PLAYER_USERNAME_REGEX, message="Unesite korisnicko ime u ispravnom formatu")
	private String username;
	
	@NotNull(message = "Molimo odaberite pol vase konzole")
	private Gender gender;
	
	private Avatar avatar;
	
	@Min(value = 1, message = "Molimo potvrdite saglasnost")
	private int signed;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username != null && username.length() > 0) {
			this.username = username.trim();
		}
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public int getSigned() {
		return signed;
	}

	public void setSigned(int signed) {
		this.signed = signed;
	}
	
	
}