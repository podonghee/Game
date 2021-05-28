package com.podong.game.module.gamevideo.dao;

import com.podong.game.module.gamevideo.bean.GameVideoInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface GameVideoMapper {
    List<HashMap<String,Object>> getGameVideoList(Object data);
    int getGameVideoCnt(Object data);
    List<HashMap<String,Object>> getDetailVideoList(Object data);
    int getDetailVideoCnt(Object data);
    List<HashMap<String,Object>> getDetailToList(Object data);
}
