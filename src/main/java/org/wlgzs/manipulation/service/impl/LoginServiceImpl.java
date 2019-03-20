package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wlgzs.manipulation.entity.Staff;
import org.wlgzs.manipulation.mapper.StaffMapper;
import org.wlgzs.manipulation.service.LoginService;
import org.wlgzs.manipulation.util.Result;
import org.wlgzs.manipulation.util.ResultCode;

import javax.servlet.http.HttpSession;

/**
 * @author: 胡亚星
 * @createTime 2019-03-19 11:16
 * @description:
 **/
@Service
public class LoginServiceImpl extends ServiceImpl<StaffMapper, Staff> implements LoginService {


    @Override
    public Result login(HttpSession session, String userPhone, String userPassword) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("staff_phone", userPhone);
        Staff staff = baseMapper.selectOne(queryWrapper);
        if (staff != null) {
            if (staff.getStaffPassword().equals(userPassword)) {
                //登录成功
                session.setAttribute("staff", staff);
                return new Result(ResultCode.SUCCESS);
            }
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.FAIL);
    }
}
