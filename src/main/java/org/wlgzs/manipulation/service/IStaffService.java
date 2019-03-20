package org.wlgzs.manipulation.service;

import org.wlgzs.manipulation.entity.Staff;
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
public interface IStaffService extends IService<Staff> {

    //查询所有医师信息
    List<Staff> selectAllStaff();

    //新增医师
    Result addStaff(Staff staff);

    Result Modify(Staff staff);

    Result delete(int staffId);
}
