package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wlgzs.manipulation.entity.Members;
import org.wlgzs.manipulation.mapper.MembersMapper;
import org.wlgzs.manipulation.service.IMembersService;
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
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements IMembersService {

    @Override
    public Result addMembers(Members members) {
        if (members != null) {
            baseMapper.insert(members);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    public Result checkName(String membersName) {
        QueryWrapper<Members> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("members_name", membersName);
        Members members = baseMapper.selectOne(queryWrapper);
        if (members != null) {
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result checkPhone(String membersPhone) {
        QueryWrapper<Members> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("members_phone", membersPhone);
        Members members = baseMapper.selectOne(queryWrapper);
        if (members != null) {
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result membersList(int page, String findName) {
        if(findName.equals("")){//查询所有
            Page page1 = new Page(page,10);
            QueryWrapper<Members> queryWrapper = new QueryWrapper<>();
            IPage<Members> iPage = baseMapper.selectPage(page1,queryWrapper);
            return new Result(ResultCode.SUCCESS,iPage.getRecords());
        }//按条件查询
        QueryWrapper<Members> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("members_phone","%%"+findName).like("pinyin_code",findName);
        List<Members> membersList = baseMapper.selectList(queryWrapper);
        return new Result(ResultCode.SUCCESS,membersList);
    }

    @Override
    public Result delete(int membersId) {
        Members members = baseMapper.selectById(membersId);
        if(members != null){
            baseMapper.deleteById(members);
            return new Result(ResultCode.SUCCESS,"删除成功!");
        }
        return new Result(ResultCode.FAIL,"不存在!");
    }

    @Override
    public Members details(int membersId) {
        Members members = baseMapper.selectById(membersId);
        return members;
    }

    @Override
    public Result Modify(Members members) {
        if (members != null) {
            baseMapper.updateById(members);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }
}
