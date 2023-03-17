package dev.be.loansystem.service;

import dev.be.loansystem.domain.Application;
import dev.be.loansystem.domain.Judgment;
import dev.be.loansystem.dto.JudgmentDTO.*;
import dev.be.loansystem.repository.ApplicationRepository;
import dev.be.loansystem.repository.JudgmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JudgmentServiceImplTest {

    @InjectMocks JudgmentServiceImpl judgmentService;

    @Mock private JudgmentRepository judgmentRepository;
    @Mock private ApplicationRepository applicationRepository;

    @Spy private ModelMapper modelMapper;

    @Test
    void Shoule_ReturnsResponseOfNewJudgmentEntity_When_RequestNewJudgment() {
        Judgment entity = Judgment.builder()
                .name("Member Kim")
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(50000000))
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .build();

        Request request = Request.builder()
                .name("Member Kim")
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(50000000))
                .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(judgmentRepository.save(any(Judgment.class))).thenReturn(entity);

        Response actual = judgmentService.create(request);

        assertThat(actual.getJudgmentId()).isSameAs(entity.getJudgmentId());
        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getApplicationId()).isSameAs(entity.getApplicationId());
        assertThat(actual.getApprovalAmount()).isSameAs(entity.getApprovalAmount());
    }
}