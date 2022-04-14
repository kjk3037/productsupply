package cn.zq.domain;

import java.math.BigDecimal;
import java.util.Date;
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
    public class SaleOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private Integer orderId;

    private String orderCode;

    private String materialCode;

      /**
     * 数量
     */
      private Integer count;

      /**
     * 含税金额
     */
      private BigDecimal priceIncludingTax;

    private BigDecimal amountIncludingTax;

    private Float taxRate;

      /**
     * 箱规
     */
      private String packingSpecification;

      /**
     * 包装要求
     */
      private String packingRequirement;

      /**
     * 备注
     */
      private String remark;

      /**
     * 已发货数量
     */
      private Integer deliveredCount;

    private Date createTime;

    private Date updateTime;


}
