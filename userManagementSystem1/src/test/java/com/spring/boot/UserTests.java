package com.spring.boot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.boot.entity.Address;
import com.spring.boot.entity.User;

@SpringBootTest
public class UserTests {

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
	
	
	void testGetAllUsers() {
		
	}
}
