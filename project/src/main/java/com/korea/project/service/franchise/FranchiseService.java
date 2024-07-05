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

    public List<FranchiseVO> getAllFranchisesPaged(int offset, int limit) {
        try {
            return franchiseMapper.selectAllFranchisesPaged(offset, limit);
        } catch (DataAccessException e) {
            System.err.println("Failed to retrieve franchises: " + e.getMessage());
            throw e;
        }
    }

    public List<FranchiseVO> getFranchisesBySectorPaged(int sector, int offset, int limit) {
        try {
            return franchiseMapper.selectFranchisesBySectorPaged(sector, offset, limit);
        } catch (DataAccessException e) {
            System.err.println("Failed to retrieve franchises for sector " + sector + ": " + e.getMessage());
            throw e;
        }
    }

    public List<FranchiseVO> getAllFranchises() {
        try {
            return franchiseMapper.selectAllFranchises();
        } catch (DataAccessException e) {
            System.err.println("Failed to retrieve all franchises: " + e.getMessage());
            throw e;
        }
    }

    public int countAllFranchises() {
        try {
            return franchiseMapper.countAllFranchises();
        } catch (DataAccessException e) {
            System.err.println("Failed to count all franchises: " + e.getMessage());
            throw e;
        }
    }

    public int countFranchisesBySector(int sector) {
        try {
            return franchiseMapper.countFranchisesBySector(sector);
        } catch (DataAccessException e) {
            System.err.println("Failed to count franchises for sector " + sector + ": " + e.getMessage());
            throw e;
        }
    }
    //검색창
    public List<FranchiseVO> searchFranchisesByName(String name, int offset, int limit) {
        return franchiseMapper.searchFranchisesByName(name, offset, limit);
    }

    public int countFranchisesByName(String name) {
        return franchiseMapper.countFranchisesByName(name);
    }
    public FranchiseVO getFranchiseById(Long id) {
        try {
            return franchiseMapper.selectFranchiseById(id);
        } catch (DataAccessException e) {
            System.err.println("Failed to retrieve franchise with id " + id + ": " + e.getMessage());
            throw e;
        }
    }
}