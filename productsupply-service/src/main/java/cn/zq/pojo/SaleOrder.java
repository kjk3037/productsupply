package cn.zq.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

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
      private String userId;

    private Integer customerId;

      /**
     * 1、线下订单；2、电商月结；3、代销月结
     */
      private Integer orderType;

      /**
     * 要求入库日期
     */
      @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
      private Date inputRequireDate;

      /**
       * 要求入库日期
       */
      @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
      private Date outRequireDate;

      /**
     * 下单日期
     */
      @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
      private Date orderDate;

      /**
     * 结算方式
     */
      private Integer settlementMethod;

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
      **/
      private Integer orderStatus;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    private List<SaleOrderDetail> saleOrderDetails;
    private List<MultipartFile> attachment;
}
