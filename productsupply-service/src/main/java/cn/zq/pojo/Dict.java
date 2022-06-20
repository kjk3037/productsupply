package cn.zq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-06-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer typeId;

      /**
     * 数值
     */
      private Integer value;

      /**
     * 名称
     */
      private String name;

    private String desc;


}
