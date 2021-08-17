package cn.zq.backstage.controller;


import cn.zq.backstage.service.WorkflewEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kjk
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/workflew/entity")
public class WorkflewEntityController {
    @Autowired
    WorkflewEntityService workflewEntityService;
    @GetMapping("/getLink")
    public List getLink(Integer eid){
        return workflewEntityService.getLink(eid);
    }
}

