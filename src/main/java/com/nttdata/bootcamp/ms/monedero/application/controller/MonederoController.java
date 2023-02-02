package com.nttdata.bootcamp.ms.monedero.application.controller;

import com.nttdata.bootcamp.ms.monedero.domain.dto.MonederoDto;
import com.nttdata.bootcamp.ms.monedero.domain.service.MonederoService;
import com.nttdata.bootcamp.ms.monedero.infraestructure.entity.Monedero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Flux<Monedero> getAll(){
      return monederoService.getAll();
    }

    /**
     *
     * Obtains the information of a certain wallet
     * @Param walletId
     * @return Monedero
     */
    @GetMapping("/{id}")
    private Mono<Monedero> getMonederoById(@PathVariable Integer walletId){
        return monederoService.getMonederoById(walletId);
    }

    @PostMapping
    private Mono<Monedero> createMonedero(@RequestBody MonederoDto monederoDto){
        return monederoService.createMonedero(monederoDto);
    }
    @PutMapping
    private Mono<Monedero> updateMonedero(@RequestBody MonederoDto monederoDto) {
        return monederoService.updateMonedero(monederoDto);
    }

    @PutMapping("/phoneNumber")
    private Mono<Monedero> updatePhoneNumber(@PathVariable Integer walletId, @PathVariable String phoneNumber){
        return monederoService.updatePhoneNumber(walletId, phoneNumber);
    }

    @GetMapping("/balance")
    private Mono<Object> getAvailableBalance(@PathVariable Integer walletId, @PathVariable String phoneNumber){
        return monederoService.getAvailableBalance(walletId, phoneNumber);
    }

    @PostMapping("/deposit")
    private Mono<Monedero> makeDeposit(MonederoDto monederoDto){
        return monederoService.makeDeposit(monederoDto);
    }

    @PostMapping("/payment")
    private Mono<Monedero> makePayment(MonederoDto monederoDto){
        return monederoService.makePayment(monederoDto);
    }

    @PutMapping("/debitCard")
    private Mono<Monedero> associateDebitCard(MonederoDto monederoDto){
        return monederoService.associateDebitCard(monederoDto);
    }

    @DeleteMapping
    private Mono<Monedero> deleteMonedero(Integer walletId){
        return monederoService.deleteMonedero(walletId);
    }
}
