package com.shop.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Auctionrecord {
    private Integer id;

    private Integer userid;

    private Integer auctionid;

    private Date auctiontime;

    private BigDecimal auctionprice;

    public Auctionrecord(Integer id, Integer userid, Integer auctionid, Date auctiontime, BigDecimal auctionprice, User user) {
        this.id = id;
        this.userid = userid;
        this.auctionid = auctionid;
        this.auctiontime = auctiontime;
        this.auctionprice = auctionprice;
        this.user = user;
    }

    // 表示一对一关系
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Date getAuctiontime() {
        return auctiontime;
    }

    public void setAuctiontime(Date auctiontime) {
        this.auctiontime = auctiontime;
    }

    public BigDecimal getAuctionprice() {
        return auctionprice;
    }

    public void setAuctionprice(BigDecimal auctionprice) {
        this.auctionprice = auctionprice;
    }
}