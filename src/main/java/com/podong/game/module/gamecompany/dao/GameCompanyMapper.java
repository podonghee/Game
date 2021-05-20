package com.podong.game.module.gamecompany.dao;


import com.podong.game.module.game.bean.GameVO;
import com.podong.game.module.gamecompany.bean.GameCompanyInfoVO;
import com.podong.game.module.schduling.bean.GameCompanyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface GameCompanyMapper {
    List<GameCompanyInfoVO> getGameCompanyList(Object data);
    int getGameCompanyCnt(Object data);
    List<GameCompanyInfoVO> getGameCompanyDetail(Object data);
}
