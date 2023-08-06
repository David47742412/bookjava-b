package com.idat.learn.controllers;

import com.idat.learn.entities.CategoryEntity;
import com.idat.learn.response.ResponseApi;
import com.idat.learn.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<ResponseApi<CategoryEntity>>> create(HttpServletRequest req, @RequestBody() CategoryEntity entity) {
        entity.workSpaceCreate = req.getHeader("User-Agent");
        entity.workSpaceUpdate = req.getHeader("User-Agent");
        entity.ipReq = req.getRemoteAddr();
        return this._service.create(entity)
                .map(e -> new ResponseEntity<>(e, HttpStatusCode.valueOf(e.statusCode)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ResponseApi<CategoryEntity>>> update(@PathVariable String id, HttpServletRequest req, @RequestBody() CategoryEntity entity) {
        entity.workSpaceCreate = req.getHeader("User-Agent");
        entity.workSpaceUpdate = req.getHeader("User-Agent");
        entity.ipReq = req.getRemoteAddr();
        return this._service.update(id, entity)
                .map(e -> new ResponseEntity<>(e, HttpStatusCode.valueOf(e.statusCode)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResponseApi<CategoryEntity>>> delete(@PathVariable("id") String id, HttpServletRequest req) {
        var entity = new CategoryEntity();
        entity.workSpaceUpdate = req.getHeader("User-Agent");
        entity.ipReq = req.getRemoteAddr();
        return this._service.delete(id, entity)
                .map(e -> new ResponseEntity<>(e, HttpStatusCode.valueOf(e.statusCode)));
    }

}
