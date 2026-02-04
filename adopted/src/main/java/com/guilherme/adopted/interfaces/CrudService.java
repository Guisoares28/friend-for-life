package com.guilherme.adopted.interfaces;

import org.springframework.data.domain.Page;

public interface CrudService<REQ extends Request, RES extends Response, U extends Update, T> {

    RES create(REQ requestObject);

    RES get(T id);

    Page<RES> getAll(int page, int size);

    RES update(U updateObject, T id);

    RES delete(T id); 
}
