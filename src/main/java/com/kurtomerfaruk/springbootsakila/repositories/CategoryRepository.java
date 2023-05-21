package com.kurtomerfaruk.springbootsakila.repositories;

import com.kurtomerfaruk.springbootsakila.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 20.05.2023 10:35
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
