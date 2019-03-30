package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wlgzs.manipulation.entity.Members;
import org.wlgzs.manipulation.entity.Record;
import org.wlgzs.manipulation.mapper.MembersMapper;
import org.wlgzs.manipulation.mapper.RecordMapper;
import org.wlgzs.manipulation.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wlgzs.manipulation.util.Result;
import org.wlgzs.manipulation.util.ResultCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public Result addRecord(Record record) {
        if (record != null) {
            //扣除该会员对应的剩余次数
            Members members = membersMapper.selectById(record.getMembersId());
            if(members.getSurplusNumber() > 0){
                members.setSurplusNumber(members.getSurplusNumber() - 1);
                membersMapper.updateById(members);
            }else{
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

    @Override
    public Result recordList(int page, String findName, String start_time, String end_time) {
        Page page1 = new Page(page, 6);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        IPage<Record> iPage = null;
        if (start_time.equals("")) {//查询所有
            queryWrapper.like("members_name", findName);
            queryWrapper.orderBy(true, false, "record_time");
            iPage = baseMapper.selectPage(page1, queryWrapper);
        } else {//按条件查询
            queryWrapper.like("members_name", findName).or().like("pinyin_code",findName).between("record_time", start_time, end_time);
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
    public IPage<Record> summary(int page, String staffName, String tuinaType, String startTime, String endTime) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        Page page1 = new Page(page, 10);
        if (tuinaType.equals("all") && startTime.equals("")) {//默认所有种类
            queryWrapper.eq("staff_name", staffName).or().likeLeft("members_phone",staffName);
            queryWrapper.orderBy(true, false, "record_time");
        } else if (tuinaType.equals("all") && !startTime.equals("")) {
            queryWrapper.eq("staff_name", staffName).or().likeLeft("members_phone",staffName).between("record_time", startTime, endTime);
            queryWrapper.orderBy(true, false, "record_time");
        } else if (startTime.equals("")) {
            queryWrapper.eq("staff_name", staffName).or().likeLeft("members_phone",staffName).eq("tuina_name", tuinaType);
            queryWrapper.orderBy(true, false, "record_time");
        } else {
            queryWrapper.eq("staff_name", staffName).or().likeLeft("members_phone",staffName).eq("tuina_name", tuinaType).between("record_time", startTime, endTime);
            queryWrapper.orderBy(true, false, "record_time");
        }
        IPage<Record> iPage = baseMapper.selectPage(page1, queryWrapper);
        return iPage;
    }


}
