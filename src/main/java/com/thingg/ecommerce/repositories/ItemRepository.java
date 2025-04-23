package com.thingg.ecommerce.repositories;

import com.thingg.ecommerce.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByItemId(String itemId);

    Integer countByCategoryId(Long id);
}
