package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
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
//@RequestMapping("/api")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;



    @GetMapping("api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                             @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                             @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_CATEGOEIES_BY,required = false)String sortBy,
                                                             @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR,required = false)String sortOrder) // instead of getAllCategories() which is the method of controller consider showCategories()
    {
        CategoryResponse categoryResponse =categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);// and here getAllCategories is the method of the service here
    }

    @PostMapping("api/public/categories")
//    @RequestMapping(value = "api/public/categories",method = RequestMethod.POST)
    public ResponseEntity <CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO savedCategoryDTO =categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK) ;
    }

    @DeleteMapping("api/admin/categories/{categoryId}")
//    @RequestMapping(value = "api/admin/categories/{categoryId}",method = RequestMethod.DELETE)
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId)
    {
        CategoryDTO deletedCategory= categoryService.deleteCategory(categoryId);
//            return ResponseEntity.status(HttpStatus.OK).body(status);
         return new ResponseEntity<>(deletedCategory,HttpStatus.OK);
    }


    @PutMapping("api/public/categories/{categoryId}")
//    @RequestMapping(value = "api/public/categories/{categoryId}",method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId)
    {
            CategoryDTO savedCategoryDTO=categoryService.updateCategory(categoryDTO,categoryId);
            return new  ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);
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