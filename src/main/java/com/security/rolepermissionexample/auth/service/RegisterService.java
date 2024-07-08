package com.security.rolepermissionexample.auth.service;

import com.security.rolepermissionexample.auth.model.User;
import com.security.rolepermissionexample.auth.model.dto.request.RegisterRequest;

public interface RegisterService {

    User registerAdmin(final RegisterRequest registerRequest);
}
