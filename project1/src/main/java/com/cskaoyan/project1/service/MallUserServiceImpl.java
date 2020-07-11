package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.MallUserDao;
import com.cskaoyan.project1.dao.MallUserDaoImpl;
import com.cskaoyan.project1.model.bo.MallLoginBO;
import com.cskaoyan.project1.model.bo.MallLoginBO2;
import com.cskaoyan.project1.model.vo.LoginVO;

public class MallUserServiceImpl implements MallUserService {

    MallUserDao mallUserDao = new MallUserDaoImpl();

    @Override
    public void signup(MallLoginBO mallLoginBO) {
        mallUserDao.signup(mallLoginBO);
    }

    @Override
    public LoginVO login(MallLoginBO2 mallLoginBO2) {
         return mallUserDao.login(mallLoginBO2);
    }
}
