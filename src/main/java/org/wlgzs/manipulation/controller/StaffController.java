package org.wlgzs.manipulation.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.entity.Staff;
import org.wlgzs.manipulation.util.Result;

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
        Result result = iStaffService.addStaff(staff);
        if(result.getCode() == 0){
            model.addAttribute("msg","成功！");
        }else{
            model.addAttribute("msg","成功！");
        }
        return new ModelAndView("redirect:/staff/staffList");
    }

    //查看所有医师
    @RequestMapping(value = "/staffList")
    public ModelAndView staffList(Model model){
        List<Staff> staffList = iStaffService.selectAllStaff();
        model.addAttribute("staffList",staffList);
        return new ModelAndView("staffList");
    }

    //修改医师
    @RequestMapping(value = "Modify",method = RequestMethod.PUT)
    public ModelAndView Modify(Staff staff, Model model){
        Result result = iStaffService.Modify(staff);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/staff/staffList");
    }

    //删除医师
    @RequestMapping(value = "delete/{staffId}",method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable("staffId")int staffId, Model model){
        Result result = iStaffService.delete(staffId);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/staff/staffList");
    }
}
