package cn.zq.backstage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-05-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class AccountDept implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 部门名称
     */
      private String name;
    @TableField("`desc`")
    private String desc;

      /**
     * 上级部门
     */
      private Integer parentId;

    private Date createTime;

    private Date updateTime;
}
