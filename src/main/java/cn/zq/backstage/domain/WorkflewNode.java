package cn.zq.backstage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class WorkflewNode implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 0、开始节点；1、审批节点；2、填写节点；3、分支节点；4、抄送节点；5、结束节点
     */
      private Integer nodeType;

    private String nodeName;

      /**
     * 流程id
     */
      private Integer workflewId;

      /**
     * 前节点
     */
      private Integer prevNodeId;

      /**
     * 后节点
     */
      private Integer nextNodeId;

    private Date createDate;

    private Date updateDate;


}
