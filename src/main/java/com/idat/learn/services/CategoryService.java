package com.idat.learn.services;

import com.idat.learn.entities.CategoryEntity;
import com.idat.learn.repositories.ICategoryRepository;
import com.idat.learn.response.ResponseApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CategoryService {

    private final ICategoryRepository _repository;

    public CategoryService(ICategoryRepository repository) {
        _repository = repository;
    }

    public Mono<ResponseApi<CategoryEntity>> find() {
        var response = new ResponseApi<CategoryEntity>();
        return _repository.findAll()
                .collectList()
                .map(categories -> {
                    response.statusCode = 200;
                    response.message = "";
                    response.body = categories;
                    return response;
                })
                .onErrorResume(ex -> {
                    response.statusCode = 500;
                    response.message = "Ha ocurrido un error al traer las categorias";
                    System.out.println(ex.getMessage());
                    return Mono.just(response);
                });
    }

    public Mono<ResponseApi<CategoryEntity>> create(CategoryEntity entity) {
        try {
            entity.createDate = null;
            entity.updateDate = null;
            return this._repository.save(entity).then(this.find());
        } catch (Exception ex) {
            var response = new ResponseApi<CategoryEntity>();
            response.statusCode = 500;
            response.message = "Ha ocurrido un Error al momento de guardar los datos";
            return Mono.create(e -> e.success(response));
        }
    }

}
