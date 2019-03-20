package org.wlgzs.manipulation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.entity.Members;
import org.wlgzs.manipulation.entity.Storage;
import org.wlgzs.manipulation.util.Result;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-03-19
 */
@RestController
@RequestMapping("/members")
public class MembersController extends BaseController {

    //添加一个新会员
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelAndView addMembers(Members members, Model model) {
        Result result = iMembersService.addMembers(members);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("index");
    }

    //判断是否有这个用户
    @RequestMapping(value = "/checkName")
    public Result checkName(String membersName) {
        return iMembersService.checkName(membersName);
    }

    //判断是否有这个手机号
    @RequestMapping(value = "/checkPhone")
    public Result checkPhone(String membersPhone) {
        return iMembersService.checkPhone(membersPhone);
    }

    //删除用户
    @RequestMapping(value = "/delete/{membersId}", method = RequestMethod.DELETE)
    public ModelAndView delete(Model model, @PathVariable("membersId") int membersId) {
        Result result = iMembersService.delete(membersId);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/members/membersList/1");
    }

    //查看会员详情
    @RequestMapping(value = "/details/{membersId}")
    public ModelAndView details(Model model, @PathVariable("membersId") int membersId) {
        Members members = iMembersService.details(membersId);
        model.addAttribute("members", members);
        if (members != null) {
            //查询用户是不是有余额
            List<Storage> storageList = iStorageService.selectStorage(membersId);
            model.addAttribute("storageList", storageList);
            return new ModelAndView("membersDetails");
        }
        model.addAttribute("msg", "不存在！");
        return new ModelAndView("redirect:/members/membersList/1");
    }

    //修改用户信息
    @RequestMapping(value = "Modify",method = RequestMethod.PUT)
    public ModelAndView Modify(Members members, Model model){
        Result result = iMembersService.Modify(members);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/members/details/"+members.getMembersId());
    }

    //搜索会员
    @RequestMapping(value = "/membersList/{page}")
    public ModelAndView membersList(Model model, @PathVariable("page") int page,
                                    @RequestParam(value = "findName", defaultValue = "") String findName) {
        Result result = iMembersService.membersList(page, findName);
        if (findName.equals("")) {
            IPage<Members> iPage = (IPage<Members>) result.getData();
            model.addAttribute("isSearch", 0);
            model.addAttribute("membersList", iPage.getRecords());
            model.addAttribute("TotalPages", iPage.getPages());//总页数
            model.addAttribute("Number", iPage.getCurrent());//当前页数
            return new ModelAndView("membersList");
        }
        List<Members> membersList = (List<Members>) result.getData();
        model.addAttribute("isSearch", 1);
        model.addAttribute("membersList", membersList);
        return new ModelAndView("membersList");
    }

}
