package cn.zq.pojo.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseMaterialInfo {
    @TableField(exist = false)
    String materialName;
    @TableField(exist = false)
    String materialStandard;
    @TableField(exist = false)
    String materialModel;
}
