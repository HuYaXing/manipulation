package org.wlgzs.manipulation.service.impl;

import org.wlgzs.manipulation.entity.TuinaType;
import org.wlgzs.manipulation.mapper.TuinaTypeMapper;
import org.wlgzs.manipulation.service.ITuinaTypeService;
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
public class TuinaTypeServiceImpl extends ServiceImpl<TuinaTypeMapper, TuinaType> implements ITuinaTypeService {

    @Override
    public List<TuinaType> selectType() {
        return null;
    }
}
