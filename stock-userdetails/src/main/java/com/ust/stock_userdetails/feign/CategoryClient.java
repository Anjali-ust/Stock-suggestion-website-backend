package com.ust.stock_userdetails.feign;

import com.ust.stock_userdetails.client.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="stock-category", url="http://localhost:7063/category")
public interface CategoryClient {
    @GetMapping("/get-category-by-id/{id}")
    public Optional<Category> getCategoryById(@PathVariable("id") String id);
}
