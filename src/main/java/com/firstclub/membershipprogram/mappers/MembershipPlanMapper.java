package com.firstclub.membershipprogram.mappers;

import com.firstclub.membershipprogram.dtos.MembershipPlanDto;
import com.firstclub.membershipprogram.entities.MembershipPlanEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MembershipPlanMapper {

    public MembershipPlanDto map(MembershipPlanEntity entity) {
        MembershipPlanDto dto = new MembershipPlanDto();

        dto.setId(entity.getId());
        dto.setPlanCode(entity.getPlanCode());
        dto.setPlanName(entity.getPlanName());
        dto.setPrice(entity.getPrice());
        dto.setActive(entity.getActive());
        dto.setDurationDays(entity.getDurationDays());

        return dto;
    }

    public MembershipPlanEntity reverse(MembershipPlanDto dto) {
        MembershipPlanEntity entity = new MembershipPlanEntity();

        entity.setId(dto.getId());
        entity.setPlanCode(dto.getPlanCode());
        entity.setPlanName(dto.getPlanName());
        entity.setPrice(dto.getPrice());
        entity.setActive(dto.getActive());
        entity.setDurationDays(dto.getDurationDays());

        return entity;
    }

    public List<MembershipPlanDto> map(List<MembershipPlanEntity> entities){
        List<MembershipPlanDto> dtoList = new ArrayList<>();
        entities.forEach(entity -> dtoList.add(map(entity)));

        return dtoList;
    }
}
