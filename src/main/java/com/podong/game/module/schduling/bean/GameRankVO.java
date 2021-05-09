package com.podong.game.module.schduling.bean;

import lombok.Data;

@Data
public class GameRankVO {
    private int gameRankId;
    private String gameId;
    private String gameRankYear;
    private String gameRankOneweek;
    private String useYn;
    private String gameRankCount;
    private String gameRankStatus;
    private String gameRankPast;
    private String gameRankImg;
}
