package com.thymeleaf.dao;

import com.thymeleaf.entity.PositionRank;

import java.util.List;

public interface PositionRankDao {
    List<PositionRank> findAll();
}
