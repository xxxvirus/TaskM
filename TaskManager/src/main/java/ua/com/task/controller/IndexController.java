package ua.com.task.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.task.entity.Task;
import ua.com.task.entity.User;
import ua.com.task.service.PerformerService;
import ua.com.task.service.TaskService;
import ua.com.task.service.UserService;
import ua.com.task.validator.UserValidator;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private PerformerService performerService;

	@GetMapping
	public String index(Principal principal, Model model) {
//		if (principal != null) {
//			System.out.println(principal.getName());
//			SecurityContextHolder.getContext().getAuthentication()
//					.getPrincipal();
//		}
		model.addAttribute("tasks", taskService.findAllActive());
		return "user-index";
	}

	@InitBinder("user")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new UserValidator(userService));
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "user-registration";
	}

	@PostMapping("/registration")
	public String save(@ModelAttribute("user") @Valid User user,
			BindingResult br, Model model) {
		if (br.hasErrors())
			return "user-registration";
		userService.save(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "user-login";
	}
	
	@RequestMapping("/createTask")
	public String creteTask(Model model){
		model.addAttribute("task", new Task());
		return "user-createTask";
	}
	
	@PostMapping("/createTask")
	public String saveTask(@ModelAttribute("task") Task task){
		taskService.createTask(task);
		return "redirect:/";
	}
	
	@GetMapping("/editTask/{id}")
	public String editTask(Model model, @PathVariable int id){
		model.addAttribute("task", taskService.findOne(id));
		return "user-editTask";
	}
	
	@PostMapping("/editTask/{id}")
	public String saveTaskEdit(Task task){
		taskService.editTask(task);
		return "redirect:/";
	}
	
	@GetMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable int id){
		taskService.delete(id);
		return "redirect:/";
	}
	
//	@GetMapping("/join/{id}")
//	public String join(@ModelAttribute("task") Task task, @PathVariable int id){
//		taskService.joinToTask(task, id);
//		return "redirect:/task/{id}";
//	}
	
//	@GetMapping("/exit/{id}")
//	public String exit(@ModelAttribute("task") Task task, @PathVariable int id){
//		taskService.exitFromTeam(task, id);
//		return "redirect:/";
//	}

}
