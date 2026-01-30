package com.guilherme.adopted.interfaces;

public interface ConverterInterface<REQ extends Request, E extends EntityInterface, RES extends Response> {

    E toEntity(REQ requestObject);

    RES toResponse(E entityObject);
}
