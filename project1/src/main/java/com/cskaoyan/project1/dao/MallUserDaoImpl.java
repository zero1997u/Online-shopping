package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.MallLoginBO;
import com.cskaoyan.project1.model.bo.MallLoginBO2;
import com.cskaoyan.project1.model.vo.LoginVO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class MallUserDaoImpl implements MallUserDao {
    @Override
    public void signup(MallLoginBO mallLoginBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into user values(null,?,?,?,?,?,?)",
                    mallLoginBO.getEmail(),
                    mallLoginBO.getNickname(),
                    mallLoginBO.getPwd(),
                    mallLoginBO.getRecipient(),
                    mallLoginBO.getAddress(),
                    mallLoginBO.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoginVO login(MallLoginBO2 mallLoginBO2) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        MallLoginBO2 mallLoginBO21 = null;
        LoginVO loginVO = new LoginVO();
        try {
            mallLoginBO21 = runner.query("select email,pwd" +
                    " from user where email = ?",new BeanHandler<>(MallLoginBO2.class),
                    mallLoginBO2.getEmail());
            loginVO = runner.query("select nickname as token,nickname as name from user where email = ?",
                    new BeanHandler<>(LoginVO.class),mallLoginBO2.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(mallLoginBO21.equals(mallLoginBO2)){
                return loginVO;
            }else{
                return null;
            }
        }

    }
}
