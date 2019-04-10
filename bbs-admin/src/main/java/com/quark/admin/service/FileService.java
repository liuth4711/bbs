package com.quark.admin.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.File;

import java.util.List;

public interface FileService  extends BaseService<File> {

     Boolean isMd5Exist(String md5);
}
