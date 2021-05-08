package com.podong.game.controller;

import com.podong.game.common.bean.RequestData;
import com.podong.game.common.controller.CommonController;
import com.podong.game.common.service.CommonService;
import com.podong.game.module.game.module.GameModule;
import com.podong.game.module.schduling.BatchScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class GameController extends CommonController {

    @Autowired
    @Qualifier("gameMekaServiceImpl")
    private CommonService gameMekaService;
    @Autowired
    @Qualifier("gameModuleImpl")
    private GameModule gameModule;
    @Autowired
    private BatchScheduling batchScheduling;

    @Override
    @RequestMapping("/command")
    @ResponseBody
    public Object command(@RequestBody RequestData request) {
        return _command(request, gameMekaService);
    }
}





