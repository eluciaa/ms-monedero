package com.nttdata.bootcamp.ms.monedero.infraestructure.repository;

import com.nttdata.bootcamp.ms.monedero.infraestructure.entity.Monedero;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonederoRepository extends ReactiveMongoRepository<Monedero, Integer>{
}
