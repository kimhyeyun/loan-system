package dev.be.loansystem.service;


import dev.be.loansystem.dto.EntryDTO.*;

public interface EntryService {

    Response create(Long applicationId, Request request);
}
