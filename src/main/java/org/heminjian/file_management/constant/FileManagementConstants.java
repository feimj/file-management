/*
 * @(#)FileManagementConstants.java    2018年6月14日
 * 
 * Copyright (c) 2018, GuangZhou MinJian Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou MinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.heminjian.file_management.constant;

/**
 * ClassName: FileManagementConstants.<br>
 * Description: 文件管理常量类.<br>
 * Date: 2018年6月14日
 * @author HeMinJian
 * @version 1.0.0
 * @since JDK 1.7
 */
public class FileManagementConstants {

    /**
     * 默认的千字节，其值为 {@value} byte.
     */
    public static final int KB = 1024;
    
    /**
     * 默认的兆字节，其值为 {@value} byte.
     */
    public static final int MB = KB * 1024;
    
    /**
     * 默认的吉兆字节，其值为 {@value} byte.
     */
    public static final int GB = MB * 1024;
    
    /**
     * 默认的太字节，其值为 1024 GB.
     */
    public static final long TB = GB * 1024;
    
    /**
     * 默认的“升序”排序方式，其值为 {@value}
     */
    public static final int ASC = 1;
    
    /**
     * 默认的“倒序”排序方式，其值为 {@value}
     */
    public static final int DESC = 2;
    
    /**
     * 默认的“按名称排序”排序类型，其值为 {@value}
     */
    public static final int SORT_BY_NAME = 3;
    
    /**
     * 默认的“按最后修改时间排序”排序类型，其值为 {@value}
     */
    public static final int SORT_BY_LASTMODIFIED = 4;
    
    /**
     * 默认的“按类型排序”排序类型，其值为 {@value}
     */
    public static final int SORT_BY_TYPE = 5;
    
    /**
     * 默认的“按大小排序”排序类型，其值为 {@value}
     */
    public static final int SORT_BY_SIZE = 6;
    
    /**
     * 默认的执行成功，其值为 {@value}
     */
    public static final String SUCCESS = "SUCCESS";
    
    /**
     * 默认的执行失败，其值为 {@value}
     */
    public static final String FAILURE = "FAILURE";
    
}
