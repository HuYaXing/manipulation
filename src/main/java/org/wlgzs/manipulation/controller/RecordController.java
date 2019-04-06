package org.wlgzs.manipulation.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.entity.Record;
import org.wlgzs.manipulation.entity.Staff;
import org.wlgzs.manipulation.entity.TuinaType;
import org.wlgzs.manipulation.util.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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
@RequestMapping("/record")
public class RecordController extends BaseController {

    //新增一条记录(治疗)
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelAndView addRecord(Model model, Record record) {
        Result result = iRecordService.addRecord(record);
        if (result.getCode() == 0) model.addAttribute("msg", "成功！");
        else model.addAttribute("msg", "失败！");
        return new ModelAndView("redirect:/record/recordList/1");
    }

    //查看所有记录(按会员搜索，前台汇总)（需要可以按拼音码搜索）
    @RequestMapping(value = "/recordList/{page}")
    public ModelAndView recordList(@PathVariable("page") int page, Model model,
                                   @RequestParam(value = "findName", defaultValue = "") String findName,
                                   @RequestParam(value = "start_time", defaultValue = "") String start_time,
                                   @RequestParam(value = "end_time", defaultValue = "") String end_time) {
        if (findName.equals("")) model.addAttribute("isSearch", 0);
        else model.addAttribute("isSearch", 1);
        if(!start_time.equals("")){
            model.addAttribute("start_time", start_time.substring(0,10));
            model.addAttribute("end_time", end_time.substring(0,10));
        }
        Result result = iRecordService.recordList(page, findName, start_time, end_time);
        IPage<Record> ipage = (IPage<Record>) result.getData();
        model.addAttribute("TotalPages", ipage.getPages());//查询的总页数
        model.addAttribute("Number", page);//查询的当前第几页
        List<Record> recordList = ipage.getRecords();
        model.addAttribute("recordList", recordList);
        model.addAttribute("size", recordList.size());
        model.addAttribute("findName", findName);
        System.out.println("recordList" + recordList);
        return new ModelAndView("membersRecord");
    }

    //删除记录
    @RequestMapping(value = "/delete/{recordId}", method = RequestMethod.DELETE)
    public ModelAndView delete(Model model, @PathVariable("recordId") int recordId) {
        Result result = iRecordService.delete(recordId);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/record/recordList/1");
    }

    //去修改记录
    @RequestMapping(value = "/toModify/{recordId}")
    public ModelAndView toModify(Model model, @PathVariable("recordId") int recordId) {
        Record record = iRecordService.getById(recordId);
        model.addAttribute("record", record);
        if (record == null) {
            model.addAttribute("msg", "该记录不存在！");
        }
        return new ModelAndView("Modify");
    }

    //修改记录
    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ModelAndView Modify(Record record, Model model) {
        Result result = iRecordService.Modify(record);
        model.addAttribute("msg", result.getMsg());
        return new ModelAndView("redirect:/record/recordList/1");
    }

    //按员工，时间，推拿种类，并返回总数量(必须要员工名字)
    @RequestMapping(value = "/summary/{page}")
    public ModelAndView summary(@PathVariable("page") int page, Model model,
                                String staffName, @RequestParam(value = "start_time", defaultValue = "") String start_time,
                                @RequestParam(value = "end_time", defaultValue = "") String end_time) {
        System.out.println("staffName1"+staffName);
        if (!"".equals(start_time) && !"".equals(end_time)) {
            start_time = start_time + " 00:00:00";
            end_time = end_time + " 23:59:59";
        }

        //查询所有员工
        List<Staff> staffList = iStaffService.selectAllStaff();
        staffName = (staffName == null || staffName.equals("")) ? staffList.get(0).getStaffName() : staffName;
        HashMap<String, Integer> hashMap = iRecordService.staffWorkload(staffName, start_time, end_time, model);
        //返回医师的信息
        QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
        staffQueryWrapper.eq("staff_name",staffName);
        Staff staff = iStaffService.list(staffQueryWrapper).get(0);
        model.addAttribute("staff", staff);
        model.addAttribute("hashMap", hashMap);
        QueryWrapper<TuinaType> queryWrapper = new QueryWrapper<TuinaType>();
        List<TuinaType> tuinaTypeList = iTuinaTypeService.list(queryWrapper);
        model.addAttribute("tuinaTypeList", tuinaTypeList);
        IPage<Record> iPage = iRecordService.summary(page, staffName, start_time, end_time);
        List<Record> recordList = iPage.getRecords();
        model.addAttribute("recordList", recordList);
        if (recordList.size() <= 0) {
            model.addAttribute("msg", "没有数据！");
        }
        model.addAttribute("size", recordList.size());
        model.addAttribute("TotalPages", iPage.getPages());//查询的总页数
        model.addAttribute("Number", page);//查询的当前第几页
        model.addAttribute("isStaff", 1);
        model.addAttribute("staffName", staffName);
        model.addAttribute("staffList", staffList);
        model.addAttribute("start_time", start_time.substring(0,10));
        model.addAttribute("end_time", end_time.substring(0,10));
        return new ModelAndView("staffWorkload");
    }

    //查询某个医师的每月工作量
    @RequestMapping(value = "/monthWork/{page}")
    public ModelAndView monthWork(String staffName,@PathVariable("page") int page,Model model){
        System.out.println("staffName1-----"+staffName);

        //查询所有员工
        List<Staff> staffList = iStaffService.selectAllStaff();
        staffName = (staffName == null || staffName.equals("")) ? staffList.get(0).getStaffName() : staffName;

        //获取本月时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String start_time = format.format(c.getTime());

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String end_time = format.format(ca.getTime());

        HashMap<String, Integer> hashMap = iRecordService.staffWorkload(staffName, start_time, end_time, model);
        System.out.println("hashMap"+hashMap);
        //返回医师的信息
        QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
        staffQueryWrapper.eq("staff_name",staffName);
        Staff staff = iStaffService.list(staffQueryWrapper).get(0);
        model.addAttribute("staff", staff);
        model.addAttribute("hashMap", hashMap);
        QueryWrapper<TuinaType> queryWrapper = new QueryWrapper<TuinaType>();
        List<TuinaType> tuinaTypeList = iTuinaTypeService.list(queryWrapper);
        model.addAttribute("tuinaTypeList", tuinaTypeList);
        IPage<Record> iPage = iRecordService.summary(page, staffName, start_time, end_time);
        List<Record> recordList = iPage.getRecords();
        model.addAttribute("recordList", recordList);
        if (recordList.size() <= 0) {
            model.addAttribute("msg", "没有数据！");
        }
        model.addAttribute("size", recordList.size());
        model.addAttribute("TotalPages", iPage.getPages());//查询的总页数
        model.addAttribute("Number", page);//查询的当前第几页
        model.addAttribute("isStaff", 1);
        model.addAttribute("staffName", staffName);
        model.addAttribute("staffList", staffList);
        model.addAttribute("start_time", start_time.substring(0,10));
        model.addAttribute("end_time", end_time.substring(0,10));
        return new ModelAndView("monthWork");
    }

}
