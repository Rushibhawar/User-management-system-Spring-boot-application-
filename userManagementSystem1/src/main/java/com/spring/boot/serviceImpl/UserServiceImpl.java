package com.spring.boot.serviceImpl;

import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.Address;
import com.spring.boot.entity.User;
import com.spring.boot.repository.AddressRepository;
import com.spring.boot.repository.UserRepository;
import com.spring.boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;

	private AddressRepository addressRepository;
	
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdateUser(User user) {
    	user.setUserCreatedDate(new Date());
        userRepository.save(user);
        
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
	
	@Override
	public boolean updateUser(User currentUser, Long userId) {
		// TODO Auto-generated method stub
		User updateUser = userRepository.findById(userId).orElse(null);

		updateUser.setUserFirstName(currentUser.getUserFirstName());
		updateUser.setUserLastName(currentUser.getUserLastName());
		updateUser.setUserEmail(currentUser.getUserEmail());
		updateUser.setUserMobileNumber(currentUser.getUserMobileNumber());
		updateUser.setUserDateOfBirth(currentUser.getUserDateOfBirth());
		updateUser.setUserCreatedDate(new Date());
		/* updateUser.setUserPassword(currentUser.getUserPassword()); */
		
		updateUser.setAddress(currentUser.getAddress());

		
		
		User u = userRepository.save(updateUser);
		if(u == null) {
			return false;
		}
		return true;
	}

	 
	 @Override
	 public Page<User> findPaginated(int pageNo, int pageSize, String sortBy, String sortDir,boolean isActive) {
		 
	     Sort sort = sortDir.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	     Pageable pageable  = PageRequest.of(pageNo, pageSize, sort);
		 return this.userRepository.findAllByIsActive(isActive, pageable);
	 }

	 
	 /*
		 * @Override public List<User> getUsersWithIsActive0() { // TODO Auto-generated
		 * method stub return userRepository.findByIsActive(false); }
		 */

	 

		
		/*
		 * @Override public Page<User> findByIsActive(boolean isActive, Pageable
		 * pageable) { // TODO Auto-generated method stub return return
		 * userRepository.findByIsActive(isActive, pageable); }
		 */
}
