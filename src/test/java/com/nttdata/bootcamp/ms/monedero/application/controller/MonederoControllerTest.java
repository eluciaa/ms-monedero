package com.nttdata.bootcamp.ms.monedero.application.controller;

import com.nttdata.bootcamp.ms.monedero.domain.dto.MonederoDto;
import com.nttdata.bootcamp.ms.monedero.domain.service.MonederoService;
import com.nttdata.bootcamp.ms.monedero.infraestructure.entity.Monedero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebFluxTest(MonederoController.class)
public class MonederoControllerTest {

    @MockBean
    private MonederoService monederoService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAll() {

        Flux<Monedero> monederoFlux = Flux.just(new Monedero(1, 20F, "992698578", 1),
                new Monedero(2, 10F, "993478366", 2));

        when(monederoService.getAll()).thenReturn(monederoFlux);

        Flux<Monedero> responseBody = webTestClient.get()
                .uri("/monedero")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Monedero.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new Monedero(1, 20F, "992698578", 1))
                .expectNext(new Monedero(2, 10F, "993478366", 2))
                .verifyComplete();
    }

    @Test
    void getMonederoById() {

        Monedero monedero = new Monedero(2, 10F, "993478366", 2);

        Mono<Monedero> monederoMono = Mono.just(new Monedero(2, 10F, "993478366", 2));
        when(monederoService.getMonederoById(monedero.getId())).thenReturn(monederoMono);

        webTestClient.get()
                .uri("/monedero/" + monedero.getId())
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Monedero.class)
                .getResponseBody();

        StepVerifier.create(monederoMono)
                .expectNext(monedero)
                .verifyComplete();
    }

    @Test
    void createMonedero() {

        Monedero monedero = new Monedero(1, 100F, "992695847", 1);
        MonederoDto monederoDto = new MonederoDto(1, 100F, "992695847", 1, null);

        Mono<Monedero> monederoMono = Mono.just(new Monedero(1, 100F, "992695847", 1));
        Mono<MonederoDto> monederoDtoMono  =  Mono.just(new MonederoDto(1, 100F, "992695847", 1, null));

        when(monederoService.createMonedero(monederoDto)).thenReturn(monederoMono);

        webTestClient.post()
                .uri("/monedero")
                .body(monederoDtoMono, MonederoDto.class)
                .exchange()
                .expectStatus().isCreated();

        StepVerifier.create(monederoMono)
                .expectNext(monedero)
                .verifyComplete();
    }

    @Test
    void updateMonedero(){
        Monedero monedero = new Monedero(1, 100F, "992695847", 1);
        MonederoDto monederoDto = new MonederoDto(1, 100F, "992695847", 1, null);

        Mono<Monedero> monederoMono = Mono.just(new Monedero(1, 100F, "992695847", 1));
        Mono<MonederoDto> monederoDtoMono  =  Mono.just(new MonederoDto(1, 100F, "992695847", 1, null));

        when(monederoService.updateMonedero(monederoDto)).thenReturn(monederoMono);

        webTestClient.put()
                .uri("/monedero")
                .body(monederoDtoMono, MonederoDto.class)
                .exchange()
                .expectStatus().isOk();

        StepVerifier.create(monederoMono)
                .expectNext(monedero)
                .verifyComplete();

    }

    @Test
    void getAvailableBalance(){
        Integer walletId = 1;
        Float balance = 200F;

        Mono<Object> objectMono = Mono.just(200F);

        when(monederoService.getAvailableBalance(walletId)).thenReturn(objectMono);
        webTestClient.get()
                .uri("/monedero/balance/" + walletId)
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Mono.class)
                .getResponseBody();

        StepVerifier.create(objectMono)
                .expectNext(balance)
                .verifyComplete();

    }

    @Test
    void makeDeposit(){
        Monedero monedero = new Monedero(1, 100F, "992695847", 1);
        MonederoDto monederoDto = new MonederoDto(1, 100F, "992695847", 1, null);

        Mono<Monedero> monederoMono = Mono.just(new Monedero(1, 100F, "992695847", 1));
        Mono<MonederoDto> monederoDtoMono  =  Mono.just(new MonederoDto(1, 100F, "992695847", 1, null));

        when(monederoService.makeDeposit(monederoDto)).thenReturn(monederoMono);
        webTestClient.post()
                .uri("/monedero/deposit")
                .body(monederoDtoMono, MonederoDto.class)
                .exchange()
                .expectStatus().isOk();

        StepVerifier.create(monederoMono)
                .expectNext(monedero)
                .verifyComplete();

    }

    @Test
    void makePayment(){
        Monedero monedero = new Monedero(1, 100F, "992695847", 1);
        MonederoDto monederoDto = new MonederoDto(1, 100F, "992695847", 1, null);

        Mono<Monedero> monederoMono = Mono.just(new Monedero(1, 100F, "992695847", 1));
        Mono<MonederoDto> monederoDtoMono  =  Mono.just(new MonederoDto(1, 100F, "992695847", 1, null));

        when(monederoService.makePayment(monederoDto)).thenReturn(monederoMono);
        webTestClient.post()
                .uri("/monedero/payment")
                .body(monederoDtoMono, MonederoDto.class)
                .exchange()
                .expectStatus().isOk();

        StepVerifier.create(monederoMono)
                .expectNext(monedero)
                .verifyComplete();

    }

    @Test
    void associateDebitCard(){

        Monedero monedero = new Monedero(1, 100F, "992695847", 1);
        MonederoDto monederoDto = new MonederoDto(1, 100F, "992695847", 1, null);

        Mono<Monedero> monederoMono = Mono.just(new Monedero(1, 100F, "992695847", 1));
        Mono<MonederoDto> monederoDtoMono  =  Mono.just(new MonederoDto(1, 100F, "992695847", 1, null));

        when(monederoService.associateDebitCard(monederoDto)).thenReturn(monederoMono);

        webTestClient.put()
                .uri("/monedero/debitCard")
                .body(monederoDtoMono, MonederoDto.class)
                .exchange()
                .expectStatus().isOk();

        StepVerifier.create(monederoMono)
                .expectNext(monedero)
                .verifyComplete();

    }

    @Test
    void deleteMonedero(){
        given(monederoService.deleteMonedero(any())).willReturn(Mono.empty());
        webTestClient.delete()
                .uri("/monedero/1")
                .exchange()
                .expectStatus().isOk();
    }
}
