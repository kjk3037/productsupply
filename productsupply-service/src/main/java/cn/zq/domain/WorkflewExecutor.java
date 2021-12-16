package cn.zq.domain;

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
 * @since 2021-12-09
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class WorkflewExecutor implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private Integer workflewId;

    private Integer workflewNodeId;

      /**
     * 1、账号；2、角色；3、部门
     */
      private Integer executorType;

    private Date createTime;

    private Date updateTime;


}
