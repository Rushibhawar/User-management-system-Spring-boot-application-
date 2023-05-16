package com.spring.boot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.entity.Messages;
import com.spring.boot.entity.User;
import com.spring.boot.repository.UserRepository;
import com.spring.boot.serviceImpl.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping("/")
	public String login() {
		System.out.println("loginPage page");
		return "loginPage";
	}
	@RequestMapping("/home")
	public String home(Model model, @RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "5")int selectedSize,
			@RequestParam(defaultValue = "userFirstName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,@RequestParam(defaultValue = "false")boolean isActive) {
		
		// print out the current page number for debugging purposes
		System.out.println("Home page & page no : "+page);
		
		
	    // retrieve a page of users based on the specified page number, size, sorting criteria, and active status
		Page<User> userPage = userServiceImpl.findPaginated(page-1, selectedSize, sortBy, sortDir,isActive);
		
	    // add the list of users to the model, along with various pagination information
		model.addAttribute("userList", userPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", selectedSize);
		model.addAttribute("totalPages", userPage.getTotalPages());
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortDir", sortDir);	
		 
		//for debugging process
		System.out.println("list  : "+userPage.getContent());
		
	    // return the name of the view to render
		return "home";
	}
	
	
	/*
	 * This method handles the requests for adding a new user or editing an existing one.
	 * If the userId is 0, it means that a new user is being added and the 'addUser' view is returned.
	 * Otherwise, it means that an existing user is being edited and the user details are fetched
	 * from the database using the userId. The user object is added to the model and the 'addUser' view
	 * is returned.
	 *
	 * @param userId: the id of the user being edited, can be null
	 * @param model: the model object to be populated with data for the view
	 *
	 */
	@RequestMapping("/addUser")
	public String addUser(@RequestParam(value = "id", required = false) Long userId, Model model) {

		if(userId == 0) {
	        // Creating a new user object
			User u =  new User();
			u.setUserId((long) 0);
			model.addAttribute("user",u); // Adding the user object to the model
			System.out.println("user id is 0 : "+userId);
			return "addUser"; // Returning the 'addUser' view
		}
		else {
			// Fetching the user details from the database using the userId
			User user = userServiceImpl.getUserById(userId);
			
			model.addAttribute("user", user);  // Adding the user object to the model
			System.out.println("user id is not 0 :"+userId);
			System.out.println("user obj : "+user);
			return "addUser"; // Returning the 'addUser' view
		}
	}
	
	
	/*
	 * This method is used to save or update a user object. It takes a User object,
	 * a Long userId, and a Model object as parameters. If the userId is null or 0,
	 * it means a new user is being created, so it sets the address of the user
	 * object and saves it to the database. After saving, it redirects to the home
	 * page. If the userId is not null or 0, it means an existing user is being
	 * updated. In this case, it updates the user object with the provided userId
	 * and saves it to the database.
	 */
	@RequestMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "id", required = false,defaultValue = "0")Long userId, Model model) {
		System.out.println("url rewritting uId : "+userId);
		
		// Check if userId is null or 0 (i.e., new user is being created)
		if(userId == null || userId == 0 ) {
			System.out.println("creating new user");
			
			// Set the address type for the user's addresses
			user.getAddress().get(0).setIsPermanentOrCurrent("permanent");
			user.getAddress().get(1).setIsPermanentOrCurrent("correspondence");
			userServiceImpl.saveOrUpdateUser(user);  // Save the new user

			return "redirect:/home"; // Redirect to the home page
		}
		else {
			 // If userId is not null or 0 (i.e., existing user is being updated)
			System.out.println("Updating exiting user");
			
			// Set the address type for the user's addresses
			user.getAddress().get(0).setIsPermanentOrCurrent("permanent");
			user.getAddress().get(1).setIsPermanentOrCurrent("correspondence");
			
			 // Update the user with the new details
			boolean check = userServiceImpl.updateUser(user, userId);
			if(check) {
				System.out.println("updated success");
			}
			else {
				System.out.println("not updated");
			}
			// Redirect to the home page
			return "redirect:/home";
		}
		
	}
	
	/*This function soft deletes the user i.e it sets isActive to true
	 * and updates the user
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam(value = "id", required = false)Long userId, Model model) {
		System.out.println("user id : "+userId);
		User user =  userServiceImpl.getUserById(userId);
		System.out.println("user :" +user);
		user.setActive(true);
		userServiceImpl.saveOrUpdateUser(user);
		return "redirect:/home";
		
	}
	
	/*
	 * This method handles a request to display a list of inactive users. It
	 * takes in parameters for pagination and sorting, and a boolean value for
	 * filtering inactive users. It then retrieves a paginated list of inactive
	 * users from the userServiceImpl, and adds the list and pagination information
	 * to the model. Finally, it returns the name of the view template
	 * ("inactiveUsers"), which will display the list of inactive users.
	 */	
	@RequestMapping("/inactiveUsers")
	public String inactiveUsers(Model model, @RequestParam(defaultValue = "1")int page,
			@RequestParam(defaultValue = "userFirstName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,@RequestParam(defaultValue = "true")boolean isActive) {
		
		System.out.println("inactive page & page no : "+page);
		
		int size = 3;
		Page<User> userPage = userServiceImpl.findPaginated(page-1, size, sortBy, sortDir,isActive);
		model.addAttribute("userList", userPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
		model.addAttribute("totalPages", userPage.getTotalPages());
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortDir", sortDir);	
		
		System.out.println("list  : "+userPage.getContent());
		
		return "inactiveUsers";
	}
	
	
	@RequestMapping("/hardDeleteUser")
	public String hardDeleteUser(@RequestParam(value = "id", required = false)Long userId, Model model) {
		System.out.println("user id : "+userId);
		User user =  userServiceImpl.getUserById(userId);
		System.out.println("user :" +user);
		user.setActive(true);
		userServiceImpl.deleteUser(userId);
		return "redirect:/home";
		
	}
	
	@RequestMapping("/restoreUser")
	public String restoreUser(@RequestParam(value = "id", required = false)Long userId, Model model) {
		System.out.println(" user id of user to be restore: "+userId);
		User user =  userServiceImpl.getUserById(userId);
		System.out.println("user to be restored  :" +user);
		user.setActive(false);
		userServiceImpl.saveOrUpdateUser(user);
		return "redirect:/home";
		
	}
	
	@PostMapping("/saveUserUsingJakson")
	public ResponseEntity<String> saveUser1(@RequestBody User User){
		   
				userRepository.save(User);
	            return ResponseEntity.ok().body("User saved successfully");
	}
	
}
