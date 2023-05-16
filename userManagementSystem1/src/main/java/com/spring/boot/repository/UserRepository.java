package com.spring.boot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/*
	 * List<User> findByIsActive(boolean isActive); Page<User>
	 * findByIsActive(boolean isActive, Pageable pageable);
	 */
    Page<User> findAllByIsActive(boolean isActive, Pageable pageable);

}
