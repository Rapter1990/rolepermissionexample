package com.security.rolepermissionexample.auth.service;

import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.dto.request.LoginRequest;

public interface LoginService {

    Token login(final LoginRequest loginRequest);
}
