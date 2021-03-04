package com.shop.service.imple;

import com.shop.mapper.AuctionCustomerMapper;
import com.shop.mapper.AuctionMapper;
import com.shop.mapper.AuctionrecordMapper;
import com.shop.pojo.Auction;
import com.shop.pojo.AuctionCustomer;
import com.shop.pojo.AuctionExample;
import com.shop.pojo.Auctionrecord;
import com.shop.service.AuctionService;
import com.shop.tools.AuctionException;
import com.sun.media.sound.AiffFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : AuctionServiceImple  //类名
 * @date 2020/10/23 14:30
 */
@Service
public class AuctionServiceImple implements AuctionService {
    @Autowired
    private AuctionMapper auctionMapper;
    @Autowired
    private AuctionCustomerMapper auctionCustomerMapper;
    @Autowired
    private AuctionrecordMapper auctionrecordMapper;
    @Override
    public List<Auction> findAllAuctions( Auction auction) {
        AuctionExample example = new AuctionExample();
        AuctionExample.Criteria criteria = example.createCriteria();
        if(auction!=null){
            //查询判断
            if(auction.getAuctionname()!=null && !"".equals(auction.getAuctionname())){
                criteria.andAuctionnameLike("%"+auction.getAuctionname()+"%");

            }
            if(auction.getAuctiondesc() !=null && !"".equals(auction.getAuctiondesc())){
                criteria.andAuctiondescLike("%"+auction.getAuctiondesc()+"%");
            }
            if(auction.getAuctionstarttime()!=null){
                criteria.andAuctionstarttimeGreaterThanOrEqualTo(auction.getAuctionstarttime());
            }
            if(auction.getAuctionendtime() !=null){
                criteria.andAuctionendtimeLessThanOrEqualTo(auction.getAuctionendtime());
            }
            if(auction.getAuctionstartprice() !=null){
                criteria.andAuctionstartpriceGreaterThan(auction.getAuctionstartprice());
            }

        }
        List<Auction> auctions = auctionMapper.selectByExample(example);
        return auctions;
    }

    @Override
    public List<Auction> findAllAuctions() {
        AuctionExample example = new AuctionExample();
        AuctionExample.Criteria criteria = example.createCriteria();
        List<Auction> auctions = auctionMapper.selectByExample(example);
        return auctions;
    }

    @Override
    public void insertAuction(Auction auction) {
        auctionMapper.insert(auction);
    }

    @Override
    public Auction selectAuctionAndAuctionList(Integer id) {
        return auctionCustomerMapper.selectAuctionAndAuctionRecordList(id);
    }

    @Override
    public void saveAuctionRecord(Auctionrecord record) throws AuctionException {

        Auction auction = auctionCustomerMapper.selectAuctionAndAuctionRecordList(record.getAuctionid());
        List<Auctionrecord> list = auction.getAuctionrecodList();
        //1.判断当前时间是否在拍卖期间
        if(!auction.getAuctionendtime().after(record.getAuctiontime())){
            //1.1当前竞拍时间不在拍卖期间，无法进行商品拍卖
            throw new AuctionException("当前活动已结束");
        }

        //2.判断当前竞拍记录的值，是否为最高价
        if(list.size()>0){
            //2.1表明当前记录中存在着拍卖记录
            Auctionrecord passRecord = list.get(0);
            if(passRecord.getAuctionprice().compareTo(record.getAuctionprice())==-1){
                //2.2拍卖价格低于拍卖记录中的最高价格
                throw new AuctionException("不能低于最高拍卖价");
            }
        }
        else{
            //2.3判断当前拍卖价格，是否高于无人拍卖的商品的价格
            if(record.getAuctionprice().compareTo(auction.getAuctionstartprice())==-1){
                //2.4拍卖者的价格不能低于商品的最低竞价
                throw new AuctionException("不能低于起拍价");

            }

        }
        auctionrecordMapper.insert(record);


    }

    @Override
    public List<AuctionCustomer> selectAuctionendtime() {
        return auctionCustomerMapper.selectAuctionendtime();
    }

    @Override
    public List<Auction> selectAuctionNoendtime() {
        return auctionCustomerMapper.selectAuctionNoendtime();
    }
}
