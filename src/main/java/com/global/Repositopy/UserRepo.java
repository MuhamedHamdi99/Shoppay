package com.global.Repositopy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.global.Entity.User;



@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
