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
    public class ActHiProcinst implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private String procInstId;

    private String businessKey;

    private String procDefId;

    private Date startTime;

    private Date endTime;

    private Long duration;

    private String startUserId;

    private String startActId;

    private String endActId;

    private String superProcessInstanceId;

    private String deleteReason;

    private String tenantId;

    private String name;


}
