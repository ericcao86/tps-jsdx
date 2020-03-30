package com.iflytek.tps;

import com.iflytek.tps.foun.dto.AppResponse;
import com.iflytek.tps.service.LogOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IpsController {

    @Autowired
    private LogOperService logOperService;

    @PostMapping(value = "/index")
    @ResponseBody
    public AppResponse<String> logOper(){
        return logOperService.readLog("ats");
    }
}
