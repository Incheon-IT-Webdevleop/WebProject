package com.korea.project.service.franchise;

import com.korea.project.mapper.franchise.FranchiseMapper;
import com.korea.project.vo.franchise.FranchiseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseService {
    private final FranchiseMapper franchiseMapper;

    @Autowired
    public FranchiseService(FranchiseMapper franchiseMapper) {
        this.franchiseMapper = franchiseMapper;
    }

    public List<FranchiseVO> getAllFranchises() {
        List<FranchiseVO> franchises = franchiseMapper.selectAllFranchises();
        System.out.println("Retrieved franchises: " + franchises.size());
        return franchises;
    }
}