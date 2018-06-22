/*
 * @(#)FileUtils.java    2018年6月14日
 * 
 * Copyright (c) 2018, GuangZhou HeMinJian Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou HeMinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.heminjian.file_management.util;

import java.io.File;
import java.util.Comparator;

import org.heminjian.file_management.constant.FileManagementConstants;

/**
 * ClassName: FileUtils.<br>
 * Description: 文件操作工具类.<br>
 * Date: 2018年6月14日
 * @author HeMinJian
 * @version 1.0.0
 * @since JDK 1.7
 */
public class FileUtils implements Comparator<File> {
    
    private int sort; // 排序类型
    private int order; // 排序方式
    
    /**
     * 自定义构造器
     * Date: 2018年6月14日
     * @author HeMinJian
     * @param sort 排序类型
     * @param order 排序方式
     */
    public FileUtils (int sort, int order) {
        this.sort = sort;
        this.order = order;
    }

    @Override
    public int compare(File file1, File file2) {
        long difference;
        switch (sort) {
        case FileManagementConstants.SORT_BY_NAME:
            difference = file1.getName().compareTo(file2.getName());
            if (order == FileManagementConstants.ASC) {
                if (difference > 0) {
                    return 1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return -1;
                } 
            } else {
                if (difference > 0) {
                    return -1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        case FileManagementConstants.SORT_BY_LASTMODIFIED:
            difference = file1.lastModified() - file2.lastModified();
            if (order == FileManagementConstants.ASC) {
                if (difference > 0) {
                    return 1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (difference > 0) {
                    return -1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        case FileManagementConstants.SORT_BY_TYPE:
            String mimeType1 = file1.getName().substring(file1.getName().lastIndexOf(".") + 1);
            String mimeType2 = file2.getName().substring(file2.getName().lastIndexOf(".") + 1);
            difference = mimeType1.compareTo(mimeType2);
            if (order == FileManagementConstants.ASC) {
                if (difference > 0) {
                    return 1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (difference > 0) {
                    return -1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        case FileManagementConstants.SORT_BY_SIZE:
            difference = file1.length() - file2.length();
            if (order == FileManagementConstants.ASC) {
                if (difference > 0) {
                    return 1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (difference > 0) {
                    return -1;
                } else if (difference == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        default:
            return 0;
        }
    }
    
    @Override
    public boolean equals(Object object) {
        if (this.equals(object)) {
            return true;
        } else {
            return false;
        }
    }
    
}
