package com.idat.learn.services;

import com.idat.learn.entities.CategoryEntity;
import com.idat.learn.repositories.ICategoryRepository;
import com.idat.learn.response.ResponseApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CategoryService {

    private final ICategoryRepository _repository;

    public CategoryService(ICategoryRepository repository) {
        _repository = repository;
    }

    public Mono<CategoryEntity> findById(String id) {
        return this._repository.findById(id);
    }

    public Mono<ResponseApi<CategoryEntity>> find() {
        var response = new ResponseApi<CategoryEntity>();
        return _repository.find()
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
            System.out.println(ex.getMessage());
            var response = new ResponseApi<CategoryEntity>();
            response.statusCode = 500;
            response.message = "Ha ocurrido un Error al momento de guardar los datos";
            return Mono.create(e -> e.success(response));
        }
    }

    public Mono<ResponseApi<CategoryEntity>> update(String id, CategoryEntity entity) {
        try {
            this.findById(id).map(e -> {
                entity.workSpaceCreate = e.workSpaceCreate;
                entity.createDate = e.createDate;
                entity.deleted = e.deleted;
                return e;
            });
            entity.categoryId = id;
            entity.updateDate = LocalDateTime.now();
            return this._repository.save(entity).then(this.find());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            var response = new ResponseApi<CategoryEntity>();
            response.statusCode = 500;
            response.message = "Ha ocurrido un Error al momento de actualizar los datos";
            return Mono.create(e -> e.success(response));
        }
    }

    public Mono<ResponseApi<CategoryEntity>> delete(String id, CategoryEntity category) {
        try {
            this.findById(id).map(e -> {
                category.workSpaceCreate = e.workSpaceCreate;
                category.createDate = e.createDate;
                return e;
            });
            category.categoryId = id;
            category.deleted = true;
            return this._repository.save(category).then(this.find());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            var response = new ResponseApi<CategoryEntity>();
            response.statusCode = 500;
            response.message = "Ha ocurrido un Error al momento de eliminar una categoria";
            return Mono.create(e -> e.success(response));
        }
    }

}
