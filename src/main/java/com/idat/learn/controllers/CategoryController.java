package com.idat.learn.controllers;

import com.idat.learn.entities.CategoryEntity;
import com.idat.learn.response.ResponseApi;
import com.idat.learn.services.CategoryService;
import io.reactivex.Observable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService _service;

    public CategoryController(CategoryService service) {
        _service = service;
    }

    @GetMapping("")
    public Mono<ResponseApi<CategoryEntity>> find() {
        return _service.find();
    }

    @PostMapping("")
    public Mono<ResponseApi<CategoryEntity>> create(HttpServletRequest req, @RequestBody() CategoryEntity entity) {
        entity.workSpaceCreate = req.getHeader("User-Agent");
        entity.workSpaceUpdate = req.getHeader("User-Agent");
        entity.ipReq = req.getRemoteAddr();
        return this._service.create(entity);
    }

}
