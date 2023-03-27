package dev.be.loansystem.service;

import dev.be.loansystem.domain.Application;
import dev.be.loansystem.domain.Entry;
import dev.be.loansystem.domain.Repayment;
import dev.be.loansystem.dto.BalanceDTO;
import dev.be.loansystem.dto.BalanceDTO.RepaymentRequest.RepaymentType;
import dev.be.loansystem.dto.RepaymentDTO.*;
import dev.be.loansystem.exception.BaseException;
import dev.be.loansystem.exception.ResultType;
import dev.be.loansystem.repository.ApplicationRepository;
import dev.be.loansystem.repository.EntryRepository;
import dev.be.loansystem.repository.RepaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepaymentServiceImpl implements RepaymentService{

    private final RepaymentRepository repaymentRepository;
    private final ApplicationRepository applicationRepository;
    private final EntryRepository entryRepository;

    private final BalanceService balanceService;

    private final ModelMapper modelMapper;

    @Override
    public Response create(Long applicationId, Request request) {
        if (!isRepayableApplication(applicationId)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Repayment repayment = modelMapper.map(request, Repayment.class);
        repayment.setApplicationId(applicationId);

        repaymentRepository.save(repayment);

        BalanceDTO.Response updatedBalance = balanceService.repaymentUpdate(applicationId,
                BalanceDTO.RepaymentRequest.builder()
                        .repaymentAmount(request.getRepaymentAmount())
                        .type(RepaymentType.REMOVE)
                        .build()
        );

        Response response = modelMapper.map(repayment, Response.class);
        response.setBalance(updatedBalance.getBalance());

        return response;
    }

    private boolean isRepayableApplication(Long applicationId) {
        Optional<Application> existedApplication = applicationRepository.findById(applicationId);

        if (existedApplication.isEmpty()) {
            return false;
        }

        if (existedApplication.get().getContractedAt() == null) {
            return false;
        }

        Optional<Entry> existedEntry = entryRepository.findByApplicationId(applicationId);
        return existedEntry.isPresent();
    }
}
