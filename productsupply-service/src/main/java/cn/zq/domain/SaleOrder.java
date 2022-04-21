package cn.zq.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
    public class SaleOrder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 销售订单流码
     */
      private String code;

    private Integer customerId;

      /**
     * 1、线下订单；2、电商月结；3、代销月结
     */
      private Integer orderType;

      /**
     * 要求入库日期
     */
      private Date inputRequireDate;

      /**
       * 要求入库日期
       */
      private Date outRequireDate;

      /**
     * 下单日期
     */
      private Date orderDate;

      /**
     * 结算方式
     */
      private Integer settlementMethod;

      /**
     * 附件(多为合同)
     */
      private String attachment;

      /**
     * 订单金额(含税)
     */
      private BigDecimal amount;

      /**
     * 税率
     */
      private Float taxRate;

      /**
     * 折扣金额
     */
      private BigDecimal discount;

      /**
     * 发货状态：1、已完成；0、未完成
     */
      private Integer deliveryStatus;

      /**
     * 收款状态：1、已完成；0、未完成
     */
      private Integer receiptStatus;
      /*
      *@describe 1、进行中；2、已完成；3、流程中；4、作废
      *@param
      **/
      private Integer orderStatus;

    private Date createTime;

    private Date updateTime;

    private List<SaleOrderDetail> saleOrderDetails;
}
