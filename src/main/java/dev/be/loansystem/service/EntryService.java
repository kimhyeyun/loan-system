package dev.be.loansystem.service;


import dev.be.loansystem.dto.BalanceDTO.UpdateRequest;
import dev.be.loansystem.dto.EntryDTO.*;

public interface EntryService {

    Response create(Long applicationId, Request request);

    Response get(Long applicationId);

    UpdateResponse update(Long applicationId, Request request);
}

