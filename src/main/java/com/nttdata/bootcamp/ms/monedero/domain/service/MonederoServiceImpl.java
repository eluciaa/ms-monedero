package com.nttdata.bootcamp.ms.monedero.domain.service;

import com.nttdata.bootcamp.ms.monedero.domain.dto.MonederoDto;
import com.nttdata.bootcamp.ms.monedero.infraestructure.entity.Monedero;
import com.nttdata.bootcamp.ms.monedero.infraestructure.repository.MonederoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MonederoServiceImpl implements  MonederoService{

    @Autowired
    private MonederoRepository monederoRepository;

    @Override
    public Flux<Monedero> getAll() {
        return monederoRepository.findAll();
    }

    @Override
    public Mono<Monedero> getMonederoById(Integer id) {
        return monederoRepository.findById(id);
    }

    @Override
    public Mono<Monedero> createMonedero(MonederoDto monederoDto) {
        Monedero monedero = new Monedero();
        monedero.setId(monederoDto.getWalletId());
        monedero.setSaldo(monederoDto.getAvailableBalance());
        monedero.setTelefonoAsociado(monederoDto.getPhoneNumber());
        monedero.setIdDebito(monederoDto.getDebitId());
        return monederoRepository.save(monedero);
    }

    @Override
    public Mono<Monedero> updateMonedero(MonederoDto monederoDto) {
        return monederoRepository.findById(monederoDto.getWalletId())
                .flatMap(newMonedero -> {
                    newMonedero.setId(monederoDto.getWalletId());
                    newMonedero.setSaldo(monederoDto.getAvailableBalance());
                    newMonedero.setTelefonoAsociado(monederoDto.getPhoneNumber());
                    newMonedero.setIdDebito(monederoDto.getDebitId());
                    return monederoRepository.save(newMonedero);
                });
    }
    @Override
    public Mono<Object> getAvailableBalance(Integer walletId) {
        return monederoRepository.findById(walletId)
                .flatMap( monedero -> Mono.just(monedero.getSaldo()));
    }

    @Override
    public Mono<Monedero> makeDeposit(MonederoDto monederoDto) {
        return monederoRepository.findById(monederoDto.getWalletId())
                .flatMap(newMonedero -> {
                    Float newDeposit = newMonedero.getSaldo() + monederoDto.getAmount();
                    newMonedero.setSaldo(newDeposit);
                    return monederoRepository.save(newMonedero);
                });
    }

    @Override
    public Mono<Monedero> makePayment(MonederoDto monederoDto) {
        return monederoRepository.findById(monederoDto.getWalletId())
                .flatMap(newMonedero -> {
                    Float newDeposit = newMonedero.getSaldo() - monederoDto.getAmount();
                    newMonedero.setSaldo(newDeposit);
                    return monederoRepository.save(newMonedero);
                });
    }

    @Override
    public Mono<Monedero> associateDebitCard(MonederoDto monederoDto) {
        return monederoRepository.findById(monederoDto.getWalletId())
                .flatMap(newMonedero -> {
                    newMonedero.setIdDebito(monederoDto.getDebitId());
                    return monederoRepository.save(newMonedero);
                });
    }

    @Override
    public Mono<Monedero> deleteMonedero(Integer id) {
        return monederoRepository.findById(id)
                .flatMap(monedero -> monederoRepository.delete(monedero)
                        .then(Mono.just(monedero)));
    }
}
