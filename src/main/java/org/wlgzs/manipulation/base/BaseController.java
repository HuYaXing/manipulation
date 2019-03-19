package org.wlgzs.manipulation.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.wlgzs.manipulation.service.*;

/**
 * @author: 胡亚星
 * @createTime 2019-03-19 9:26
 * @description:
 **/
public class BaseController {

    @Autowired
    protected IMembersService iMembersService;

    @Autowired
    protected IRecordService iRecordService;

    @Autowired
    protected IStaffService iStaffService;

    @Autowired
    protected IStorageService iStorageService;

    @Autowired
    protected ITuinaTypeService iTuinaTypeService;
}
