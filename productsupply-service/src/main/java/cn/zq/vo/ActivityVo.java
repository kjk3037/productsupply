package cn.zq.vo;

import cn.zq.pojo.User;
import lombok.Data;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityImpl;

import java.util.List;
import java.util.Map;

@Data
public class ActivityVo extends HistoricActivityInstanceEntityImpl {
//    public ActivityVo(HistoricActivityInstance instance){
//        this.assignee=instance.getAssignee();
//        this.activityId=instance.getActivityId();
//        this.activityName=instance.getActivityName();
//        this.activityType=instance.getActivityType();
//
//    }
    UserVo assigneeInfo;
    List candidates;
}
