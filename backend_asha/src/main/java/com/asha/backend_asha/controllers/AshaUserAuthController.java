package com.asha.backend_asha.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asha.backend_asha.models.AshaUser;
import com.asha.backend_asha.models.Role;
import com.asha.backend_asha.repositories.AshaUserRepository;
import com.asha.backend_asha.repositories.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ashaUserAuth")
public class AshaUserAuthController {
	private static final Logger logger = LogManager.getLogger(AshaUserAuthController.class);

	@Autowired
	private AshaUserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public String register(@RequestBody AshaUser user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			return "Username already exists";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName("USER").orElse(new Role());
		userRole.setName("USER");
		// roleRepository.save(userRole);
		user.setRoles(Set.of(userRole));
		userRepository.save(user);

		return "User registered successfully!";
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(HttpServletRequest request, @RequestBody AshaUser user) {
		Optional<AshaUser> existingUser = userRepository.findByUsername(user.getUsername());
		logger.info("This is an info message.");
		if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {

			// Create Authentication object
			Authentication authentication = new UsernamePasswordAuthenticationToken(existingUser.get(), // Principal
																										// (authenticated
																										// user)
					null, // No credentials needed here
					existingUser.get().getRoles() // Authorities/roles
			);

			// Set the authentication context
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("Session ID: " + request.getSession().getId());
			request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

			// Return the authenticated user (sanitize as needed for response)
			return ResponseEntity.ok(existingUser.get());
		}

		// Return a generic error response for invalid credentials
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}
}
