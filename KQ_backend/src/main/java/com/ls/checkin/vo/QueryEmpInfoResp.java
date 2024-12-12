package com.ls.checkin.vo;

import lombok.Data;

@Data
public class QueryEmpInfoResp {

    private long empId;

    private String account;

    private String name;

    private Integer gender;

    private String phone;

    private String email;

    private String avatar;

    private Integer role;
}
