package com.example.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSUtil {

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient storageClient = null;


    public static String[] update(byte[] bytes,String fileEx) {
        try {
//            读取配置
            ClientGlobal.init("fdfs_client.conf");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
//            用这个对象完成上传下载删除操作
            storageClient = new StorageClient(trackerServer, storageServer);
            /**
             * 参数1 为需要上传的文件的绝对路径
             * 参数2 为需要的上传的文件的拓展名
             * 参数3 文件的属性 通常不上传
             */
            return storageClient.upload_file(bytes, fileEx, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            try {
                storageServer.close();
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static byte[] download(String groupName,String remoteFilename) {
        try {
//            读取配置
            ClientGlobal.init("fdfs_client.conf");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
//            用这个对象完成上传下载删除操作
            storageClient = new StorageClient(trackerServer, storageServer);

            /**
             *  文件下载
             *  返回值 0 表示文件下载成功
             * 参数1 需要文件组名
             * 参数2 需要下载的远程文件名
             * 参数3 需要保存的本地文件名
             *
             */
           return storageClient.download_file(groupName, remoteFilename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            try {
                storageServer.close();
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static void delete(String groupName,String remoteFilename) {
        try {
//            读取配置
            ClientGlobal.init("fdfs_client.conf");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
//            用这个对象完成上传下载删除操作
            storageClient = new StorageClient(trackerServer, storageServer);

            /**
             *  文件删除
             *  返回值 0 表示文件下载成功
             * 参数1 需要文件组名
             * 参数2 需要下载的远程文件名
             *
             */
            storageClient.delete_file(groupName, remoteFilename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            try {
                storageServer.close();
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
