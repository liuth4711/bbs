package com.quark.common.dao;

import com.quark.common.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao extends JpaRepository<File,Integer> {


    List<File> findByMd5(String md5);


}
