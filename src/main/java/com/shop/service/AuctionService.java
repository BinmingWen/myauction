package com.shop.service;

import com.shop.pojo.Auction;
import com.shop.pojo.AuctionCustomer;
import com.shop.pojo.Auctionrecord;
import com.shop.tools.AuctionException;

import java.util.List;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : AuctionService  //类名
 * @date 2020/10/23 14:29
 */
public interface AuctionService {
    List<Auction> findAllAuctions(Auction auction);

    List<Auction> findAllAuctions();
    void insertAuction(Auction auction);
    Auction selectAuctionAndAuctionList(Integer id);
    void saveAuctionRecord(Auctionrecord record) throws AuctionException;
    List<AuctionCustomer> selectAuctionendtime();
    List<Auction> selectAuctionNoendtime();
}
