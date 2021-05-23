package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;
import lombok.Data;

@Data
public class GameSshotVO extends gameTargetVO {
    private String gameId;
    private String gameSshotUrl;   // 게임메카 업체고유번호
    private String gameSshotImgUrl; // 이미지 url
    private String gameSshotId;
    private String gameSshotImg; // 스샷 이미지 인포 에 저장
}
