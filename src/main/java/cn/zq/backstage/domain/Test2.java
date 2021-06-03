package cn.zq.backstage.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kjk
 * @since 2021-05-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Test2 implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String name;

    private String tt;


}
