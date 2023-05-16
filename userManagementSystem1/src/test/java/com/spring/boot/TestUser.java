package com.spring.boot;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.controller.UserController;
import com.spring.boot.entity.Address;
import com.spring.boot.entity.User;
import com.spring.boot.repository.UserRepository;
import com.spring.boot.serviceImpl.UserServiceImpl;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional	
@DisplayNameGeneration(DisplayNameGenerator.Simple.class) // to display the test cases function name accordingly, 
//we can also do it for specific function using @DisplayName("Get list of all users") and specify your own test case name for the function
@TestMethodOrder(MethodOrderer.MethodName.class) // this is to order our test cases and also we can use 
// @Order annotation to specify the order of the test cases where the lowest number ha highest priority
// then we need to add @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUser {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;

    @Autowired
    private UserController controller;
    


    @Mock
    private Model model;
    
    private User testUser;
    private User testUser1;
    private Address address1;
    private Address address2;
    
    
    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUserId(1L);
        testUser.setUserFirstName("John");
        testUser.setUserLastName("Doe");
        testUser.setUserEmail("john.doe@example.com");
        testUser.setUserPassword("password");
        testUser.setUserMobileNumber("1234567890");
        testUser.setUserDateOfBirth(new Date());
        testUser.setActive(false);
        testUser.setUserCreatedDate(new Date());
        
        address1 = new Address();
        address1.setAddressId(1L);
        address1.setIsPermanentOrCurrent("permanent");
        address1.setAddressStreet("123 Main St.");
        address1.setAddressLine1("Apt. 4B");
        address1.setAddressLine2("Block 4");
        address1.setAddressCity("Anytown");
        address1.setAddressState("CA");
        address1.setAddressPincode("12345");
        
        address2 = new Address();
        address2.setAddressId(2L);
        address2.setIsPermanentOrCurrent("correspondence");
        address2.setAddressStreet("123 Main St.");
        address2.setAddressLine1("Apt. 4B");
        address2.setAddressLine2("Block 4");
        address2.setAddressCity("Anytown");
        address2.setAddressState("CA");
        address2.setAddressPincode("12345");
        
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);
        testUser.setAddress(addressList);
        
        
        
        testUser1 = new User();
        testUser1.setUserId(2L);
        testUser1.setUserFirstName("John1");
        testUser1.setUserLastName("Doe1");
        testUser1.setUserEmail("john1.doe@example.com");
        testUser1.setUserPassword("password");
        testUser1.setUserMobileNumber("1234567890");
        testUser1.setUserDateOfBirth(new Date());
        testUser1.setActive(false);
        testUser1.setUserCreatedDate(new Date());
        
        List<Address> addressList1 = new ArrayList<>();
        addressList1.add(address1);
        addressList1.add(address2);
        testUser1.setAddress(addressList1);
        
        
        
    }

    
    
    //Test case for getAllUsers()
    @Test
    //@DisplayName("Get list of all users")
    public void testGetAllUsers() {
    	userRepository.save(testUser);
    	userRepository.save(testUser1);
        List<User> userList = Arrays.asList(testUser, testUser1);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();
        
        assertAll("Verify getAllUsers()",
                () -> assertEquals(userList.size(), result.size()),
                () -> assertEquals(userList.get(0).getUserFirstName(), result.get(0).getUserFirstName()),
                () -> assertEquals(userList.get(0).getUserLastName(), result.get(0).getUserLastName()),
                () -> assertEquals(userList.get(0).getUserEmail(), result.get(0).getUserEmail()),
                () -> assertEquals(userList.get(0).getUserMobileNumber(), result.get(0).getUserMobileNumber()),
                () -> assertEquals(userList.get(0).getUserDateOfBirth(), result.get(0).getUserDateOfBirth()),
                () -> assertEquals(userList.get(1).getUserFirstName(), result.get(1).getUserFirstName()),
                () -> assertEquals(userList.get(1).getUserLastName(), result.get(1).getUserLastName()),
                () -> assertEquals(userList.get(1).getUserEmail(), result.get(1).getUserEmail()),
                () -> assertEquals(userList.get(1).getUserMobileNumber(), result.get(1).getUserMobileNumber()),
                () -> assertEquals(userList.get(1).getUserDateOfBirth(), result.get(1).getUserDateOfBirth())
            );
        
        assertEquals(userList.size(), result.size());
        assertEquals(userList.get(0).getUserFirstName(), result.get(0).getUserFirstName());
        assertEquals(userList.get(1).getUserLastName(), result.get(1).getUserLastName());
        assertEquals(userList.get(1).getUserEmail(), result.get(1).getUserEmail());
        assertEquals(userList.get(1).getUserMobileNumber(), result.get(1).getUserMobileNumber());
        assertEquals(userList.get(1).getUserDateOfBirth(), result.get(1).getUserDateOfBirth());
    }

    //Test case for getUserById()
    @Test
    public void testGetUserById() {
        Long userId = 1L;
        userRepository.save(testUser);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        User result = userService.getUserById(userId);

        assertEquals(testUser.getUserFirstName(), result.getUserFirstName());
        assertEquals(testUser.getUserLastName(), result.getUserLastName());
        assertEquals(testUser.getUserEmail(), result.getUserEmail());
        assertEquals(testUser.getUserDateOfBirth(), result.getUserDateOfBirth());
        assertEquals(testUser.getUserMobileNumber(), result.getUserMobileNumber());
    }


    //Test case for saveOrUpdateUser()
    @Test
    //@DisplayName("User saved or not")
    public void testSaveOrUpdateUser() {
		
		 
        // When
        userService.saveOrUpdateUser(testUser);

        // Then
        verify(userRepository, times(1)).save(testUser);
        assertNotNull(testUser.getUserCreatedDate());
        assertNotNull(testUser.getUserEmail());
        assertNotNull(testUser.getUserFirstName());
        assertNotNull(testUser.getUserLastName());
    }
    
 // Define a test method for the deleteUser() method
    @Test
    //@DisplayName("User delete temporarily or not")
    public void testDeleteUserIsActive() throws Exception {
		/*
		 * when(userRepository.save(testUser)).thenReturn(testUser);
		 * userRepository.save(testUser); // arrange long id = 1L; User user = testUser;
		 * System.out.println("user isd : " + user.getUserId());
		 * user.setUserId(testUser.getUserId()); user.setActive(true); Optional<User>
		 * optionalUser = Optional.of(user);
		 */
          
          String endpoint = "/deleteUser";
          // Write a test that sends a mock HTTP request to the endpoint and validates the response
          MvcResult result = mockMvc.perform(get(endpoint)
        		  .param("id", Long.toString(1L)))
        		  .andExpect(redirectedUrl("/home"))
        		  .andReturn();
			
			/*
			 * String viewName = controller.deleteUser(testUser.getUserId(), model);
			 */			 			 
          
          // assert  
          String redirectedUrl = result.getResponse().getRedirectedUrl();
          assertEquals("/home", redirectedUrl);	
			/* assertTrue(user.isActive()); */
      }
    
  
    //Test case for deleteUser()
    @Test
    //@DisplayName("User permanently deleted or not")
    public void testDeleteUser() throws Exception {
        Long userId = 1L;

        String endpoint = "/hardDeleteUser";
        MvcResult mvcResult = mockMvc.perform(get(endpoint)
        		.param("id", testUser.getUserId().toString()))
        		.andExpect(redirectedUrl("/home"))
        		.andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        String viewName = modelAndView.getViewName();
		/*
		 * String redirectUrl = mvcResult.getResponse().getRedirectedUrl();
		 * assertEquals("/home", redirectUrl);
		 */
        assertEquals("redirect:/home", viewName);
		
    }

    //Test case for updateUser()
    @Test
    //@DisplayName("Users updated or not")
    public void testUpdateUser() {

        User currentUser = testUser;
        Long userId = 1L;
        User updateUser = testUser1;


        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(updateUser));
        Mockito.when(userRepository.save(updateUser)).thenReturn(updateUser);

        // When
        boolean result = userService.updateUser(currentUser, userId);

        // Then
        assertTrue(result);
        assertEquals(currentUser.getUserFirstName(), updateUser.getUserFirstName());
        assertEquals(currentUser.getUserLastName(), updateUser.getUserLastName());
        assertEquals(currentUser.getUserEmail(), updateUser.getUserEmail());
        assertEquals(currentUser.getUserDateOfBirth(), updateUser.getUserDateOfBirth());
        assertEquals(currentUser.getAddress(), updateUser.getAddress());
    }

    
}

