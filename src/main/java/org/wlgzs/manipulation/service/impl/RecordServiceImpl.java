package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.wlgzs.manipulation.entity.Record;
import org.wlgzs.manipulation.entity.Storage;
import org.wlgzs.manipulation.mapper.RecordMapper;
import org.wlgzs.manipulation.mapper.StorageMapper;
import org.wlgzs.manipulation.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wlgzs.manipulation.service.IStorageService;
import org.wlgzs.manipulation.util.Result;
import org.wlgzs.manipulation.util.ResultCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    protected IStorageService iStorageService;

    @Autowired
    protected StorageMapper storageMapper;

    @Override
    public Result addRecord(Record record) {
        if (record != null) {
            //查询会员是否有余额
            Storage storage = iStorageService.selectStorage(record.getMembersId(), record.getTuinaType());
            if (storage != null) {
                //减去剩余次数
                storage.setSurplusNumber(storage.getSurplusNumber() - 1);
                storageMapper.updateById(storage);
                if (storage.getSurplusNumber() <= 0) {
                    storageMapper.deleteById(storage);
                }
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
        Page page1 = new Page(page, 10);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        IPage<Record> iPage = null;
        if (start_time.equals("")) {//查询所有
            queryWrapper.like("members_name", findName);
            iPage = baseMapper.selectPage(page1, queryWrapper);
        } else {//按条件查询
            queryWrapper.like("members_name", findName).between("record_time", start_time, end_time);
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
            queryWrapper.eq("staff_name", staffName);
        } else if (tuinaType.equals("all") && !startTime.equals("")) {
            queryWrapper.eq("staff_name", staffName).between("record_time", startTime, endTime);
        } else if (startTime.equals("")) {
            queryWrapper.eq("staff_name", staffName).eq("tuina_name", tuinaType);
        } else {
            queryWrapper.eq("staff_name", staffName).eq("tuina_name", tuinaType).between("record_time", startTime, endTime);
        }
        IPage<Record> iPage = baseMapper.selectPage(page1, queryWrapper);
        return iPage;
    }


}
