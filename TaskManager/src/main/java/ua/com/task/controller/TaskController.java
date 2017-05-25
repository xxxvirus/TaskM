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

@Controller
@RequestMapping("/task/{id}")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public String task(Model model, @PathVariable int id){
		model.addAttribute("task", taskService.findOne(id));
		return "user-task";
	}
	
	@GetMapping("/editTask/{idd}")
	public String editTask(Model model, @PathVariable int id){
		model.addAttribute("task", taskService.findOne(id));
		return "user-editTask";
	}
	
	@PostMapping("/editTask/{idd}")
	public String saveTaskEdit(Task task){
		taskService.editTask(task);
		return "redirect:/task/{id}";
	}
	
	@GetMapping("/deleteTask/{idd}")
	public String deleteTask(@PathVariable int id){
		taskService.delete(id);
		return "redirect:/";
	}
}
