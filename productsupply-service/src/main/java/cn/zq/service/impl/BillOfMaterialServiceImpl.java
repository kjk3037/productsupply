package cn.zq.service.impl;

import cn.zq.pojo.BillOfMaterial;
import cn.zq.dao.BillOfMaterialMapper;
import cn.zq.pojo.MaterialInfo;
import cn.zq.service.BillOfMaterialService;
import cn.zq.service.MaterialInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    BillOfMaterialMapper billOfMaterialMapper;
    @Autowired
    MaterialInfoService materialInfoService;
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
        List<BillOfMaterial> BOM = billOfMaterialMapper.selectByCode(parentCode);
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

    @Override
    public List getListBOMDetail(List codes) {
        QueryWrapper<BillOfMaterial> BOMQueryWrapper = new QueryWrapper<>();
        BOMQueryWrapper.in("parent_code",codes);
        LinkedList<BillOfMaterial> BOMDetailList = (LinkedList<BillOfMaterial>) list(BOMQueryWrapper);
        QueryWrapper<MaterialInfo> MaterialQueryWrapper = new QueryWrapper<>();
        BOMQueryWrapper.in("code",codes);
        List<MaterialInfo> materialInfolist = materialInfoService.list(MaterialQueryWrapper);
        for (MaterialInfo materialInfo:materialInfolist){

        }
        return null;
    }
}
