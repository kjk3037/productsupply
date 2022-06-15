package cn.zq.pojo;

import java.util.Date;
import java.io.Serializable;

import cn.zq.pojo.base.BaseMaterialInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class MaterialRequirementOutput extends BaseMaterialInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.INPUT)
      private Integer id;

    private String materialCode;

    private Integer count;

    private String businessKey;

      /**
     * 1、销售订单；2、用于生产
     */
      private Integer businessType;

      /**
     * 出库需求日期
     */
      private Date requireDate;

      /**
     * 1、销售需求；2、生产需求；
     */
      private Integer requireType;

      /**
     * 1、启用；2、关闭;
     */
      private Integer status;

    private Date createTime;

    private Date updateTime;
    @TableField(exist = false)
    private String requireTypeName;
    @TableField(exist = false)
    private String businessTypeName;
    @TableField(exist = false)
    private String statusName;
}
