package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;
import lombok.Data;

@Data
public class GameSshotVO extends PostFixVO {
    private String gameId;
    private String gameSshotUrl;   // 게임메카 업체고유번호
    private String gameSshotImgUrl; //
}
