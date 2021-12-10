package cn.zq.backstage.domain;

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
 * @since 2021-09-03
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class WorkflewEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer subModuleId;

    private Integer workflewId;

    private Integer workflewNodeId;

    private String prevEntityId;

    private String executorId;

    private String executorResult;

    private String executorSuggestion;

      /**
     * 1、运行中；2、已完成；3、已终止
     */
      private Integer status;

    private Date createTime;

    private Date updateTime;


}
