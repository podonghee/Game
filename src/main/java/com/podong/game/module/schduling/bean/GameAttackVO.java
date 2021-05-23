package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;
import lombok.Data;

@Data
public class GameAttackVO extends PostFixVO {
    private String gameId;
    private String gameAttackUrl;   // 게임메카 업체고유번호
    private String gameAttackImgUrl; // 이미지 url
}
