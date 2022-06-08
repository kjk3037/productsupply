package cn.zq.utils;

import cn.zq.common.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class FileUtils {
    public static File createFile(File file,Integer num) throws IOException {
        if(!file.exists()){
            file.createNewFile();
            return file;
        }else {
            Integer count =++num;
            StringBuilder s = new StringBuilder(file.getPath().split("\\.")[0]);
            File newFile = new File(s.append("(" + count + ")"+"." + file.getPath().split("\\.")[1]).toString());
            return createFile(newFile,count);
        }
    }
    public static List<String> uploadFiles(MultipartFile[] files){
        List<String> filesName=new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                InputStream is = file.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                IOUtils.copy(is, os);
                byte[] bytes = os.toByteArray();
                String end = "";
                System.out.println(file.getContentType());
//                String path ="E:/kjk/project/java/productsupply/file/" + file.getOriginalFilename();
                String path ="C:/MyDocument/myJavaProject/productsupply/file/" + file.getOriginalFilename();
                File newFile = new File(path);
                File finalFile=FileUtils.createFile(newFile,0);
                filesName.add(finalFile.getName());
                OutputStream outputStream = new FileOutputStream(finalFile);
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传发生异常 -> {}", e.getMessage());
            }
        }return filesName;
    }


}
