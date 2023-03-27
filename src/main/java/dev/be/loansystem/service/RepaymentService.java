package dev.be.loansystem.service;

import dev.be.loansystem.dto.RepaymentDTO.*;

public interface RepaymentService {

    Response create(Long applicationId, Request request);
}
