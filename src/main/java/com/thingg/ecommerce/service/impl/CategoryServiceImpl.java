package com.thingg.ecommerce.service.impl;

import com.thingg.ecommerce.entities.CategoryEntity;
import com.thingg.ecommerce.io.CategoryRequest;
import com.thingg.ecommerce.io.CategoryResponse;
import com.thingg.ecommerce.repositories.CategoryRepository;
import com.thingg.ecommerce.service.CategoryService;
import com.thingg.ecommerce.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponse add(CategoryRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.uploadFile(file);
        CategoryEntity newCategory = convertToEntity(request);
        newCategory.setImgUrl(imgUrl);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> read() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public void delete(String categoryId) {
        CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
        fileUploadService.deleteFile(existingCategory.getImgUrl());
        categoryRepository.delete(existingCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategoryEntity) {
        return CategoryResponse.builder()
                .categoryId(newCategoryEntity.getCategoryId())
                .name(newCategoryEntity.getName())
                .description(newCategoryEntity.getDescription())
                .bgColor(newCategoryEntity.getBgColor())
                .imgUrl(newCategoryEntity.getImgUrl())
                .createdAt(newCategoryEntity.getCreatedAt())
                .updatedAt(newCategoryEntity.getUpdatedAt())
                .build();
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
