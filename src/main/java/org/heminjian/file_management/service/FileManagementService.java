/*
 * @(#)FileManagementService.java    2018年6月14日
 * 
 * Copyright (c) 2018, HeMinJian F.R.O Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou HeMinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.heminjian.file_management.service;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.heminjian.file_management.constant.FileManagementConstants;
import org.heminjian.file_management.util.DateUtils;
import org.heminjian.file_management.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ClassName: PhotoManagementService.<br>
 * Description: 文件管理服务接口实现类.<br>
 * Date: 2018年6月14日
 * @author HeMinJian
 * @version 1.0.0
 * @since JDK 1.7
 */
@Service
public class FileManagementService {

    protected static final Logger logger = LoggerFactory.getLogger(FileManagementService.class);
    
    @Value("${filemanagement.rootPath}")
    private String rootPath;
    
    /**
     * 分页查找文件信息.<br>
     * Date: 2018年6月14日
     * @author HeMinJian
     * @param folderPath 文件夹路径（相对根路径）
     * @param offset 偏移量
     * @param limit 分页大小（-1表示不分页）
     * @param sort 排序类型
     * @param order 排序方式
     * @return 文件信息
     */
    public List<Map<String, Object>> findFileInfosByPage(String folderPath, int offset, int limit, String sort, String order) {
        List<Map<String, Object>> fileInfos = new ArrayList<Map<String, Object>>();
        File currentRoot = new File(rootPath + folderPath);
        File[] files = currentRoot.listFiles();
        if (files != null) {
            if (files.length != 0) {
                /* 文件分类排序 start */
                if (order.equals("asc")) {
                    if (sort != null && !(sort.equals(""))) {
                        if (sort.equals("name")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_NAME, FileManagementConstants.ASC));
                        } else if (sort.equals("lastModified")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_LASTMODIFIED, FileManagementConstants.ASC));
                        } else if (sort.equals("type")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_TYPE, FileManagementConstants.ASC));
                        } else if (sort.equals("size")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_SIZE, FileManagementConstants.ASC));
                        } else {}
                    } else {
                        Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_NAME, FileManagementConstants.ASC));
                    }
                } else {
                    if (sort != null && !(sort.equals(""))) {
                        if (sort.equals("name")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_NAME, FileManagementConstants.DESC));
                        } else if (sort.equals("lastModified")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_LASTMODIFIED, FileManagementConstants.DESC));
                        } else if (sort.equals("type")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_TYPE, FileManagementConstants.DESC));
                        } else if (sort.equals("size")) {
                            Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_SIZE, FileManagementConstants.DESC));
                        } else {}
                    } else {
                        Arrays.sort(files, new FileUtils(FileManagementConstants.SORT_BY_NAME, FileManagementConstants.DESC));
                    }
                }
                /* 文件分类排序 end */
                NumberFormat numberFormat = NumberFormat.getInstance(); // 格式化字符串
                numberFormat.setMaximumFractionDigits(2); // 只显示小数点后两位
                long fileLength;
                for (int i = offset; i < files.length; i ++) {
                    Map<String, Object> fileInfo = new HashMap<String, Object>();
                    fileInfo.put("isFile", files[i].isFile());
                    if (folderPath.equals("")) {
                        fileInfo.put("relativePath", files[i].getName());
                    } else {
                        fileInfo.put("relativePath", folderPath.replace("\\", "/") + "/" + files[i].getName());
                    }
                    fileInfo.put("name", files[i].getName());
                    fileInfo.put("lastModified", DateUtils.dateToString(new Date(files[i].lastModified())));
                    if (files[i].isFile()) {
                        fileLength = files[i].length();
                        if (fileLength < FileManagementConstants.KB) {
                            fileInfo.put("size", fileLength + " B");
                        } else if (fileLength >= FileManagementConstants.KB && fileLength < FileManagementConstants.MB) {
                            fileInfo.put("size", numberFormat.format(fileLength / FileManagementConstants.KB) + " KB");
                        } else if (fileLength >= FileManagementConstants.MB && fileLength < FileManagementConstants.GB) {
                            fileInfo.put("size", numberFormat.format(fileLength / FileManagementConstants.MB) + " MB");
                        } else if (fileLength >= FileManagementConstants.GB && fileLength < FileManagementConstants.TB) {
                            fileInfo.put("size", numberFormat.format(fileLength / FileManagementConstants.GB) + "GB");
                        } else {
                            fileInfo.put("size", numberFormat.format(fileLength / FileManagementConstants.TB) + "TB");
                        }
                        fileInfo.put("type", files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1));
                    } else {
                        fileInfo.put("size", "-");
                        fileInfo.put("type", "文件夹");
                    }
                    fileInfos.add(fileInfo);
                    if (limit != -1) {
                        if (i == (offset + limit - 1)) {
                            break;
                        }
                    }
                }
                return fileInfos;
            } else {
                return fileInfos;
            }
        } else {
            return fileInfos;
        }
    }
    
    /**
     * Description: 删除一个或多个文件，当删除文件含有一个非空文件夹时删除失败.<br>
     * Date: 2018年6月14日
     * @author HeMinJian
     * @param relativePaths 文件相对路径集合
     * @return 成功返回“SUCCESS”，失败返回“FAILURE”
     */
    public Map<String, Object> deleteFiles(String[] relativePaths) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean directoryIsEmpty = true;
        for (int i = 0; i < relativePaths.length; i++) {
            File file = new File(rootPath + relativePaths[i]);
            if (file.isDirectory()) {
                if (file.listFiles().length != 0) {
                    directoryIsEmpty = false;
                } else {
                    file.delete();
                }
            } else {
                file.delete();
            }
        }
        if (directoryIsEmpty) {
            resultMap.put("deleteResult", FileManagementConstants.SUCCESS);
        } else {
            resultMap.put("deleteResult", FileManagementConstants.FAILURE);
            resultMap.put("directoryIsEmpty", false);
        }
        return resultMap;
    }
    
}
