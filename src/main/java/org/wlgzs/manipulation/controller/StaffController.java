package org.wlgzs.manipulation.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.entity.Staff;
import org.wlgzs.manipulation.util.Result;
import org.wlgzs.manipulation.util.ResultCode;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-03-19
 */
@RestController
@RequestMapping("/staff")
public class StaffController extends BaseController {

    //新增医师
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ModelAndView addStaff(Staff staff,Model model){
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pinyin_code",staff.getPinyinCode());
        int count = iStaffService.count(queryWrapper);
        if(count > 0){
            model.addAttribute("msg","失败！");
            return new ModelAndView("redirect:/staff/staffList");
        }
        Result result = iStaffService.addStaff(staff);
        if(result.getCode() == 0){
            model.addAttribute("msg","成功！");
            System.out.println("成功");
        }else{
            model.addAttribute("msg","失败！");
            System.out.println("失败");
        }
        return new ModelAndView("redirect:/staff/staffList");
    }

    //查看所有医师
    @RequestMapping(value = "/staffList")
    public ModelAndView staffList(Model model){
        List<Staff> staffList = iStaffService.selectAllStaff();
        System.out.println(staffList);
        model.addAttribute("staffList",staffList);
        return new ModelAndView("staffList");
    }

    //删除医师
    @RequestMapping(value = "/delete/{staffId}",method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable("staffId")int staffId, Model model){
        Result result = iStaffService.delete(staffId);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/staff/staffList");
    }
}
