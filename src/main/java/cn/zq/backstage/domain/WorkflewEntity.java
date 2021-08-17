package cn.zq.backstage.domain;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
    public class WorkflewEntity implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private Integer moduleEntityId;

    private Integer workflewId;

    private Integer workflewNodeId;

    private Integer approverId;

    private String approverResult;

    private String approverSuggestion;

      /**
     * 1、运行中；2、已完成；3、已终止
     */
      private Integer status;

    private Date createTime;

    private Date updateTime;


}
