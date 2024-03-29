package hello.thymeleaf.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

@Controller
@RequestMapping("/basic")
public class BasicController {

	@GetMapping("/text-basic")
	 public String textBasic(Model model) {
	 model.addAttribute("data", "Hello Spring!");
	 return "basic/text-basic";
	 }
	
	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
	 model.addAttribute("data", "Hello <b>Spring!</b>");
	 return "basic/text-unescaped";
	}
	
	@GetMapping("/variable")
	public String variable(Model model) {
		 User userA = new User("userA", 10);
		 User userB = new User("userB", 20);
		 
		 List<User> list = new ArrayList<>();
		 list.add(userA);
		 list.add(userB);
		 
		 Map<String, User> map = new HashMap<>();
		 map.put("userA", userA);
		 map.put("userB", userB);
		 
		 model.addAttribute("user", userA);
		 model.addAttribute("users", list);
		 model.addAttribute("userMap", map);
		 
		 return "basic/variable";
	}
	
	@GetMapping("/basic-objects")
	public String basicObjects(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
		session.setAttribute("sessionData", "Hello Session");
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("servletContext", request.getServletContext());
		return "basic/basic-objects";
	}
	
	@Component("helloBean")
	static class HelloBean{
		public String hello(String data) {
			return "Hello " + data;
		}
	}
	
	
	@Data
	static class User {
		private String username;
		private int age;
		
		public User(String username, int age) {
			this.username=username;
			this.age=age;
		}
	}
}




























