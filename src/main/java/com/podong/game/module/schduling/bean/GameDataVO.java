package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;

public class GameDataVO extends PostFixVO {
    private String gameId; // 게임번호
    private String gameName; //게임명
    private String platform; // 플랫폼
    private String genre;    // 장르
    private String producer; //제작사
    private String provider; //제공업체
    private String useCount; //이용등급
    private String yearRelase; //출시년도
    private String img; // 이미지
    private String useYn; // 사용여부
    public String getGameId() {
        return gameId;
    }

    public GameDataVO setGameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public String getGameName() {
        return gameName;
    }

    public GameDataVO setGameName(String gameName) {
        this.gameName = gameName;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public GameDataVO setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public GameDataVO setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public GameDataVO setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public String getProvider() {
        return provider;
    }

    public GameDataVO setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public String getUseCount() {
        return useCount;
    }

    public GameDataVO setUseCount(String useCount) {
        this.useCount = useCount;
        return this;
    }

    public String getYearRelase() {
        return yearRelase;
    }

    public GameDataVO setYearRelase(String yearRelase) {
        this.yearRelase = yearRelase;
        return this;
    }

    public String getImg() {
        return img;
    }

    public GameDataVO setImg(String img) {
        this.img = img;
        return this;
    }

    public String getUseYn() {
        return useYn;
    }

    public GameDataVO setUseYn(String useYn) {
        this.useYn = useYn;
        return this;
    }
}
