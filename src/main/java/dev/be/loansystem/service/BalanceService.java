package dev.be.loansystem.service;

import dev.be.loansystem.dto.BalanceDTO;
import dev.be.loansystem.dto.BalanceDTO.*;

public interface BalanceService {

    Response create(Long applicationId, CreateRequest request);

    Response update(Long applicationId, UpdateRequest request);

    void delete(Long applicationId);

    Response repaymentUpdate(Long applicationId, RepaymentRequest request);
}
