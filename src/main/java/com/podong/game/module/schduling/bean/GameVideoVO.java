package com.podong.game.module.schduling.bean;

import lombok.Data;

@Data
public class GameVideoVO {
    private String gameId;
    private String gameVideoUrl;   // 게임메카 업체고유번호
    private String gameVideoImgUrl; // 이미지 url
    private String gameVideoTitle; // 메인 타이틀
    private int gameVideoId;
    private String gameVideoImg; // 스샷 이미지 인포 에 저장
    private String description; // 설명
    private int gameParentVideoId; // 그룹 상위 id

}
