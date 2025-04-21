package com.thingg.ecommerce.repositories;

import com.thingg.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryId(String categoryId);
}
