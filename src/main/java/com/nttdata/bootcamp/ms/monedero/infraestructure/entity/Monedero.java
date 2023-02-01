package com.nttdata.bootcamp.ms.monedero.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "monedero")
@Data
@Generated
@NoArgsConstructor
public class Monedero {

    @Id
    private Integer id;

    private Float saldo;

    private String telefonoAsociado;

    private Integer idDebito;
}
