package dev.be.loansystem.controller;

import dev.be.loansystem.dto.JudgmentDTO.*;
import dev.be.loansystem.dto.ResponseDTO;
import dev.be.loansystem.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/judgments")
public class JudgmentController extends AbstractController {

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(judgmentService.create(request));
    }

    @GetMapping("/{judgmentId}")
    public ResponseDTO<Response> get(@PathVariable Long judgmentId) {
        return ok(judgmentService.get(judgmentId));
    }

    @PutMapping("/{judgmentId}")
    public ResponseDTO<Response> update(@PathVariable Long judgmentId, @RequestBody Request request) {
        return ok(judgmentService.update(judgmentId, request));
    }

    @DeleteMapping("/{judgmentId}")
    public ResponseDTO<Void> delete(@PathVariable Long judgmentId) {
        judgmentService.delete(judgmentId);

        return ok();
    }
}
