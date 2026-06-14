package com.firstclub.membershipprogram.mappers;

import com.firstclub.membershipprogram.dtos.MembershipTierDto;
import com.firstclub.membershipprogram.entities.MembershipTierEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MembershipTierMapper {

    public List<MembershipTierDto> map(List<MembershipTierEntity> entities) {
        List<MembershipTierDto> dtoList = new ArrayList<>();

        entities.forEach(entity -> dtoList.add(map(entity)));

        return dtoList;
    }

    public MembershipTierDto map(MembershipTierEntity entity) {
        MembershipTierDto dto = new MembershipTierDto();
        dto.setName(entity.getName());

        return dto;
    }
}
