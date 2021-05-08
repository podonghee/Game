package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;

public class GameCompanyVO extends PostFixVO {
    private String gameCompanyCid;   // 게임메카 업체고유번호
    private String gameCompanyName;  // 게임업체 명
    private String gameCompanyTel;   // 게임업체 전화번호
    private String gameCompanyAddr;  // 게임업체 주소
    private String gameCompanySite;  // 게임업체 사이트
    private String gameCompanyImg;   // 게임업체 이미지
    private String useYn;            // 사용여부

    public String getGameCompanyCid() {
        return gameCompanyCid;
    }

    public GameCompanyVO setGameCompanyCid(String gameCompanyCid) {
        this.gameCompanyCid = gameCompanyCid;
        return this;
    }

    public String getGameCompanyName() {
        return gameCompanyName;
    }

    public GameCompanyVO setGameCompanyName(String gameCompanyName) {
        this.gameCompanyName = gameCompanyName;
        return this;
    }

    public String getGameCompanyTel() {
        return gameCompanyTel;
    }

    public GameCompanyVO setGameCompanyTel(String gameCompanyTel) {
        this.gameCompanyTel = gameCompanyTel;
        return this;
    }

    public String getGameCompanyAddr() {
        return gameCompanyAddr;
    }

    public GameCompanyVO setGameCompanyAddr(String gameCompanyAddr) {
        this.gameCompanyAddr = gameCompanyAddr;
        return this;
    }

    public String getGameCompanySite() {
        return gameCompanySite;
    }

    public GameCompanyVO setGameCompanySite(String gameCompanySite) {
        this.gameCompanySite = gameCompanySite;
        return this;
    }

    public String getGameCompanyImg() {
        return gameCompanyImg;
    }

    public GameCompanyVO setGameCompanyImg(String gameCompanyImg) {
        this.gameCompanyImg = gameCompanyImg;
        return this;
    }

    public String getUseYn() {
        return useYn;
    }

    public GameCompanyVO setUseYn(String useYn) {
        this.useYn = useYn;
        return this;
    }
}
