package ua.com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.task.entity.Report;
import ua.com.task.entity.Task;
import ua.com.task.entity.User;
import ua.com.task.service.PerformerService;
import ua.com.task.service.ReportService;
import ua.com.task.service.TaskService;

@Controller
@RequestMapping("/task/{id}")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private PerformerService performerService;
	@Autowired
	private ReportService reportService;
	
	@ModelAttribute("report")
	public Report getReport() {
		return new Report();
	}

	@GetMapping
	public String task(Model model, @PathVariable int id) {
		model.addAttribute("task", taskService.findOne(id));
		model.addAttribute("taskPerf", taskService.findPerformersWhoJoin(id));
		model.addAttribute("reports", reportService.findByTaskId(id));
		model.addAttribute("isImPerformer", performerService.isIamPerformer(id));
		return "user-task";
	}

	@GetMapping("/editTask")
	public String editTask(Model model, @PathVariable int id) {
		model.addAttribute("task", taskService.findOne(id));
		return "user-editTask";
	}

	@PostMapping("/editTask")
	public String saveTaskEdit(Task task) {
		taskService.editTask(task);
		return "redirect:/task/{id}";
	}

	@GetMapping("/deleteTask")
	public String deleteTask(@PathVariable int id) {
		taskService.delete(id);
		return "redirect:/";
	}

	@GetMapping("/join")
	public String join(@ModelAttribute("task") Task task, @PathVariable int id) {
		taskService.joinToTask(task, id);
		return "redirect:/task/{id}";
	}

	@GetMapping("/exit")
	public String exit(@ModelAttribute("task") Task task, @PathVariable int id) {
		taskService.exitFromTeam(task, id);
		return "redirect:/task/{id}";
	}

	@GetMapping("/finish")
	public String finish(@ModelAttribute("task") Task task, @PathVariable int id) {
		taskService.finishTask(task, id);
		return "redirect:/task/{id}";
	}

	@PostMapping
	public String addReport(@ModelAttribute("report") Report report,
			@PathVariable int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		report.setUser(user);
		Task task = taskService.findOne(id);
		report.setTask(task);
		reportService.save(report);
		return "redirect:/task/{id}";
	}
	
	@GetMapping("/delete/{idd}")
	public String delReport(@PathVariable int idd) {
		reportService.delete(idd);
		return "redirect:/task/{id}";
	}
}
