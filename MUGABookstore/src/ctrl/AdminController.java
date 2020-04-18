package ctrl;

import bean.BookBean;
import bean.UserBean;
import dto.BooksSoldReportDTO;
import model.BookModel;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {
    private final BookModel bookModel;

    AdminController(final BookModel bookModel) {
        this.bookModel = bookModel;
    }


    @GetMapping(value = "/admin")
    public String adminDashboard(HttpSession session, Model m) {
    	if (session.getAttribute("loggedInUser") == null) {
    		m.addAttribute("admin", false);
    		return "home";
    	}
    	else {
	    	UserBean ub = (UserBean) session.getAttribute("loggedInUser"); 
	//        final ModelAndView mv = new ModelAndView("admin_dashboard");
	    	if (ub.getType().equals("Administrator")) {
	    		m.addAttribute("admin", true);
	            return "admin_dashboard";
	    	}
	    	else {
	    		m.addAttribute("admin", false);
	            return "home";
	    	}
    	}
    }

    @PostMapping(value = "/admin")
    public ModelAndView bookSoldReport(
            HttpSession session,
            HttpServletRequest request
    ) throws ParseException {
        final ModelAndView mv = new ModelAndView("admin_dashboard");
        if (request.getParameter("date") != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            TemporalAccessor date = formatter.parse(request.getParameter("date"));

            mv.addObject("reportDate", request.getParameter("date"));

            final List<BookBean> books = this.bookModel.listBooksSoldDuringMonth(
                    Year.from(date),
                    Month.from(date)
            );

            mv.addObject("books", books);

        }

        return mv;
    }
}
