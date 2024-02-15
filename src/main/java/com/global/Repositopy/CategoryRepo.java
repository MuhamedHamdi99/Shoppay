package com.global.Repositopy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.Entity.Category;

@Repository
public interface CategoryRepo  extends JpaRepository<Category, Long>{

}
