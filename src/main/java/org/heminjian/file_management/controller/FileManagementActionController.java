/*
 * @(#)FileManagementActionController.java    2018年6月14日
 * 
 * Copyright (c) 2018, GuangZhou MinJian Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou MinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.heminjian.file_management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.heminjian.file_management.constant.FileManagementConstants;
import org.heminjian.file_management.service.FileManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: FileManagementActionController.<br>
 * Description: 文件管理异步数据处理类.<br>
 * Date: 2018年6月14日
 * @author HeMinJian
 * @version 1.0.0
 * @since JDK 1.7
 */
@Controller
public class FileManagementActionController {
    
    private static final Logger logger = LoggerFactory.getLogger(FileManagementActionController.class);
    
    @Autowired
    protected FileManagementService fileManagementService;

    /**
     * Description: 分页查找文件信息.<br>
     * Date: 2018年6月19日
     * @author HeMinJian
     * @param folderPath 文件夹相对路径
     * @param offset 偏移量
     * @param limit 分页大小（-1表示不分页）
     * @param sort 排序类型
     * @param order 排序方式
     * @return 成功返回“SUCCESS”和文件信息；失败返回“FAILURE”
     */
    @RequestMapping(value = "/findFileInfosByPage", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> findFileInfosByPage(@RequestParam String folderPath, @RequestParam int offset, 
            @RequestParam int limit, @RequestParam String sort, @RequestParam String order) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String findResult;
        try {
            List<Map<String, Object>> fileInfos = fileManagementService.findFileInfosByPage(folderPath, offset, limit, sort, order);
            resultMap.put("fileInfos", fileInfos);
            findResult = FileManagementConstants.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
            findResult = FileManagementConstants.FAILURE;
        }
        resultMap.put("findResult", findResult);
        return resultMap;
    }
    
    /**
     * Description: 删除一个或多个文件.<br>
     * Date: 2018年6月19日
     * @author HeMinJian
     * @param relativePaths 文件相对路径集合
     * @return 成功返回“SUCCESS”，失败返回“FAILURE”
     */
    @RequestMapping(value = "/deleteFiles", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> deleteFiles(@RequestParam(value = "relativePaths[]") String[] relativePaths) {
        Map<String, Object> resultMap;
        try {
            resultMap = fileManagementService.deleteFiles(relativePaths);
        } catch (Exception e) {
            // TODO: handle exception
            resultMap = new HashMap<String, Object>();
            resultMap.put("deleteResult", FileManagementConstants.FAILURE);
        }
        return resultMap;
    }
    
}
