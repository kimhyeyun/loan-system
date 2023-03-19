package dev.be.loansystem.service;


import dev.be.loansystem.dto.ApplicationDTO.*;

public interface ApplicationService {

    Response create(Request request);

    Response get(Long applicationId);

    Response update(Long applicationId, Request request);

    void delete(Long applicationId);

    Boolean accetpTerms(Long applicationId, AcceptTermsRequest request);

    Response contract(Long applicationId);

}
