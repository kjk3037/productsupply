package cn.zq.pojo;

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
    public class ActHiIdentitylink implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private String groupId;

    private String type;

    private String userId;

    private String taskId;

    private String procInstId;

//    private Ru
}
