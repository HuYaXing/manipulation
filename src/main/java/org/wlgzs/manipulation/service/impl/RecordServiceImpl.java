package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.wlgzs.manipulation.entity.Members;
import org.wlgzs.manipulation.entity.Record;
import org.wlgzs.manipulation.entity.Staff;
import org.wlgzs.manipulation.entity.TuinaType;
import org.wlgzs.manipulation.mapper.MembersMapper;
import org.wlgzs.manipulation.mapper.RecordMapper;
import org.wlgzs.manipulation.mapper.StaffMapper;
import org.wlgzs.manipulation.mapper.TuinaTypeMapper;
import org.wlgzs.manipulation.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wlgzs.manipulation.util.Result;
import org.wlgzs.manipulation.util.ResultCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-03-19
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Autowired
    protected MembersMapper membersMapper;

    @Autowired
    protected StaffMapper staffMapper;

    @Autowired
    protected TuinaTypeMapper tuinaTypeMapper;

    @Override
    public Result addRecord(Record record) {
        if (record != null) {
            //扣除该会员对应的剩余次数
            Members members = membersMapper.selectById(record.getMembersId());
            if (members.getSurplusNumber() > 0) {
                members.setSurplusNumber(members.getSurplusNumber() - 1);
                membersMapper.updateById(members);
            } else {
                return new Result(ResultCode.FAIL);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.now();
            String localTime = formatter.format(time);
            LocalDateTime ldt = LocalDateTime.parse(localTime, formatter);
            record.setRecordTime(ldt);
            //生成记录
            baseMapper.insert(record);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    //用户拼音码
    @Override
    public Result recordList(int page, String findName, String start_time, String end_time) {
        //查询用户按拼音码
        QueryWrapper<Members> membersQueryWrapper = new QueryWrapper<>();
        membersQueryWrapper.eq("pinyin_code", findName);
        Members members = membersMapper.selectOne(membersQueryWrapper);
        Page page1 = new Page(page, 6);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        IPage<Record> iPage = null;
        if (members != null) {
            if (start_time.equals("")) {//查询所有
                queryWrapper.like("members_name", members.getMembersName());
                queryWrapper.orderBy(true, false, "record_time");
                iPage = baseMapper.selectPage(page1, queryWrapper);
            } else {//按条件查询
                queryWrapper.like("members_name", members.getMembersName()).between("record_time", start_time, end_time);
                queryWrapper.orderBy(true, false, "record_time");
                iPage = baseMapper.selectPage(page1, queryWrapper);
            }
            return new Result(ResultCode.SUCCESS, iPage);
        }
        if (start_time.equals("")) {//查询所有
            queryWrapper.like("members_name", findName);
            queryWrapper.orderBy(true, false, "record_time");
            iPage = baseMapper.selectPage(page1, queryWrapper);
        } else {//按条件查询
            queryWrapper.like("members_name", findName).between("record_time", start_time, end_time);
            queryWrapper.orderBy(true, false, "record_time");
            iPage = baseMapper.selectPage(page1, queryWrapper);
        }
        return new Result(ResultCode.SUCCESS, iPage);
    }

    @Override
    public Result delete(int recordId) {
        Record record = baseMapper.selectById(recordId);
        if (record != null) {
            baseMapper.deleteById(record);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    public Result Modify(Record record) {
        if (record != null) {
            baseMapper.updateById(record);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    public IPage<Record> summary(int page, String staffName, String startTime, String endTime) {

        System.out.println("staffName"+staffName);
        //查询用户按拼音码
        QueryWrapper<Staff> membersQueryWrapper = new QueryWrapper<>();
        membersQueryWrapper.eq("pinyin_code", staffName);
        Staff staff = staffMapper.selectOne(membersQueryWrapper);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        Page page1 = new Page(page, 6);
        if (staff != null) {//拼音码有医师（按照医师信息查询）
            if (startTime.equals("")) {//没有设置时间，只要名字查询
                queryWrapper.eq("staff_name", staff.getStaffName());
                queryWrapper.orderBy(true, false, "record_time");
            } else {
                queryWrapper.eq("staff_name", staff.getStaffName()).between("record_time", startTime, endTime);
                queryWrapper.orderBy(true, false, "record_time");
            }
            IPage<Record> iPage = baseMapper.selectPage(page1, queryWrapper);
            return iPage;
        }

        //按staff_name查询
        if (startTime.equals("")) {//没有设置时间，只要名字查询
            queryWrapper.eq("staff_name", staffName);
            queryWrapper.orderBy(true, false, "record_time");
        } else {
            queryWrapper.eq("staff_name", staffName).between("record_time", startTime, endTime);
            queryWrapper.orderBy(true, false, "record_time");
        }
        IPage<Record> iPage = baseMapper.selectPage(page1, queryWrapper);
        return iPage;
    }

    @Override
    public HashMap<String, Integer> staffWorkload(String staffName, String startTime, String endTime, Model model) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        //存放结果信息
        HashMap<String, Integer> hashMap = new HashMap();

        //查询所有类型
        QueryWrapper<TuinaType> tuinaTypeQueryWrapper = new QueryWrapper<>();
        List<TuinaType> tuinaTypeList = tuinaTypeMapper.selectList(tuinaTypeQueryWrapper);
        model.addAttribute("tuinaTypeList", tuinaTypeList);
        if (!startTime.equals("") && !endTime.equals("")) {//按照时间查询
            for (TuinaType tn : tuinaTypeList) {
                queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("staff_name", staffName).eq("tuina_type", tn.getTuinaName()).between("record_time", startTime, endTime);
                int map = baseMapper.selectCount(queryWrapper);
                hashMap.put(tn.getTuinaName(), map);
            }
        } else {
            for (TuinaType tn : tuinaTypeList) {
                queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("staff_name", staffName).eq("tuina_type", tn.getTuinaName());
                int map = baseMapper.selectCount(queryWrapper);
                hashMap.put(tn.getTuinaName(), map);
            }
        }
        model.addAttribute("hashMap", hashMap);
        return hashMap;
    }


}
