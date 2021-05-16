package com.podong.game.module.schduling.bean;

import lombok.Data;

@Data
public class GameRankVO {
    private int gameRankId;
    private String gameId;
    private String gameRankYear;
    private String gameRankOneWeek;
    private String useYn;
    private int gameRankCount;
    private String gameRankStatus;
    private int gameRankPast;
    private String gameRankImg;
    private int gameLevel;
}
