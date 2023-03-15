package dev.be.loansystem.service;

import dev.be.loansystem.dto.TermsDTO.*;

import java.util.List;

public interface TermsService {

    Response create(Request request);

    List<Response> getAll();
}
