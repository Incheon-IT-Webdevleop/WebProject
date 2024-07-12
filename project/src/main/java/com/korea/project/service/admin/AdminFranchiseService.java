package com.korea.project.service.admin;

import com.korea.project.mapper.franchise.FranchiseMapper;
import com.korea.project.vo.franchise.FranchiseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminFranchiseService {

    private final FranchiseMapper franchiseMapper;

    @Autowired
    public AdminFranchiseService(FranchiseMapper franchiseMapper) {
        this.franchiseMapper = franchiseMapper;
    }

    public List<FranchiseVO> getAllFranchises() {
        return franchiseMapper.selectAllFranchises();
    }

    public FranchiseVO getFranchiseById(int id) {
        return franchiseMapper.selectFranchiseById(id);
    }

    public void addFranchise(FranchiseVO franchise) {
        franchiseMapper.insertFranchise(franchise);
    }

    public void updateFranchise(FranchiseVO franchise) {
        franchiseMapper.updateFranchise(franchise);
    }

    public void deleteFranchise(int id) {
        franchiseMapper.deleteFranchise(id);
    }
}