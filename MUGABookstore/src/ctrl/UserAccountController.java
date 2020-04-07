package ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAccountController {
	
	@RequestMapping(value="/user_account", method=RequestMethod.GET)
	public String showUserAccount() {
		return "user_account";
	}

}
