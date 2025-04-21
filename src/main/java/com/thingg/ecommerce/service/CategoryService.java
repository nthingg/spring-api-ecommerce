package com.thingg.ecommerce.service;

import com.thingg.ecommerce.io.CategoryRequest;
import com.thingg.ecommerce.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest request, MultipartFile file);

    List<CategoryResponse> read();

    void delete(String categoryId);
}
