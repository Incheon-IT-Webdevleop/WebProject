package com.korea.project.service.admin;

import com.korea.project.mapper.franchise.FranchiseMapper;
import com.korea.project.vo.franchise.FranchiseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminFranchiseServiceImpl implements AdminFranchiseService {

    private final FranchiseMapper franchiseMapper;

    @Autowired
    public AdminFranchiseServiceImpl(FranchiseMapper franchiseMapper) {
        this.franchiseMapper = franchiseMapper;
    }

    @Override
    public List<FranchiseVO> searchFranchisesByName(String name,int offset, int limit) {
        try {
            return franchiseMapper.searchFranchisesByName(name,offset, limit);
        } catch (DataAccessException e) {
            System.err.println("Failed to retrieve franchises by name: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public int countFranchisesByName(String name) {
        return franchiseMapper.countFranchisesByName(name);
    }

    @Override
    public FranchiseVO getFranchiseById(int id) {
        return franchiseMapper.selectFranchiseById(id);
    }

    @Override
    public void addFranchise(FranchiseVO franchise) {
        franchiseMapper.insertFranchise(franchise);
    }

    @Override
    public void updateFranchise(FranchiseVO franchise) {
        franchiseMapper.updateFranchise(franchise);
    }

    @Override
    public void deleteFranchise(int id) {
        franchiseMapper.deleteFranchise(id);
    }

    @Override
    public int countAllFranchises() {
        return franchiseMapper.countAllFranchises();
    }
    @Override
    public List<FranchiseVO> selectAllFranchisesPaged(int offset, int pageSize) {
        return franchiseMapper.selectAllFranchisesPaged(offset, pageSize);
    }
}
