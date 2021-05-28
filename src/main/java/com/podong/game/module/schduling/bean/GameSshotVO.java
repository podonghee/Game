package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;
import lombok.Data;

@Data
public class GameSshotVO  {
    private String gameId;
    private String gameSshotUrl;   // 게임메카 업체고유번호
    private String gameSshotImgUrl; // 이미지 url
    private int gameSshotId;
    private String gameSshotImg; // 스샷 이미지 인포 에 저장
    private String description; // 설명
    private int gameParentSshotId; // 그룹 상위 id
}
