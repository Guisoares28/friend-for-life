package com.guilherme.adopted.interfaces;

import com.guilherme.adopted.dtos.AuthDto;

public interface IAuthService {

    String login(AuthDto auth);

}
