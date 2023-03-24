package dev.be.loansystem.repository;

import dev.be.loansystem.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findAllByApplicationId(Long applicationId);
}
