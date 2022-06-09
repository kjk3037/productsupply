package cn.zq.pojo;

import java.math.BigDecimal;
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
 * @since 2022-06-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class BillOfMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

      /**
     * 父物料编码
     */
      private String parentCode;

      /**
     * 用料编码
     */
      private String materialCode;

      /**
     * 1、启用；0、禁用
     */
      private Integer status;

      /**
     * 重要性：1、※；2、※※ ；3、※※※
     */
      private Integer importance;

      /**
     * 工艺
     */
      private String technology;

      /**
     * 单位用量
     */
      private Integer count;

      /**
     * 损耗率
     */
      private BigDecimal lossRate;

      /**
     * 供应商
     */
      private String supplier;

      /**
     * 位号
     */
      private String tagNumber;

      /**
     * 备注
     */
      private String remark;

    private Date createTime;

    private Date updateTime;
    //层级
    @TableField(exist = false)
    private String level;
    //物料名称
    @TableField(exist = false)
    private String materialName;
    //物料规格
    @TableField(exist = false)
    private String materialStandard;
    //物料型号
    @TableField(exist = false)
    private String materialModel;

}
