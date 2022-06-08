package cn.zq.service.impl;

import cn.zq.pojo.BillOfMaterial;
import cn.zq.dao.BillOfMaterialMapper;
import cn.zq.service.BillOfMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2022-06-08
 */
@Service
public class BillOfMaterialServiceImpl extends ServiceImpl<BillOfMaterialMapper, BillOfMaterial> implements BillOfMaterialService {

    @Override
    public List getBOM(String parentCode) {
        QueryWrapper<BillOfMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_code",parentCode);
        LinkedList<BillOfMaterial> list = (LinkedList<BillOfMaterial>) list(queryWrapper);
        return list;
    }

    @Override
    public List getCompleteBOM(String parentCode,String parentLevel) {
        List<BillOfMaterial> completeBOM = new LinkedList<>();
        QueryWrapper<BillOfMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_code",parentCode).orderByAsc("id");
        List<BillOfMaterial> BOM = list(queryWrapper);
        if (BOM!=null && BOM.size()!=0){
            Integer cLevel=1;
            StringBuffer level;
            if (parentLevel.equals("0")){
                level=new StringBuffer();
            }else {
                level=new StringBuffer(parentLevel);
                level.append(".");
            }
            for (BillOfMaterial child:BOM){
                StringBuffer nowlevel=new StringBuffer(level);
                nowlevel.append(cLevel++);
                child.setLevel(nowlevel.toString());
                completeBOM.add(child);
                List childBom = getCompleteBOM(child.getMaterialCode(),nowlevel.toString());
                if (childBom!=null&&childBom.size()!=0){
                    completeBOM.addAll(childBom);
                }else{
                    continue;
                }
            }
        }
        return completeBOM;
    }
}
