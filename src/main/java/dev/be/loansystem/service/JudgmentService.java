package dev.be.loansystem.service;

import dev.be.loansystem.dto.JudgmentDTO.*;

public interface JudgmentService {

    Response create(Request request);

    Response get(Long judgmentId);

    Response getJudgmentOfApplication(Long applicationId);
}
