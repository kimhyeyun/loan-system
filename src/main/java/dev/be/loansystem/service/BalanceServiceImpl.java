package dev.be.loansystem.service;

import dev.be.loansystem.domain.Balance;
import dev.be.loansystem.dto.BalanceDTO.*;
import dev.be.loansystem.exception.BaseException;
import dev.be.loansystem.exception.ResultType;
import dev.be.loansystem.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService{

    private final BalanceRepository balanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Long applicationId, CreateRequest request) {
        if (balanceRepository.findAllByApplicationId(applicationId).isPresent()) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Balance balance = modelMapper.map(request, Balance.class);

        // 첫 생성은 entry amount 를 balance
        BigDecimal entryAmount = request.getEntryAmount();
        balance.setApplicationId(applicationId);
        balance.setBalance(entryAmount);

        Balance saved = balanceRepository.save(balance);

        return modelMapper.map(saved, Response.class);
    }
}
