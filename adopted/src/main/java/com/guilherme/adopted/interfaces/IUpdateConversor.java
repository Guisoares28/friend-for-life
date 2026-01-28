package com.guilherme.adopted.interfaces;

public interface IUpdateConversor<D, E> {

    void toEntityUpdate(D updateDto, E entity);

}
