package com.engineering.taskmanagement.controller;

import com.engineering.taskmanagement.model.Task;
import com.engineering.taskmanagement.repository.EmployeeRepository;
import com.engineering.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskController(TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Task> listTasks = taskRepository.findAll();
        model.addAttribute("listTasks", listTasks);
        return "index";
    }

    @GetMapping("/showNewTaskForm")
    public String showNewTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        model.addAttribute("employees", employeeRepository.findAll());
        return "assign-task";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        model.addAttribute("employees", employeeRepository.findAll());
        return "assign-task";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable(value = "id") long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}
