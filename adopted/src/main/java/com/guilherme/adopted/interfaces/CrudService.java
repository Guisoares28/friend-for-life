package com.guilherme.adopted.interfaces;

import java.util.List;

public interface CrudService<REQ extends Request, RES extends Response, U extends Update, T> {

    RES create(REQ requestObject);

    RES get(T id);

    List<RES> getAll();

    RES update(U updateObject, T id);

    RES delete(T id); 
}
