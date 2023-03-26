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

    @PostMapping("/{applicationId}/entries")
    public ResponseDTO<Response> create(@PathVariable Long applicationId, @RequestBody Request request) {
        return ok(entryService.create(applicationId, request));
    }

    @GetMapping("/entries/{entryId}")
    public ResponseDTO<Response> get(@PathVariable Long entryId) {
        return ok(entryService.get(entryId));
    }

    @PutMapping("/{applicationId}/entries")
    public ResponseDTO<UpdateResponse> update(@PathVariable Long applicationId, @RequestBody Request request) {
        return ok(entryService.update(applicationId, request));
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseDTO<Void> delete(@PathVariable Long entryId) {
        entryService.delete(entryId);
        return ok();
    }
}
