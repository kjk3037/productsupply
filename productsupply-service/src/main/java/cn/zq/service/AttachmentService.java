package cn.zq.service;

import cn.zq.pojo.Attachment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kjk
 * @since 2022-04-23
 */
public interface AttachmentService extends IService<Attachment> {
    Boolean uploads(Map<String,List<MultipartFile>> files , String business, String businessKey);
    void getFile(String path,HttpServletResponse response) throws IOException;
}
