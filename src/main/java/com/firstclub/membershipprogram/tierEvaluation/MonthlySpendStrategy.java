package com.firstclub.membershipprogram.tierEvaluation;

import com.firstclub.membershipprogram.dtos.MembershipTierDto;
import com.firstclub.membershipprogram.entities.UserMonthlyMetricEntity;
import com.firstclub.membershipprogram.repositories.UserMonthlyMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MonthlySpendStrategy implements TierEvaluationStrategy {

    @Autowired
    UserMonthlyMetricRepository metricRepository;


    @Override
    public boolean qualifies(String userName, MembershipTierDto membershipTier) {
        String currentMonthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        Optional<UserMonthlyMetricEntity> metricsOpt = metricRepository.findByUserNameAndMonthYear(userName, currentMonthYear);

        if (metricsOpt.isEmpty()) {
            return false;
        }

        BigDecimal currentSpend = metricsOpt.get().getTotalOrderValue();
        BigDecimal requiredSpend = getRequiredSpendForTier(membershipTier.getName());

        return currentSpend.compareTo(requiredSpend) >= 0;
    }

    private BigDecimal getRequiredSpendForTier(String tierName) {
        return switch (tierName.toUpperCase()) {
            case "PLATINUM" -> new BigDecimal("500.00");
            case "GOLD" -> new BigDecimal("150.00");
            default -> BigDecimal.ZERO;
        };
    }
}
