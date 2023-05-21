package com.kurtomerfaruk.springbootsakila.repositories;

import com.kurtomerfaruk.springbootsakila.models.Actor;
import com.kurtomerfaruk.springbootsakila.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 14.05.2023 16:13
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
