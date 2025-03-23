package org.mirasruntime.springjpa.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    CREATED("СОЗДАН"),
    IN_PROGRESS("В_ДОСТАВКЕ"),
    COMPLETED("ДОСТАВЛЕН");

    private final String viewName;
}
