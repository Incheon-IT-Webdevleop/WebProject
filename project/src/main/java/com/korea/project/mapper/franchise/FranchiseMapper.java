package com.korea.project.mapper.franchise;

import com.korea.project.vo.franchise.FranchiseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FranchiseMapper {

    List<FranchiseVO> selectAllFranchises();

}