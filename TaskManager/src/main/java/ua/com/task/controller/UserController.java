package ua.com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.task.entity.Task;
import ua.com.task.service.TaskService;
import ua.com.task.service.UserService;

@Controller
@RequestMapping("/user/{id}")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public String user(Model model, @PathVariable int id) {
		model.addAttribute("users", userService.findOne(id));
		model.addAttribute("tasks", taskService.findByUserId(id));
		return "user-user";
	}
	
	@GetMapping("/editTask/{idd}")
	public String editTask(Model model, @PathVariable int idd){
		model.addAttribute("task", taskService.findOne(idd));
		return "user-editTask";
	}
	
	@PostMapping("/editTask/{idd}")
	public String saveTaskEdit(Task task, @PathVariable int id){
		taskService.editTask(task);
		return "redirect:/user/{id}";
	}
	
	@GetMapping("/deleteTask/{idd}")
	public String deleteTask(@PathVariable int id, @PathVariable int idd){
		taskService.delete(idd);
		return "redirect:/user/{id}";
	}
	
}
