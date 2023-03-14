package dev.be.loansystem.controller;

import dev.be.loansystem.dto.CounselDTO.*;
import dev.be.loansystem.dto.ResponseDTO;
import dev.be.loansystem.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/counsels")
public class CounselController extends AbstractController {

    private final CounselService counselService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(counselService.create(request));
    }

    @GetMapping("/{counselId}")
    public ResponseDTO<Response> get(@PathVariable Long counselId) {
        return ok(counselService.get(counselId));
    }

    @PutMapping("/{counselId}")
    public ResponseDTO<Response> update(@PathVariable Long counselId, @RequestBody Request request) {
        return ok(counselService.update(counselId, request));
    }

    @DeleteMapping("/{counselId}")
    public ResponseDTO<Response> delete(@PathVariable Long counselId) {
        counselService.delete(counselId);

        return ok();
    }
}
