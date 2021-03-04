package com.shop.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户实体类")
@Data
public class User {
    @ApiModelProperty("用户id")
    private Integer userid;

    @ApiModelProperty("用户姓名")
    private String username;

    @ApiModelProperty("用户密码")
    private String userpassword;

    @ApiModelProperty("身份证号码")
    private String usercardno;

    @ApiModelProperty("电话")
    private String usertel;

    @ApiModelProperty("地址")
    private String useraddress;

    @ApiModelProperty("邮箱编号")
    private String userpostnumber;

    @ApiModelProperty("是否是管理员")
    private Integer userisadmin;

    @ApiModelProperty("问题")
    private String userquestion;

    @ApiModelProperty("答案")
    private String useranswer;

}