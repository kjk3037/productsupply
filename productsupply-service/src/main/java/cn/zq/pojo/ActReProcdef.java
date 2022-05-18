package cn.zq.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kjk
 * @since 2022-05-18
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ActReProcdef implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private Integer rev;

    private String category;

    private String name;

    private String key;

    private Integer version;

    private String deploymentId;

    private String resourceName;

    private String dgrmResourceName;

    private String description;

    private Integer hasStartFormKey;

    private Integer hasGraphicalNotation;

    private Integer suspensionState;

    private String tenantId;

    private String engineVersion;


}
