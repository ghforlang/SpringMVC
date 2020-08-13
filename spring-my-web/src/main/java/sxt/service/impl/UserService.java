package sxt.service.impl;

import sxt.dao.UserDao;
//import com.edu.nbu.dal.po.User;
import sxt.service.IUserService;

/**
 * Created by lenovo on 2017/1/10.
 */
public class UserService implements IUserService {
    private UserDao userDao;

    @Override
    public void add(String uname) {
//        System.out.println("UserService.add()");
//        User u = new User();
//        u.setUname(uname);
//        userDao.add(u);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

