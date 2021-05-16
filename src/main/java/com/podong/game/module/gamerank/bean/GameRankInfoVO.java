package com.podong.game.module.gamerank.bean;

import lombok.Data;

@Data
public class GameRankInfoVO {
    private String gameRankId;
    private String gameId;
    private int gameRankCount;
    private int gameRankPast;
    private String gameRankStatus;
    private String gameRankImg;
    private String gameName;
    private String platform;
    private String producer;
    private String serviceStatus;
}
