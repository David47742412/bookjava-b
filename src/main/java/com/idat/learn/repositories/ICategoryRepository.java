package com.idat.learn.repositories;

import com.idat.learn.entities.CategoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends ReactiveCrudRepository<CategoryEntity, String> {

}
