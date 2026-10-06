package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;
// instead of this constructor we are using the @Autowired anno
//    public CategoryController(CategoryService categoryService)
//    {
//        this.categoryService = categoryService;
//    }

    @GetMapping("api/public/categories")
//    @RequestMapping(value = "api/public/categories",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() // instead of getAllCategories() which is the method of controller consider showCategories()
    {
        List<Category> categories=categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);// and here getAllCategories is the method of the service here
    }

    @PostMapping("api/public/categories")
//    @RequestMapping(value = "api/public/categories",method = RequestMethod.POST)
    public ResponseEntity <String> createCategory(@Valid @RequestBody Category category)
    {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully",HttpStatus.OK) ;
    }

    @DeleteMapping("api/admin/categories/{categoryId}")
//    @RequestMapping(value = "api/admin/categories/{categoryId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        try {
            String status = categoryService.deleteCategory(categoryId);
//             return new ResponseEntity<>(status, HttpStatus.OK);
//               return ResponseEntity.ok(status);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }
        catch (ResponseStatusException e)
        {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }


    @PutMapping("api/public/categories/{categoryId}")
//    @RequestMapping(value = "api/public/categories/{categoryId}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId)
    {
        try
        {
            Category savedCategory=categoryService.updateCategory(category,categoryId);
            return new  ResponseEntity<>("Category with category id:" +category,HttpStatus.OK);
        }
        catch (ResponseStatusException e)
        {
           return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }



}
/*


@GetMapping("/categories")
public List<Category> showCategories()
{
    return categoryService.fetchAllCategories();
}

1>Controller method → showCategories()
2>Service method → fetchAllCategories()

* */