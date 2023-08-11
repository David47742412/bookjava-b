package com.idat.learn.controllers;

import com.idat.learn.dto.CrudBookDto;
import com.idat.learn.dto.FindBookDto;
import com.idat.learn.response.ResponseApi;
import com.idat.learn.services.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService _service;

    public BookController(BookService service) {
        _service = service;
    }

    @GetMapping()
    public Mono<ResponseEntity<ResponseApi<FindBookDto>>> find() {
        return this._service.find().map((data) -> new ResponseEntity<>(data, HttpStatusCode.valueOf(data.statusCode)));
    }

    @PostMapping()
    public Mono<ResponseEntity<ResponseApi<FindBookDto>>> insert(@RequestBody() CrudBookDto crudBookDto, HttpServletRequest req) {
        crudBookDto.workspace = req.getHeader("User-Agent");
        crudBookDto.ipReq = req.getRemoteAddr();
        return this._service.curd('N', crudBookDto).publishOn(Schedulers.boundedElastic()).map(dataResult -> {
            dataResult.body = Objects.requireNonNull(this._service.find().block()).body;
            return new ResponseEntity<>(dataResult, HttpStatusCode.valueOf(dataResult.statusCode));
        });
    }

    @PutMapping()
    public Mono<ResponseEntity<ResponseApi<FindBookDto>>> update(@RequestBody() CrudBookDto crudBookDto, HttpServletRequest req) {
        crudBookDto.workspace = req.getHeader("User-Agent");
        crudBookDto.ipReq = req.getRemoteAddr();
        return this._service.curd('M', crudBookDto).publishOn(Schedulers.boundedElastic()).map(dataResult -> {
            dataResult.body = Objects.requireNonNull(this._service.find().block()).body;
            return new ResponseEntity<>(dataResult, HttpStatusCode.valueOf(dataResult.statusCode));
        });
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<ResponseApi<FindBookDto>>> delete(@PathVariable("id") String id, HttpServletRequest req) {
        var crudBookDto = new CrudBookDto();
        crudBookDto.workspace = req.getHeader("User-Agent");
        crudBookDto.ipReq = req.getRemoteAddr();
        crudBookDto.bookId = id;
        return this._service.curd('M', crudBookDto).publishOn(Schedulers.boundedElastic()).map(dataResult -> {
            dataResult.body = Objects.requireNonNull(this._service.find().block()).body;
            return new ResponseEntity<>(dataResult, HttpStatusCode.valueOf(dataResult.statusCode));
        });
    }

}
