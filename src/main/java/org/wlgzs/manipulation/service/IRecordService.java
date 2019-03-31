package org.wlgzs.manipulation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.ui.Model;
import org.wlgzs.manipulation.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wlgzs.manipulation.util.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-03-19
 */
public interface IRecordService extends IService<Record> {

    //新增记录
    Result addRecord(Record record);

    //按用户搜索记录（汇总功能）
    Result recordList(int page,String findName,String start_time,String end_time);

    //删除记录
    Result delete(int recordId);

    //修改记录
    Result Modify(Record record);

    //按员工，时间，推拿种类，并返回总数量
    IPage<Record> summary(int page,String staffName, String tuinaType, String startTime, String endTime);

    //按时间段查询所有医师的治疗记录次数
    void staffWorkload(int staffId,String startTime,String endTime,Model model);

}
