package cn.zq.service.impl;

import cn.zq.pojo.Attachment;
import cn.zq.dao.AttachmentMapper;
import cn.zq.service.AttachmentService;
import cn.zq.utils.FileUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjk
 * @since 2022-04-23
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
    @Autowired
    AttachmentMapper attachmentMapper;
    @Override
    public Boolean uploads(Map<String,List<MultipartFile>> files , String business, String businessKey) {
        LinkedList<Attachment> attachments = new LinkedList<>();
        for (Map.Entry<String,List<MultipartFile>> data : files.entrySet() ){
            List<String> paths =FileUtils.uploadFiles((MultipartFile[]) data.getValue().toArray());
            for (String path:paths){
                Attachment attachment = new Attachment();
                attachment.setBusiness(business);
                attachment.setBusinessKey(businessKey);
                attachment.setBusinessField(data.getKey());
                attachment.setPath(path);
                attachments.add(attachment);
            }
        }
        return saveBatch(attachments);
    }

    @Override
    public void getFile(String path,HttpServletResponse response) throws IOException {
        System.out.println("读取文件流并返回");
        //使用字节流读取本地图片
        ServletOutputStream out=null;
        BufferedInputStream buf=null;
        //创建了一个文件对象,对应的填图片存放的路径(需要的请填写自己的)
        File file = new File(path);
        response.setCharacterEncoding("utf-8");
        try {
            //使用输入读取缓冲流读取一个文件输入流
            buf = new BufferedInputStream(new FileInputStream(file));
            //利用respone获取一个字节流输出对象
            out = response.getOutputStream();
            //定义个数组,由于读取缓冲流的内容
            byte[] buffer = new byte[1024];
            //循环一直读取缓冲流中的内容到输出的对象中
            while (buf.read(buffer)!=-1){
                out.write(buffer);
            }
            //写出请求的地方
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭
            if (buf!=null) buf.close();
            if (out!=null) out.close();
        }
    }
}
