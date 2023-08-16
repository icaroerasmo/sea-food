package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.Service;
import com.icaroerasmo.seafood.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/rest/user")
@org.springframework.web.bind.annotation.RestController
public class UserRestController extends RestController<User> {
}
