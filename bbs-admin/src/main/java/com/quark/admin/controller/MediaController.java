package com.quark.admin.controller;

import com.quark.admin.service.FileService;
import com.quark.admin.utils.IsAllUploaded;
import com.quark.admin.utils.SaveFile;
import com.quark.common.dto.QuarkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/media")
public class MediaController {


    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/IsMD5Exist", method = RequestMethod.POST)
    public String bigFileUpload(String fileMd5, String fileName, String fileID) {

        try {
            boolean md5Exist = fileService.isMd5Exist(fileMd5);
            if (md5Exist) {
                return "this file is exist";
            } else {
                return "this file is not exist";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "this file is not exist";
        }
    }

    /**
     * @param guid             临时文件名
     * @param md5value         客户端生成md5值
     * @param chunks           分块数
     * @param chunk            分块序号
     * @param id               文件id便于区分
     * @param name             上传文件名
     * @param type             文件类型
     * @param lastModifiedDate 上次修改时间
     * @param size             文件大小
     * @param file             文件本身
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/BigFileUp")
    public QuarkResult fileUpload(String guid, String md5value, String chunks, String chunk, String id, String name, String type, String lastModifiedDate, int size, MultipartFile file) {
        String fileName;
        try {
            int index;
            File path = new File(ResourceUtils.getURL("classpath:").getPath());

            String uploadFolderPath =path.getPath()+"/media/";

            String mergePath = uploadFolderPath + guid + "/";
            String ext = name.substring(name.lastIndexOf("."));

            //判断文件是否分块
            if (chunks != null && chunk != null) {
                index = Integer.parseInt(chunk);
                fileName = String.valueOf(index) + ext;
                // 将文件分块保存到临时文件夹里，便于之后的合并文件
                SaveFile.saveFile(mergePath, fileName, file);
                // 验证所有分块是否上传成功，成功的话进行合并
                IsAllUploaded.Uploaded(md5value, guid, chunk, chunks, uploadFolderPath, fileName, ext, fileService);
            } else {
                fileName = guid + ext;
                //上传文件没有分块的话就直接保存
                SaveFile.saveFile(uploadFolderPath, fileName, file);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return QuarkResult.error("上传失败！");
        }

        return  QuarkResult.ok(fileName);
    }


}
