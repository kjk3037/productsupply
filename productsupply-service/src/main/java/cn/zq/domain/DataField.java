package cn.zq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-04-19
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class DataField implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String bussinessKey;

    private String prop;

    private String label;

  private int fieldType;

  private int width;

  private String parentKey;

  private String parentId;

  private Integer isList;

  private List<DataField> childs;
}
