package ua.com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.task.entity.Task;
import ua.com.task.service.TaskService;
import ua.com.task.service.UserService;

@Controller
@RequestMapping("/user/{id}/active")
public class ActiveTaskController {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@GetMapping
	public String active(Model model, @PathVariable int id) {
		model.addAttribute("users", userService.findOne(id));
		model.addAttribute("tasks", taskService.findByUserIdactive(id));
		return "user-active";
	}

	@GetMapping("/exit/{idd}")
	public String exit(@ModelAttribute("task") Task task,
			@PathVariable int idd, Model model, @PathVariable int id) {
		taskService.exitFromTeam(task, idd);
		return active(model, id);
	}

}
