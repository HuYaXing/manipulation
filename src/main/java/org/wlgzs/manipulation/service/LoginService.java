package org.wlgzs.manipulation.service;

import org.wlgzs.manipulation.util.Result;

import javax.servlet.http.HttpSession;

/**
 * @author: 胡亚星
 * @createTime 2019-03-19 11:13
 * @description:
 **/
public interface LoginService {

    //登录
    public Result login(HttpSession session, String userPhone, String userPassword);

}
