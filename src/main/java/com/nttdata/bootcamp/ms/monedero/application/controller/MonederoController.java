package com.nttdata.bootcamp.ms.monedero.application.controller;

import com.nttdata.bootcamp.ms.monedero.domain.dto.MonederoDto;
import com.nttdata.bootcamp.ms.monedero.domain.service.MonederoService;
import com.nttdata.bootcamp.ms.monedero.infraestructure.entity.Monedero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/monedero")
public class MonederoController {

    @Autowired
    private MonederoService monederoService;

    /**
     *
     * Obtains every wallet with its information
     * @return Monedero
     */
    @GetMapping
    public Flux<Monedero> getAll(){
      return monederoService.getAll();
    }

    /**
     *
     * Obtains the information of a certain wallet
     * @Param walletId
     * @return Monedero
     */
    @GetMapping("/{walletId}")
    public Mono<Monedero> getMonederoById(@PathVariable Integer walletId){
        return monederoService.getMonederoById(walletId);
    }

    /**
     *
     * Create new waller
     * @Param MonederoDto
     * @return Monedero
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Monedero> createMonedero(@RequestBody MonederoDto monederoDto){
        return monederoService.createMonedero(monederoDto);
    }

    @PutMapping
    public Mono<Monedero> updateMonedero(@RequestBody MonederoDto monederoDto) {
        return monederoService.updateMonedero(monederoDto);
    }

    @GetMapping("/balance/{walletId}")
    public Mono<Object> getAvailableBalance(@PathVariable Integer walletId){
        return monederoService.getAvailableBalance(walletId);
    }

    @PostMapping("/deposit")
    public Mono<Monedero> makeDeposit(@RequestBody MonederoDto monederoDto){
        return monederoService.makeDeposit(monederoDto);
    }

    @PostMapping("/payment")
    public Mono<Monedero> makePayment(@RequestBody MonederoDto monederoDto){
        return monederoService.makePayment(monederoDto);
    }

    @PutMapping("/debitCard")
    public Mono<Monedero> associateDebitCard(@RequestBody MonederoDto monederoDto){
        return monederoService.associateDebitCard(monederoDto);
    }

    @DeleteMapping("/{walletId}")
    public Mono<Monedero> deleteMonedero(@PathVariable Integer walletId){
        return monederoService.deleteMonedero(walletId);
    }
}
