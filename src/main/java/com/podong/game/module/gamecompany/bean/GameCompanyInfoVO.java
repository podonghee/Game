package com.podong.game.module.gamecompany.bean;

import lombok.Data;

import java.util.List;

@Data
public class GameCompanyInfoVO  {
    private String gameCompanyCid;   // 게임메카 업체고유번호
    private String gameCompanyName;  // 게임업체 명
    private String gameCompanyTel;   // 게임업체 전화번호
    private String gameCompanyAddr;  // 게임업체 주소
    private String gameCompanySite;  // 게임업체 사이트
    private String gameCompanyImg;   // 게임업체 이미지
    private int gameMakeCnt; // 게임 만든 카운트
    private int gameServiceCnt; // 게임 서비스 카운트
    private List makeGameList; // 개발중인 게임리스트
    private List serviceGameList; // 서비스 중인 게임리스트

}
