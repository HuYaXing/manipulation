package org.wlgzs.manipulation.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.entity.Members;
import org.wlgzs.manipulation.entity.Staff;
import org.wlgzs.manipulation.entity.TuinaType;
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

    //去添加会员
    @RequestMapping("/toAddMembers")
    public ModelAndView toAddMembers(Model model){
        //查询所有类型
        QueryWrapper<TuinaType> queryWrapper = new QueryWrapper<TuinaType>();
        List<TuinaType> tuinaTypeList = iTuinaTypeService.list(queryWrapper);
        model.addAttribute("tuinaTypeList", tuinaTypeList);
        return new ModelAndView("addMembers");
    }

    //为会员添加剩余次数
    @RequestMapping("/addNumber/{membersId}")
    public Result addNumber(int Number,@PathVariable("membersId")int membersId){
        return iMembersService.addNumber(Number,membersId);
    }

    //添加一个新会员
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result addMembers(Members members) {
        return iMembersService.addMembers(members);
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
            //需要所有的医师信息
            List<Staff> staffList = iStaffService.selectAllStaff();
            model.addAttribute("staffList",staffList);
            return new ModelAndView("MemberMassage");
        }
        model.addAttribute("msg", "不存在！");
        return new ModelAndView("redirect:/members/membersList/1");
    }

    //修改用户信息
    @RequestMapping(value = "/Modify",method = RequestMethod.PUT)
    public ModelAndView Modify(Members members, Model model){
        Result result = iMembersService.Modify(members);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/members/details/"+members.getMembersId());
    }

    //搜索会员
    @RequestMapping(value = "/MemberMassage/{page}")
    public ModelAndView membersList(Model model, @PathVariable("page") int page,
                                    @RequestParam(value = "findName", defaultValue = "") String findName) {
        if(findName.equals(""))model.addAttribute("isSearch",0);
        else model.addAttribute("isSearch",1);
        Result result = iMembersService.membersList(page, findName);
        //需要所有的医师信息
        List<Staff> staffList = iStaffService.selectAllStaff();
        model.addAttribute("staffList",staffList);
        if (findName.equals("")) {
            IPage<Members> iPage = (IPage<Members>) result.getData();
            model.addAttribute("membersList", iPage.getRecords());
            model.addAttribute("TotalPages", iPage.getPages());//总页数
            model.addAttribute("Number", iPage.getCurrent());//当前页数
            return new ModelAndView("MemberMassage");
        }
        List<Members> membersList = (List<Members>) result.getData();
        model.addAttribute("findName", findName);
        model.addAttribute("membersList", membersList);
        return new ModelAndView("MemberMassage");
    }

    //搜索会员(删除。。等)
    @RequestMapping(value = "/membersList/{page}")
    public ModelAndView membersList1(Model model, @PathVariable("page") int page,
                                    @RequestParam(value = "findName", defaultValue = "") String findName) {
        if(findName.equals(""))model.addAttribute("isSearch",0);
        else model.addAttribute("isSearch",1);
        Result result = iMembersService.membersList(page, findName);
        if (findName.equals("")) {
            IPage<Members> iPage = (IPage<Members>) result.getData();
            model.addAttribute("membersList", iPage.getRecords());
            model.addAttribute("TotalPages", iPage.getPages());//总页数
            model.addAttribute("Number", iPage.getCurrent());//当前页数
            return new ModelAndView("membersList");
        }
        List<Members> membersList = (List<Members>) result.getData();
        model.addAttribute("findName", findName);
        model.addAttribute("membersList", membersList);
        return new ModelAndView("membersList");
    }

}
