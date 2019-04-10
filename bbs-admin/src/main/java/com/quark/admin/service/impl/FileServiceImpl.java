package com.quark.admin.service.impl;

import com.quark.admin.service.FileService;
import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.AdminUserDao;
import com.quark.common.dao.FileDao;
import com.quark.common.entity.AdminUser;
import com.quark.common.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileServiceImpl extends BaseServiceImpl<FileDao, File> implements FileService {



    @Override
    public Boolean isMd5Exist(String md5) {

        List<File> fileList=repository.findByMD5(md5);

        return  ! fileList.isEmpty();
    }
}
