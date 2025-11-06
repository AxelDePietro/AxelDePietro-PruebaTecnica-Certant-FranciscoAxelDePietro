package com.axel.pruebatecnica.api.entity.enums;

import lombok.Getter;

@Getter
public enum SeatTypeEnum {

    RECITAL_CAMPO(3000),
    RECITAL_PLATEA(2000),
    RECITAL_PALCO(1000),

    OBRA_ENTRADA_GENERAL(2000),
    OBRA_ENTRADA_VIP(5000),

    CHARLA_SIN_MEET(2000),
    CHARLA_CON_MEET(5000);

    private final int valor;

    SeatTypeEnum(int valor) {
        this.valor = valor;
    }
}
