package com.nttdata.bootcamp.ms.monedero.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@AllArgsConstructor
public class MonederoDto {

    private Integer walletId;
    private Float availableBalance;

    private String phoneNumber;

    private Integer debitId;

}
