package cn.zq.pojo;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kjk
 * @since 2022-06-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class MaterialRequirementInput extends MaterialInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String materialCode;

      /**
     * 1、销售订单；2、库存订单；3、其他入库需求申请；4、计划所得
     */
      private Integer businessType;

    private String businessKey;

    private Integer count;

    private Date requireDate;

      /**
     * 1、销售需求；2、生产需求；3、其他需求
     */
      private Integer requireType;

      /**
     * 1、待计划；2、已计划；3、取消
     */
      private Integer status;

    private Date createTime;

    private Date updateTime;
  @TableField(exist = false)
  private String materialName;
  @TableField(exist = false)
  private String materialStandard;
  @TableField(exist = false)
  private String materialMode;

}
