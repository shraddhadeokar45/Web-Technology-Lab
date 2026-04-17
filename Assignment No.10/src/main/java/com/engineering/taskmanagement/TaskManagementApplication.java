package com.engineering.taskmanagement;

import com.engineering.taskmanagement.model.Employee;
import com.engineering.taskmanagement.model.Task;
import com.engineering.taskmanagement.repository.EmployeeRepository;
import com.engineering.taskmanagement.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
		return args -> {
			// Create dummy employees
			Employee emp1 = new Employee("Dr. Sarah Jenkins", "sarah.j@college.edu", "Computer Science");
			Employee emp2 = new Employee("Prof. Alan Turing", "alan.t@college.edu", "Electrical Engineering");
			Employee emp3 = new Employee("Ms. Grace Hopper", "grace.h@college.edu", "Information Technology");
			
			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);

			// Create initial tasks
			Task t1 = new Task("Midterm Exam Duty", "Invigilation for CS-101 in Hall B", "Exam Duty", LocalDate.now().plusDays(5), "Pending", emp1);
			Task t2 = new Task("Project Guide Allocation", "Assign AI project topics to final year students and complete documentation", "Project Guide", LocalDate.now().plusDays(2), "In Progress", emp2);
			Task t3 = new Task("Annual Event Coordination", "Coordinate the TechFest event schedule and budget planning", "Event Coordination", LocalDate.now().minusDays(1), "Completed", emp3);
			
			taskRepository.save(t1);
			taskRepository.save(t2);
			taskRepository.save(t3);
		};
	}
}
