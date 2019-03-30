package org.wlgzs.manipulation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wlgzs.manipulation.entity.Members;
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
public interface IMembersService extends IService<Members> {
    //添加会员
    Result addMembers(Members members);

    //搜索会员
    Result membersList(int page, String findName);

    Result delete(int membersId);

    //查询会员的信息
    Members details(int membersId);

    //修改会员信息
    Result Modify(Members members);

    //会员添加剩余次数
    Result addNumber(int Number,int membersId);
}
