package com.thingg.ecommerce.service;


import com.thingg.ecommerce.io.ItemRequest;
import com.thingg.ecommerce.io.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    ItemResponse add(ItemRequest request, MultipartFile file);

    List<ItemResponse> read();

    void delete(String itemId);
}
