package com.firstclub.membershipprogram.tierEvaluation;

import com.firstclub.membershipprogram.dtos.MembershipTierDto;
import com.firstclub.membershipprogram.entities.UserMonthlyMetricEntity;
import com.firstclub.membershipprogram.repositories.UserMonthlyMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class OrderCountStrategy implements TierEvaluationStrategy {

    @Autowired
    UserMonthlyMetricRepository metricRepository;

    @Override
    public boolean qualifies(String userName, MembershipTierDto membershipTier) {
        String currentMonthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        Optional<UserMonthlyMetricEntity> metricsOpt = metricRepository.findByUserNameAndMonthYear(userName, currentMonthYear);

        if (metricsOpt.isEmpty()) {
            return false;
        }

        UserMonthlyMetricEntity metrics = metricsOpt.get();
        int requiredOrders = getRequiredOrdersForTier(membershipTier.getName());

        return metrics.getOrderCount() >= requiredOrders;
    }

    private int getRequiredOrdersForTier(String tierName) {
        return switch (tierName.toUpperCase()) {
            case "PLATINUM" -> 15;
            case "GOLD" -> 5;
            default -> 0;
        };
    }
}
