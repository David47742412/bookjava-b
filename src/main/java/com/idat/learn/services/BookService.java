package com.idat.learn.services;

import com.idat.learn.dto.CrudBookDto;
import com.idat.learn.dto.FindBookDto;
import com.idat.learn.repositories.IBookRepository;
import com.idat.learn.response.ResponseApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final IBookRepository _repository;

    public BookService(IBookRepository repository) {
        _repository = repository;
    }

    public Mono<ResponseApi<FindBookDto>> find() {
        var response = new ResponseApi<FindBookDto>();
        return this._repository.find()
                .collectList()
                .map((data) -> {
                    response.statusCode = 200;
                    response.message = "";
                    response.body = data;
                    return response;
                })
                .onErrorResume(ex -> {
                    response.statusCode = 500;
                    response.message = "Ha ocurrido un error al momento de extraer los datos";
                    return Mono.just(response);
                });
    }

    public Mono<ResponseApi<FindBookDto>> curd(char option, CrudBookDto bookDto) {
        var response = new ResponseApi<FindBookDto>();
        return this._repository.crud(option, bookDto.bookId, bookDto.title,
                        bookDto.description, bookDto.workspace,
                        bookDto.ipReq, bookDto.categoryId)
                .map(data -> {
                    if (!data.isEmpty()) {
                        response.statusCode = 400;
                        response.message = data;
                        return response;
                    }
                    response.statusCode = 200;
                    return response;
                })
                .onErrorResume(ex -> {
                    response.statusCode = 500;
                    response.message = "Ha ocurrido un error inesperado al momento de guardar los datos";
                    return Mono.just(response);
                });
    }

}
