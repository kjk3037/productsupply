package cn.zq.backstage.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kjk
 * @since 2021-08-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class WorkflewApprover implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private Integer workflewId;

    private Integer workflewNodeId;

    private String approverType;

    private Integer approverId;


}
