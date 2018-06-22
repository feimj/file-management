/*
 * @(#)FileManagementViewController.java    2018年6月14日
 * 
 * Copyright (c) 2018, GuangZhou MinJian Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou MinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.heminjian.file_management.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.heminjian.file_management.service.FileManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: FileManagementViewController.<br>
 * Description: 文件管理视图跳转类.<br>
 * Date: 2018年6月14日
 * @author HeMinJian
 * @version 1.0.0
 * @since JDK 1.7
 */
@Controller
public class FileManagementViewController extends BaseViewController {

    private static final Logger logger = LoggerFactory.getLogger(FileManagementViewController.class);
    
    @Autowired
    protected FileManagementService fileManagementService;
    
    /**
     * Description: 进入文件管理首页.<br>
     * Date: 2018年6月19日
     * @author HeMinJian
     * @param request HttpServletRequest对象
     * @param mav ModelAndView对象
     * @param model Model对象
     * @return ModelAndView对象
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView toFileManagement(HttpServletRequest request, ModelAndView mav, Model model) {
        try {
            List<Map<String, Object>> fileInfos = fileManagementService.findFileInfosByPage("", 0, -1, "name", "asc");
            model.addAttribute("fileInfos", fileInfos);
            return modelToView(request, mav, model, "filemanagement", "文件管理");
        } catch (Exception e) {
            e.printStackTrace();
            return mav;
        }
    }
    
}
