package com.kurtomerfaruk.springbootsakila.services;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 19.05.2023 20:25
 */
public interface BaseService<T> {
    T save(T entity);
    List<T> getAll();
    Optional<T> getId(Integer id);
    T update(T entity);
    void delete(Integer id);
}
