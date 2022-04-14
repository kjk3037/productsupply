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
 * @since 2022-04-13
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class MaterialInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 自增计数
     */
        @TableId(value = "atid", type = IdType.AUTO)
      private Integer atid;

      /**
     * 物料代码
     */
      private String code;

      /**
     * 物料名称
     */
      private String materialName;

      /**
     * 规格
     */
      private String standard;

      /**
     * 工艺
     */
      private String technology;

      /**
     * 品牌
     */
      private String brand;

      /**
     * 重要性
     */
      private String importance;

      /**
     * 属性
     */
      private String field;

    private String desc;

    private Date createTime;

    private Date updateTime;


}
