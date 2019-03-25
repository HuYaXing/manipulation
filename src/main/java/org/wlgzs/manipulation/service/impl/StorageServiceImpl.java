package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.wlgzs.manipulation.entity.Storage;
import org.wlgzs.manipulation.mapper.StorageMapper;
import org.wlgzs.manipulation.service.IStorageService;
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
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

    @Override
    public List<Storage> selectStorage(int membersId) {
        QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("members_id", membersId);
        List<Storage> storageList = baseMapper.selectList(queryWrapper);
        return storageList;
    }

    @Override
    public Result addStorage(Storage storage) {
        if (storage != null) {
            //先判断用户是否存在这种种类的
            QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tuina_name", storage.getTuinaName()).eq("members_id", storage.getMembersId());
            Storage storage1 = baseMapper.selectOne(queryWrapper);
            if (storage1 != null) {//已经存在（直接相加）
                storage1.setSurplusNumber(storage.getSurplusNumber() + storage1.getSurplusNumber());
                baseMapper.updateById(storage1);
                return new Result(ResultCode.SUCCESS, "添加成功！");
            }
            //不存在
            baseMapper.insert(storage);
            return new Result(ResultCode.SUCCESS, "添加成功！");
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    public Result deleteStorage(int storageId) {
        Storage storage = baseMapper.selectById(storageId);
        if(storage != null){
            baseMapper.deleteById(storage);
            return new Result(ResultCode.SUCCESS,storage);
        }
        return new Result(ResultCode.FAIL,storage);
    }

    @Override
    public Storage selectStorage(int membersId, String tuinaType) {
        QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("members_id",membersId).eq("tuina_name",tuinaType);
        Storage storage = baseMapper.selectOne(queryWrapper);
        return storage;
    }


}
