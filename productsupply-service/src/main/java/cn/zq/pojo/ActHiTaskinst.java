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
    public class ActHiTaskinst implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private String procDefId;

    private String taskDefKey;

    private String procInstId;

    private String executionId;

    private String name;

    private String parentTaskId;

    private String description;

    private String owner;

    private String assignee;

    private Date startTime;

    private Date claimTime;

    private Date endTime;

    private Long duration;

    private String deleteReason;

    private Integer priority;

    private Date dueDate;

    private String formKey;

    private String category;

    private String tenantId;


}
