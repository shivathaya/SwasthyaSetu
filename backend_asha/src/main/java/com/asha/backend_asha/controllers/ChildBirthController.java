package com.asha.backend_asha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asha.backend_asha.models.AshaUser;
import com.asha.backend_asha.models.ChildBirth;
import com.asha.backend_asha.repositories.ChildBirthRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ChildBirthController {

	@Autowired
	private ChildBirthRepository childBirthRepository;

	@PostMapping("/childbirths")
	public ResponseEntity<?> saveChildBirth(@RequestBody ChildBirth childBirth, HttpSession session,
			HttpServletRequest request) {
		System.out.println("Session ID: " + session.getId());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Principal principal = request.getUserPrincipal();
		if (principal instanceof AshaUser) {
			AshaUser loggedInUser = (AshaUser) principal;
			childBirth.setUserId(loggedInUser.getId());
			childBirthRepository.save(childBirth);
			return ResponseEntity.ok(childBirth);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not authenticated!");
		}
	}

	@GetMapping("/childbirths")
	public ResponseEntity<List<ChildBirth>> getAllChildBirths() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AshaUser loggedInUser = (AshaUser) principal;
		if (principal instanceof AshaUser) {
			List<ChildBirth> childbirths = childBirthRepository.findByUserId(loggedInUser.getId());
			return ResponseEntity.ok(childbirths);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}
