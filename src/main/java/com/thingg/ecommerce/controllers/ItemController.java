package com.thingg.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thingg.ecommerce.io.ItemRequest;
import com.thingg.ecommerce.io.ItemResponse;
import com.thingg.ecommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/admin/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse addItem(@RequestPart("item") String item,
                                @RequestPart("file") MultipartFile file) {
        ObjectMapper mapper = new ObjectMapper();
        ItemRequest itemRequest;
        try {
            itemRequest = mapper.readValue(item, ItemRequest.class);
            return itemService.add(itemRequest, file);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error occured while parsing json");
        }
    }

    @GetMapping("/items")
    public List<ItemResponse> getAllItems() {
        return itemService.read();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/items/{itemId}")
    public void remove(@PathVariable String itemId) {
        try {
            itemService.delete(itemId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
