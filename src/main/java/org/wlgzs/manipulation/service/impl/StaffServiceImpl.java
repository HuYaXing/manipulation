package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.wlgzs.manipulation.entity.Staff;
import org.wlgzs.manipulation.mapper.StaffMapper;
import org.wlgzs.manipulation.service.IStaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wlgzs.manipulation.util.Result;
import org.wlgzs.manipulation.util.ResultCode;

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
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Override
    public List<Staff> selectAllStaff() {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        List<Staff> staffList = baseMapper.selectList(queryWrapper);
        return staffList;
    }

    @Override
    public Result addStaff(Staff staff) {
        if(staff != null){
            baseMapper.insert(staff);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    public Result Modify(Staff staff) {
        if (staff != null) {
            baseMapper.updateById(staff);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    public Result delete(int staffId) {
        Staff staff = baseMapper.selectById(staffId);
        if (staff != null) {
            baseMapper.deleteById(staffId);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }


}
