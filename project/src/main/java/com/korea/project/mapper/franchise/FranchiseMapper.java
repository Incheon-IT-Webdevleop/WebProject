package com.korea.project.mapper.franchise;

import com.korea.project.vo.franchise.FranchiseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FranchiseMapper {

    List<FranchiseVO> selectAllFranchisesPaged(@Param("offset") int offset, @Param("limit") int limit);

    List<FranchiseVO> selectFranchisesBySectorPaged(@Param("sector") int sector, @Param("offset") int offset, @Param("limit") int limit);

    List<FranchiseVO> selectAllFranchises();

    List<FranchiseVO> selectFranchisesBySector(int sector);

    int countAllFranchises();

    int countFranchisesBySector(@Param("sector") int sector);
    
    // 검색창
    List<FranchiseVO> searchFranchisesByName(@Param("name") String name, @Param("offset") int offset, @Param("limit") int limit);

    int countFranchisesByName(@Param("name") String name);
    
    // 상세창 받아오기
    FranchiseVO selectFranchiseById(@Param("id") Long id);
    }