/*
 * @(#)BaseViewController.java    2018年6月14日
 * 
 * Copyright (c) 2018, GuangZhou MinJian Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou MinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.heminjian.file_management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: BaseViewController.<br>
 * Description: 视图跳转基础类.<br>
 * Date: 2018年6月14日
 * @author HeMinJian
 * @version 1.0.0
 * @since JDK 1.7
 */
public class BaseViewController {

    /**
     * Description: SpringMVC视图跳转.<br>
     * Date: 2018年6月14日
     * @author HeMinJian
     * @param request HttpServletRequest对象
     * @param mav ModelAndView对象
     * @param model Model对象
     * @param view 视图
     * @param title 标题
     * @return ModelAndView对象
     */
    protected ModelAndView modelToView(HttpServletRequest request, 
            ModelAndView mav, Model model, String view, String title) {
        model.addAttribute("title", title);
        model.addAttribute("view", view); // js和css文件引入名称
        mav.setViewName(view); // 跳转视图名称
        mav.addObject(model);
        return mav;
    }
    
}
