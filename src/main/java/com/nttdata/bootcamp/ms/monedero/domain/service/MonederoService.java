package com.nttdata.bootcamp.ms.monedero.domain.service;

import com.nttdata.bootcamp.ms.monedero.domain.dto.MonederoDto;
import com.nttdata.bootcamp.ms.monedero.infraestructure.entity.Monedero;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonederoService {

    Flux<Monedero> getAll();
    Mono<Monedero> getMonederoById(Integer id);
    Mono<Monedero> saveMonedero(MonederoDto monederoDto);
    Mono<Monedero> updateMonedero(MonederoDto monederoDto);
    Mono<Monedero> deleteMonedero(Integer id);
}
