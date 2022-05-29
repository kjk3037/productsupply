package cn.zq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kjk
 * @since 2022-05-29
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class FieldStatus implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String procDefKey;

    private String taskDefKey;

    private String fieldName;

      /**
     * 1、编辑；2、只读；3、隐藏
     */
      private Integer status;

      /**
     * 1、是；2、否
     */
      @TableField("isList")
    private Integer islist;


}
