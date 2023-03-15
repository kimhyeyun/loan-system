package dev.be.loansystem.controller;

import dev.be.loansystem.dto.ResponseDTO;
import dev.be.loansystem.dto.TermsDTO.*;
import dev.be.loansystem.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.be.loansystem.dto.ResponseDTO.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/terms")
public class TermsController {

    private final TermsService termsService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(termsService.create(request));
    }

    @GetMapping()
    public ResponseDTO<List<Response>> getAll() {
        return ok(termsService.getAll());
    }
}
