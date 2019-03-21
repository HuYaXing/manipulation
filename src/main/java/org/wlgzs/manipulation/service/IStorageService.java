package org.wlgzs.manipulation.service;

import org.wlgzs.manipulation.entity.Storage;
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
public interface IStorageService extends IService<Storage> {

    //查询（按用户）
    List<Storage> selectStorage(int membersId);

    //新增一条
    Result addStorage(Storage storage);

    //刪除一条
    Result deleteStorage(int storageId);

    //按类型和用户查询
    Storage selectStorage(int membersId,String tuinaType);
}
