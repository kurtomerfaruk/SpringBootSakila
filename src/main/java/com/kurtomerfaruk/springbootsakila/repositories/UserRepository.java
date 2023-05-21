package com.kurtomerfaruk.springbootsakila.repositories;

import com.kurtomerfaruk.springbootsakila.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 20.05.2023 23:17
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
