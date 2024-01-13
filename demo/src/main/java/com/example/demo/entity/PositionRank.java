package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionRank {
    public PositionRank() {
    }

    public PositionRank(Integer id, String positionRank) {
        this.id = id;
        this.positionRank = positionRank;
    }

    Integer id;
    String positionRank;
}
