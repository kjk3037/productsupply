package cn.zq.vo;

import lombok.Data;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityImpl;
@Data
public class ActivityVo extends HistoricActivityInstanceEntityImpl {
    String assigneeName;

}
