package com.korea.project.service.franchise;

import com.korea.project.mapper.franchise.FranchiseMapper;
import com.korea.project.vo.franchise.FranchiseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FranchiseService {

    private final FranchiseMapper franchiseMapper;

    @Autowired
    public FranchiseService(FranchiseMapper franchiseMapper) {
        this.franchiseMapper = franchiseMapper;
    }

    public List<FranchiseVO> getAllFranchises() {
        try {
            List<FranchiseVO> franchises = franchiseMapper.selectAllFranchises();
            System.out.println("Retrieved franchises: " + franchises.size());
            return franchises;
        } catch (DataAccessException e) {
            // 예외 처리
            System.err.println("Failed to retrieve franchises: " + e.getMessage());
            throw e; // 예외 다시 던지기
        }
    }

    // 필요한 다른 비즈니스 로직 메서드 추가 가능
}
