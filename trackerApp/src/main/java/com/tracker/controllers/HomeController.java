package com.tracker.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.tracker.models.DateFilter;
import com.tracker.models.LoginUser;
import com.tracker.models.SaleFile;
import com.tracker.models.User;
import com.tracker.services.SaleFileService;
import com.tracker.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
    private UserService userServ;
	
	@Autowired
	private SaleFileService saleFileServ;
	
	@GetMapping("/")
    public String index(Model model) {
    
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "login.jsp";
    }
    
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, 
            Model model, 
            HttpSession session) {
        
        // Validations and create a new user!
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }
        
        // No errors! 
        userServ.register(newUser, result);
        // Log them in.
        session.setAttribute("loggedInUser", newUser);
        return "redirect:/dashboard";
    }
    
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult result, 
            Model model, 
            HttpSession session) {
        
		// Check for errors on the login
    	User loggedInUser = userServ.login(newLogin, result);
        if(result.hasErrors()) {
        	model.addAttribute("newUser", new User());
            return "login.jsp";
        }
    
        // No errors! 
        
        // Log them in.
        session.setAttribute("loggedInUser", loggedInUser);
        return "redirect:/dashboard";
    }
    
	
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute("newSaleFile") SaleFile newSaleFile,
			Model model, 
            HttpSession session) {
		if(session.getAttribute("loggedInUser")!=null) {
			User user = (User)session.getAttribute("loggedInUser");
			Long userId = user.getId();
			model.addAttribute("saleFiles", saleFileServ.tenMostRecent(userId));
			return "dashboard.jsp";
		}
		else {
			return"redirect:/";
		}
		
	}
	
	@PostMapping("/saveSale")
	public String saveSale(@Valid @ModelAttribute("newSaleFile") SaleFile newSaleFile,
			BindingResult result,
			Model model, 
            HttpSession session) {
		
		if(result.hasErrors()) {
			User user = (User)session.getAttribute("loggedInUser");
			Long userId = user.getId();
			model.addAttribute("saleFiles", saleFileServ.allSaleFilesByUserDateDesc(userId));
			return "dashboard.jsp";
		}
		else {
			saleFileServ.create(newSaleFile, result);
		}
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/previousSales")
	public String previousSales(Model model, 
            HttpSession session,
            @ModelAttribute("filterDates") DateFilter filterDates) {
		if(session.getAttribute("loggedInUser")!=null) {
			User user = (User)session.getAttribute("loggedInUser");
			Long userId = user.getId();
			model.addAttribute("saleFiles", saleFileServ.twofiftyMostRecent(userId));
			return "previousSales.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/salesFilter")
	public String salesFilter(Model model, 
            HttpSession session,
            @ModelAttribute ("filterDates") DateFilter filterDates){
		if(session.getAttribute("loggedInUser")!=null) {
			Date d1 = filterDates.getD1();
			Date d2 = filterDates.getD2();
			User user = (User)session.getAttribute("loggedInUser");
			Long userId = user.getId();
			model.addAttribute("saleFiles", saleFileServ.findBetweenDates(d1, d2, userId));
			return "previousSales.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/adminSalesFilter")
	public String adminSalesFilter(Model model, 
            HttpSession session,
            @ModelAttribute ("filterDates") DateFilter filterDates){
		if(session.getAttribute("loggedInUser")!=null) {
			Date d1 = filterDates.getD1();
			Date d2 = filterDates.getD2();
			Long userId = filterDates.getId();
			model.addAttribute("users", userServ.allUsers());
			List<SaleFile> saleFiles = saleFileServ.findBetweenDates(d1, d2, userId);
			model.addAttribute("saleFiles", saleFiles);
			int total=0;
			for(SaleFile sale : saleFiles) {
				total += sale.getSaleCount();
			}
			model.addAttribute("weekTotal", total);
			return "salesByUser.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/fileReviews")
	public String fileReviews(HttpSession session,
			Model model) {
		if(session.getAttribute("loggedInUser")!=null) {
			model.addAttribute("saleFiles", saleFileServ.needsToBeReviewed());
			return "review.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/sale/{id}")
	public String editSale(@PathVariable Long id,
			@ModelAttribute("editSaleFile") SaleFile editSaleFile,
			Model model, 
            HttpSession session) {
		if(session.getAttribute("loggedInUser")!=null) {
			SaleFile sale = saleFileServ.findById(id);
			String saleDate = new SimpleDateFormat("MM/dd/yyyy").format(sale.getSaleDate());
			model.addAttribute("sale", sale);
			model.addAttribute("saleDate", saleDate);
			return "editSale.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/sale/{id}/review")
	public String reviewFile(@PathVariable Long id,
			HttpSession session) {
		if(session.getAttribute("loggedInUser")!=null) {
			saleFileServ.review(saleFileServ.findById(id));
			return "redirect:/fileReviews";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PutMapping("/sale/{id}/update")
	public String updateSale(@PathVariable Long id,
			@Valid @ModelAttribute("editSaleFile") SaleFile editSaleFile,
			BindingResult result,
			Model model, 
            HttpSession session) {
		if(result.hasErrors()) {
			SaleFile sale = saleFileServ.findById(id);
			String saleDate = new SimpleDateFormat("MM/dd/yyyy").format(sale.getSaleDate());
			model.addAttribute("sale", sale);
			model.addAttribute("saleDate", saleDate);
			return "editSale.jsp";
		}
		else {
			saleFileServ.update(editSaleFile, result);
		}
		return "redirect:/dashboard";
	}
	
	@GetMapping("/sale/{id}/delete")
	public String delete(@PathVariable Long id,
    		HttpSession session) {
		if(session.getAttribute("loggedInUser")!=null) {
			saleFileServ.delete(id);
			return "redirect:/dashboard";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
	
}
