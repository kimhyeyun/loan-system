package dev.be.loansystem.service;

import dev.be.loansystem.dto.CounselDTO.*;

public interface CounselService {

    Response create(Request request);

    Response get(Long counselId);

    Response update(Long counselId, Request request);

    void delete(Long counselId);
}
