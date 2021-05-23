package com.podong.game.module.schduling.bean;

import com.podong.game.common.bean.PostFixVO;
import lombok.Data;

@Data
public class gameTargetVO extends PostFixVO {
    private String gameTargetId;
    private int gameGroupId;
    private String referenceType;

}
