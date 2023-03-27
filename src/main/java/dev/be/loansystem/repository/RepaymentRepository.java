package dev.be.loansystem.repository;

import dev.be.loansystem.domain.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
}
