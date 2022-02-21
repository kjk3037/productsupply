package cn.zq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kjk
 * @since 2022-02-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 权限描述
     */
      private String describe;

      /**
     * 是否启用（0禁用，1启用）
     */
      private Integer status;

    private String url;

    private String permission;

    private Date createTime;

    private Date updateTime;


}
