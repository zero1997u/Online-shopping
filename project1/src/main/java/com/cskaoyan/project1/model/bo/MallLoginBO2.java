package com.cskaoyan.project1.model.bo;

public class MallLoginBO2 {
    private String email;
    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object obj){
        MallLoginBO2 mallLoginBO2 = (MallLoginBO2)obj;
        if(!mallLoginBO2.email.equals(email)){
            return false;
        }else if(!mallLoginBO2.pwd.equals(pwd)){
            return false;
        }
        return true;

    }
}
