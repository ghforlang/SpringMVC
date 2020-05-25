package com.edu.nbu.dal.mapper;

import com.edu.nbu.dal.po.UserPO;

public interface UserMapper {

    /**
     * 新增user
     * @param user
     * @return
     */
    int insert(UserPO user);

    /**
     * 根据pkid查询用户信息
     * @param pkId
     * @return
     */
    UserPO getByPkId(Long pkId);
}
