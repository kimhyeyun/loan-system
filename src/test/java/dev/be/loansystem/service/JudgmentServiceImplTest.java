package dev.be.loansystem.service;

import dev.be.loansystem.domain.Application;
import dev.be.loansystem.domain.Judgment;
import dev.be.loansystem.dto.ApplicationDTO.GrantAmount;
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

    @Test
    void Should_ReturnsResponseOfExistJudgmentEntity_When_RequestExistJudgmentId() {
        Long findId = 1L;

        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .build();

        when(judgmentRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = judgmentService.get(1L);

        assertThat(actual.getJudgmentId()).isSameAs(findId);
    }

    @Test
    void Should_ReturnsResponseOfExistJudgmentEntity_When_RequestExistApplicationId() {
        Long findId = 1L;

        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(applicationEntity));
        when(judgmentRepository.findByApplicationId(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = judgmentService.getJudgmentOfApplication(findId);

        assertThat(actual.getJudgmentId()).isSameAs(findId);
    }

    @Test
    void Should_ReturnUpdatedResponseOfExistJudgmentEntity_When_RequestUpdateExistsJudgmentInfo() {
        Long findId = 1L;

        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .name("Kim")
                .build();

        Request request = Request.builder()
                .name("Yun")
                .build();

        when(judgmentRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));
        when(judgmentRepository.save(any(Judgment.class))).thenReturn(entity);

        Response actual = judgmentService.update(findId, request);

        assertThat(actual.getJudgmentId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }

    @Test
    void Should_DeletedJudgmentEntity_When_RequestDeleteExistsJudgmentInfo() {
        Long targetId = 1L;

        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .build();

        when(judgmentRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));
        when(judgmentRepository.save(any(Judgment.class))).thenReturn(entity);

        judgmentService.delete(targetId);

        assertThat(entity.getIsDeleted()).isTrue();
    }

    @Test
    void Should_ReturnUpdateResponseOfExistsApplicationEntity_When_RequestGrantApprovalAmountOfJudgmentInfo() {
        Long findId = 1L;

        Judgment judgmentEntity = Judgment.builder()
                .name("Kim")
                .applicationId(findId)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(findId)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        when(judgmentRepository.findById(findId)).thenReturn(Optional.ofNullable(judgmentEntity));
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(applicationEntity));
        when(applicationRepository.save(any(Application.class))).thenReturn(applicationEntity);

        GrantAmount actual = judgmentService.grant(findId);

        assertThat(actual.getApplicationId()).isSameAs(findId);
        assertThat(actual.getApprovalAmount()).isSameAs(judgmentEntity.getApprovalAmount());
    }
}