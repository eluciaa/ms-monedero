package com.nttdata.bootcamp.ms.monedero.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

@Data
@Generated
@AllArgsConstructor
public class MonederoDto {

    @NotNull
    private Integer walletId;

    private Float availableBalance;

    @NotNull
    private String phoneNumber;

    private Integer debitId;

    private Float amount;

}
