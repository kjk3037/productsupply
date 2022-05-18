package cn.zq.pojo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kjk
 * @since 2022-05-18
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ActHiActinst implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private String procDefId;

    private String procInstId;

    private String executionId;

    private String actId;

    private String taskId;

    private String callProcInstId;

    private String actName;

    private String actType;

    private String assignee;

    private Date startTime;

    private Date endTime;

    private Long duration;

    private String tenantId;

    private String deleteReason;


}
