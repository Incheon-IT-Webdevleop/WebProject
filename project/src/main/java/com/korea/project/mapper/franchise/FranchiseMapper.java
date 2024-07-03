package com.korea.project.mapper.franchise;

import com.korea.project.vo.franchise.FranchiseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FranchiseMapper {

    List<FranchiseVO> selectAllFranchises();
    List<FranchiseVO> selectFranchisesBySector(int sector);

}