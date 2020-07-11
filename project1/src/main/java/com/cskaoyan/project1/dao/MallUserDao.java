package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.MallLoginBO;
import com.cskaoyan.project1.model.bo.MallLoginBO2;
import com.cskaoyan.project1.model.vo.LoginVO;

public interface MallUserDao {
    void signup(MallLoginBO mallLoginBO);

    LoginVO login(MallLoginBO2 mallLoginBO2);
}
