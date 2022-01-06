package com.tech.db;


public class bowlMember {
    private String id;
    private String pwd;
    private String name;
    private String tel;
    private boolean is_Man;
    private String birth;
    private String regDate;
    private String email;
    private boolean is_lunar;
    private String habit;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getId() {
        return id;
    }

    public void setId(String ID) {
        this.id = ID;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_Man() {
        return is_Man;
    }

    public void setIs_Man(boolean is_Man) {
        this.is_Man = is_Man;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_lunar() {
        return is_lunar;
    }

    public void setIs_lunar(boolean is_lunar) {
        this.is_lunar = is_lunar;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }
}
