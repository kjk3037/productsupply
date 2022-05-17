package cn.zq.pojo;

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
    public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String code;

      /**
     * 客户名称
     */
      private String name;

      /**
     * 地址
     */
      private String address;

      /**
     * 简称
     */
      private String nickname;

      /**
     * 电子邮箱
     */
      private String email;

    private String fax;

      /**
     * 电话号码
     */
      private String number;

      /**
     * 客户来源
     */
      private String source;

    private String attachment;

      /**
     * 描述
     */
      private String describe;

      /**
     * 客户经理(账号id)
     */
      private String customerManagerId;

    private Date createTime;

    private Date updateTime;


}
