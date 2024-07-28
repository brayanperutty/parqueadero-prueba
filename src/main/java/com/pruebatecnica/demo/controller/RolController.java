package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {

    public final RolService rolService;
}
