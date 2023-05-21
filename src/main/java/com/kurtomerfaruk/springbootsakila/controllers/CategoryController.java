package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.Category;
import com.kurtomerfaruk.springbootsakila.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 20.05.2023 10:38
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController extends BaseController{

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category){
        logger.info("> createCategory");
        return categoryService.save(category);
    }

    @GetMapping
    public List<Category> getAllCategory(){
        logger.info("> getAllCategory");
        return categoryService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer categoryId){
        logger.info("> getCategoryById");
        return categoryService.getId(categoryId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer categoryId, @RequestBody Category category){
        logger.info("> updateCategory");
        return categoryService.getId(categoryId)
                .map(savedCategory -> {

                    savedCategory.setName(category.getName());
                    savedCategory.setLastUpdate(category.getLastUpdate());

                    Category updatedCategory = categoryService.update(savedCategory);
                    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer categoryId){
        logger.info("> deleteCategory");
        categoryService.delete(categoryId);
        logger.info("> deleted Category");
        return new ResponseEntity<String>("Category deleted successfully!.", HttpStatus.OK);

    }
}
