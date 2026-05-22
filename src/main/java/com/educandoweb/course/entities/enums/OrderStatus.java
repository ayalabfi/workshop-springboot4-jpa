package com.educandoweb.course.entities.enums;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    // Cria uma variável code para definir manualmente os códigos do enum
    private int code;

    // Atrela a cada situação do OrderStatus o código definido
    private OrderStatus(int code) {
        this.code = code;
    }

    // Retorna o código definido para a dada situação do orderStatus
    public int getCode() {
        return code;
    }

    // Percorre o OrderStatus buscando o código informado, caso não identifique, retorna a exceção
    public static OrderStatus valueOf(int code){
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code!");
    }
}
