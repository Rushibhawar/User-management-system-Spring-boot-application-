package com.spring.boot.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.entity.User;

public interface UserService {
	  List<User> getAllUsers();
	  User getUserById(Long id);
	  void saveOrUpdateUser(User user);
	  boolean updateUser(User currentUser,Long userId);
	  void deleteUser(Long id);

		/*
		 * List<User> getUsersWithIsActive0(); Page<User> findByIsActive(boolean
		 * isActive, Pageable pageable);
		 */
	  Page<User> findPaginated(int pageNo, int pageSize, String sortBy, String sortDir,boolean isActive);
}
