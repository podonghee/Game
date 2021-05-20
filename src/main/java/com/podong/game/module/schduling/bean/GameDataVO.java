package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;
import lombok.Data;

@Data
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

}
