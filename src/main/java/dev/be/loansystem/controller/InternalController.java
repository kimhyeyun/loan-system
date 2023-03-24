package dev.be.loansystem.controller;

import dev.be.loansystem.dto.EntryDTO.*;
import dev.be.loansystem.dto.ResponseDTO;
import dev.be.loansystem.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/applications")
public class InternalController extends AbstractController {

    private final EntryService entryService;

    @PostMapping("{applicationId}/entries")
    public ResponseDTO<Response> create(@PathVariable Long applicationId, @RequestBody Request request) {
        return ok(entryService.create(applicationId, request));
    }
}
