package com.nttdata.bootcamp.ms.monedero.application.controller;

import com.nttdata.bootcamp.ms.monedero.domain.dto.MonederoDto;
import com.nttdata.bootcamp.ms.monedero.domain.service.MonederoService;
import com.nttdata.bootcamp.ms.monedero.infraestructure.entity.Monedero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
@RequestMapping("/monedero")
public class MonederoController {

    @Autowired
    private MonederoService monederoService;

    /**
     *
     * Obtener la información de todas los monederos existentes
     * @return
     */
    @GetMapping
    private Flux<Monedero> getAll(){
      return monederoService.getAll();
    }


}
