package cn.zq.domain;

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
 * @since 2022-04-19
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class DataField implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String bussinessKey;

    private String prop;

    private String label;


}