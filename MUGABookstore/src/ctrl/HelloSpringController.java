package ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@Controller
public class HelloSpringController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView returnHome() {
		String query = "select * from book";
		
		List<?> results = jdbcTemplate.queryForList(query);
		System.out.println(results.toString());
		
		return new ModelAndView("home");
	}
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		String message = "Welcome….";
		return new ModelAndView("welcome", "helloMessage", message);
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}