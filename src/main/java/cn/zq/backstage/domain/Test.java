package cn.zq.backstage.domain;

import java.time.LocalDateTime;
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
    public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String name;

    private LocalDateTime date;


}
