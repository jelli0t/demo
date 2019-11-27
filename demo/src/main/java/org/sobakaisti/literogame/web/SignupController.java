package org.sobakaisti.literogame.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.sobakaisti.literogame.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/signup")
@SessionAttributes(names = {"player"})
public class SignupController {
	public static final String PLAYER_ATTR_NAME = "player";
	public static final String PLAYER_USERNAME_REGEX = "^[\\w\\p{L}-_]{2,16}$";

	@RequestMapping(method=RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute(PLAYER_ATTR_NAME, new Player());
		return "registration";
	}
	
	@RequestMapping(value="/validate/{field}", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<FieldError> validatePlayer(
			@PathVariable String field,
			@Valid @ModelAttribute Player player, BindingResult result) {
		if(result.hasErrors() && result.getFieldError(field) != null) {			
			return new ResponseEntity<FieldError>(result.getFieldError(field), HttpStatus.NOT_ACCEPTABLE);
		}		
		return new ResponseEntity<FieldError>(new FieldError("player", "", ""), HttpStatus.OK);
	}
	
	@RequestMapping(value="/next/{step}", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
	public String next(@PathVariable String step, Model model) {	
		if(!model.containsAttribute(PLAYER_ATTR_NAME)) {
			model.addAttribute(PLAYER_ATTR_NAME, new Player());
		}
		if(step.equals("soby"))
			appendSobyResponse(model);
		return "fragments/signup_fragments :: " + step;
	}
		
	private void appendSobyResponse(Model model) {
		if(model.containsAttribute(PLAYER_ATTR_NAME)) {
			Player player = (Player) model.asMap().get(PLAYER_ATTR_NAME);
			int signed = player.getSigned();
			List<String> messages = new ArrayList<String>() {{
				switch (player.getSigned()) {
				case 1:
					add("Nisi pročitao Uslove korišćenja.");
					add("U laži su kratke noge… ");
					add("A moji zubi mogu lako da ti se nađu pod grlom.");
					break;
				case 2:
					add("Očigledno ste obdareni sposobnošću brzog čitanja. ");
					add("To je dobro…");
					add("Biće puno prilike da dokažete tu veštinu u budućnosti!");
					break;
				case 3:
					add("Hvala što ste pročitali uslove korišćenja!");
					add("Zaslužili ste prvi bedž od Simulacrum Inc.");
					break;
				}
			}};
			model.addAttribute("soby_response", messages);
		}
	}
}