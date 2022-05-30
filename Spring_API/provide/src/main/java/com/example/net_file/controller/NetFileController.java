package com.example.net_file.controller;

import com.alibaba.fastjson.JSON;
import com.example.net_file.entity.NetFile;
import com.example.net_file.service.INetFileService;
import com.example.util.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shyheng
 * @since 2022-05-26
 */
@RestController
public class NetFileController {

    @Autowired
    private INetFileService netFileService;

    @PostMapping("/file")
    public String file(MultipartFile file,Integer token) {
        if (file == null){
            return "文件未上传";
        }else {
            String fileName = file.getOriginalFilename();
            String fileEx = fileName.substring(fileName.lastIndexOf(".")+1);
            String[] update = new String[0];
            try {
                update = FastDFSUtil.update(file.getBytes(), fileEx);
            } catch (IOException e) {
                e.printStackTrace();
            }
            NetFile netFile = new NetFile();
            netFile.setFileName(file.getOriginalFilename());
            netFile.setImgGroup(update[0]);
            netFile.setImgRemtoe(update[1]);
            netFile.setUid(token);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            netFile.setDate(simpleDateFormat.format(new Date()));
            netFile.setSize(String.valueOf(file.getSize()));
            netFile.setType(fileEx);
            netFileService.save(netFile);
            return "添加成功";
        }
    }

    @GetMapping("/sel")
    public String sel(Integer id){
        Map<String,Object> map = new HashMap();
        map.put("uid",id);
        return JSON.toJSONString(netFileService.listByMap(map));
    }

    @PostMapping("/del")
    public String del(@RequestBody NetFile netFile){
        List<NetFile> list = netFileService.list();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(netFile.getId())){
                FastDFSUtil.delete(list.get(i).getImgGroup(),list.get(i).getImgRemtoe());
                netFileService.removeById(netFile);
                return "删除成功";
            }
        }
        return "删除失败";
    }

    @RequestMapping("/dow")
    public ResponseEntity<byte[]> dow(Integer id){
        Map map = new HashMap();
        map.put("id",id);
        List<NetFile> list = netFileService.listByMap(map);
        NetFile fast = list.get(0);
        byte[] download = FastDFSUtil.download(fast.getImgGroup(), fast.getImgRemtoe());

//        响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);//设置响应头类型
        httpHeaders.setContentLength(Long.valueOf(fast.getSize()));//自动提供下载速度
//        下载时的文件名
        httpHeaders.setContentDispositionFormData("attachment",fast.getFileName());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(download,httpHeaders, HttpStatus.OK);

        return responseEntity;
    }
}
