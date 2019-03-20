package org.wlgzs.manipulation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.wlgzs.manipulation.entity.Storage;
import org.wlgzs.manipulation.mapper.StorageMapper;
import org.wlgzs.manipulation.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
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
        queryWrapper.eq("members_id",membersId);
        List<Storage> storageList = baseMapper.selectList(queryWrapper);
        return storageList;
    }
}
