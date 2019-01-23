package com.smartdz.dzboss.service.user;

import com.smartdz.dzboss.domain.BaseListMdl;
import com.smartdz.dzboss.domain.User;
import com.smartdz.dzboss.service.BaseService;

public interface IUserService extends BaseService<User> {
    BaseListMdl<User> findByPage(int pageNum, int pageSize);
}
