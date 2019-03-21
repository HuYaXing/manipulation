package org.wlgzs.manipulation.service;

import org.wlgzs.manipulation.entity.TuinaType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-03-19
 */
public interface ITuinaTypeService extends IService<TuinaType> {
    //
    List<TuinaType> selectType();
}
