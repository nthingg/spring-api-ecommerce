package com.thingg.ecommerce.service.impl;

import com.thingg.ecommerce.entities.CategoryEntity;
import com.thingg.ecommerce.entities.ItemEntity;
import com.thingg.ecommerce.io.ItemRequest;
import com.thingg.ecommerce.io.ItemResponse;
import com.thingg.ecommerce.repositories.CategoryRepository;
import com.thingg.ecommerce.repositories.ItemRepository;
import com.thingg.ecommerce.service.FileUploadService;
import com.thingg.ecommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;

    @Override
    public ItemResponse add(ItemRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.uploadFile(file);
        ItemEntity newItem = convertToEntity(request);
        CategoryEntity existingCategory = categoryRepository.findByCategoryId(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found" + request.getCategoryId()));
        newItem.setImgUrl(imgUrl);
        newItem.setCategory(existingCategory);
        newItem = itemRepository.save(newItem);
        return convertToResponse(newItem);
    }

    private ItemResponse convertToResponse(ItemEntity newItem) {
        return ItemResponse.builder()
                .itemId(newItem.getItemId())
                .categoryId(newItem.getCategory().getCategoryId())
                .name(newItem.getName())
                .imgUrl(newItem.getImgUrl())
                .description(newItem.getDescription())
                .price(newItem.getPrice())
                .categoryName(newItem.getCategory().getName())
                .categoryId(newItem.getCategory().getCategoryId())
                .createdAt(newItem.getCreatedAt())
                .updatedAt(newItem.getUpdatedAt())
                .build();
    }

    private ItemEntity convertToEntity(ItemRequest request) {
        return ItemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    @Override
    public List<ItemResponse> read() {
        return itemRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public void delete(String itemId) {
        ItemEntity existingItem = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found: " + itemId));
        boolean isDeleted = fileUploadService.deleteFile(existingItem.getImgUrl());
        if (isDeleted) {
            itemRepository.delete(existingItem);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete file");
        }
    }
}
