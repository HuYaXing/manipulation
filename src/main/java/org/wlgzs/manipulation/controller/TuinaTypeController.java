package org.wlgzs.manipulation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.entity.TuinaType;

import java.util.List;

/**
 * @author: 胡亚星
 * @createTime 2019-03-21 17:41
 * @description:
 **/
@RestController
@RequestMapping("/tuinaType")
public class TuinaTypeController extends BaseController {

    //查询所有类型
    @RequestMapping(value = "/selectType")
    public ModelAndView selectType(Model model) {
        QueryWrapper<TuinaType> queryWrapper = new QueryWrapper<TuinaType>();
        List<TuinaType> tuinaTypeList = iTuinaTypeService.list(queryWrapper);
        model.addAttribute("tuinaTypeList", tuinaTypeList);
        return new ModelAndView("tuinaTypeList");
    }

    //新增一个类型
    @RequestMapping("")
    public ModelAndView addType(TuinaType tuinaType, Model model) {
        if (tuinaType != null) {
            iTuinaTypeService.save(tuinaType);
            model.addAttribute("msg", "添加成功！");
        }else {
            model.addAttribute("msg", "添加失败！");
        }
        return new ModelAndView("redirect:/tuinaType/selectType");
    }

    //删除一个类型
    @RequestMapping("/delete/{tuinaId}")
    public ModelAndView delete(@PathVariable("tuinaId") int tuinaId, Model model) {
        TuinaType tuinaType = iTuinaTypeService.getById(tuinaId);
        if (tuinaType != null) {
            iTuinaTypeService.removeById(tuinaType);
            model.addAttribute("msg", "删除成功！");
        }else{
            model.addAttribute("msg", "删除失败！");
        }
        return new ModelAndView("redirect:/tuinaType/selectType");
    }
}
