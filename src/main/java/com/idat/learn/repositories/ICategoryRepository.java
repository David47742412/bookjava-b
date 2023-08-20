package com.idat.learn.repositories;

import com.idat.learn.entities.CategoryEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.NonNullApi;

//@RepositoryRestResource(exported = false, path = "category", collectionResourceRel = "category") solo se puede utilizar para JPA y CRUDJPA
@Repository
public interface ICategoryRepository extends ReactiveCrudRepository<CategoryEntity, String> {
    @Query(value = "SELECT category_id, description FROM category WHERE __deleted__ = false")
    Flux<CategoryEntity> find();

    @NonNull
    @Query(value = "SELECT category_id, description FROM category WHERE __deleted__ = false AND category_id = :id")
    Mono<CategoryEntity> findById(String id);

}
