package com.guilherme.adopted.interfaces;

public interface IConverter<D, E, R> {

    E toEntity(D requestDto);

    R toResponse(E entity);
}
