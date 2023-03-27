package dev.be.loansystem.controller;

import dev.be.loansystem.dto.EntryDTO.*;
import dev.be.loansystem.dto.RepaymentDTO;
import dev.be.loansystem.dto.ResponseDTO;
import dev.be.loansystem.service.EntryService;
import dev.be.loansystem.service.RepaymentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/applications")
public class InternalController extends AbstractController {

    private final EntryService entryService;
    private final RepaymentService repaymentService;

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

    @PostMapping("/{applicationId}/repayments")
    public ResponseDTO<RepaymentDTO.Response> create(@PathVariable Long applicationId, @RequestBody RepaymentDTO.Request request) {
        return ok(repaymentService.create(applicationId, request));
    }

    @GetMapping("/{applicationId}/repayments")
    public ResponseDTO<List<RepaymentDTO.ListResponse>> getPayments(@PathVariable Long applicationId) {
        return ok(repaymentService.get(applicationId));
    }

    @PutMapping("/repayments/{repaymentId}")
    public ResponseDTO<RepaymentDTO.UpdateResponse> update(@PathVariable Long repaymentId, @RequestBody RepaymentDTO.Request request) {
        return ok(repaymentService.update(repaymentId, request));
    }
}
