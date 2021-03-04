package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.mapper.AuctionCustomerMapper;
import com.shop.pojo.Auction;
import com.shop.pojo.AuctionCustomer;
import com.shop.pojo.Auctionrecord;
import com.shop.pojo.User;
import com.shop.service.AuctionService;
import com.shop.tools.AuctionException;
import com.sun.jdi.connect.LaunchingConnector;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : AuctionController  //类名
 * @date 2020/10/23 15:17
 */
@Controller
@Api(tags = "拍卖控制器")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;


    private final static Integer pageSize = 5;

    @RequestMapping("/queryAllUsers")
    @ApiOperation("分页查询所有商品")
    public ModelAndView queryAllUsers(@ModelAttribute("condition") @ApiParam("查询条件") Auction condition,
                                      @ApiParam("数据校验") BindingResult bindingResult,
                                      @RequestParam(name ="pageNum",required = false,defaultValue = "1")
                                                  @ApiParam("页数") int pageNum){
        //设置分页区间
        PageHelper.startPage(pageNum,pageSize);
        List<Auction> auctions = auctionService.findAllAuctions(condition);
        //创建分页
        PageInfo pageInfo = new PageInfo(auctions);
        ModelAndView mv = new ModelAndView();
        mv.addObject("auctionList",auctions);
        mv.addObject("page",pageInfo);
        mv.setViewName("index");
        return mv;

    }
    @RequestMapping("/compAuction")
    @ApiOperation("竞拍页面")
    public ModelAndView compAuction(@ApiParam("竞拍品id") Integer id){
        Auction auction = auctionService.selectAuctionAndAuctionList(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("auctionDetail",auction);
        mv.setViewName("auctionDetail");
        return mv;
    }

    @RequestMapping("/saveAuctionRecord")
    @ApiOperation("保存竞拍记录")
    public String saveAuctionRecord(@ApiParam("竞拍记录") Auctionrecord record,
                                    @ApiParam("session对象") HttpSession session) throws AuctionException {
         User user = (User)session.getAttribute("user");
         record.setUserid(user.getUserid());
         record.setAuctiontime(new Date());
         auctionService.saveAuctionRecord(record);
         return "redirect:/compAuction?id="+record.getAuctionid();
    }
    @RequestMapping("/toAuctionResult")
    @ApiOperation("查询拍卖信息")
    public ModelAndView toAuctionResult(){
        ModelAndView mv = new ModelAndView();
        List<AuctionCustomer> endList = auctionService.selectAuctionendtime();
        List<Auction> noendList = auctionService.selectAuctionNoendtime();
        mv.addObject("endtimeList",endList);
        mv.addObject("noendtimeList",noendList);
        mv.setViewName("auctionResult");
        return mv;
    }
    @RequestMapping("/toAddAuction")
    @ApiOperation("跳转到商品添加页面")
    public String toAddAuction(){
        return "addAuction";
    }
    @RequestMapping("/publishAuctions")
    @ApiOperation("添加竞拍品新")
    public String publishAuctions(@ApiParam("商品对象") Auction auction,
                                  @ApiParam("商品图片") MultipartFile pic) throws IOException {
        //获取文件名称
        String originalFilename = pic.getOriginalFilename();
        //获取所上传的文件类型
        String contentType = pic.getContentType();
        auction.setAuctionpic(originalFilename);
        auction.setAuctionpictype(contentType);
        pic.transferTo(new File("E:\\picture\\"+originalFilename));

        //把拍卖信息保存到数据库中
        auctionService.insertAuction(auction);
        return "redirect:/queryAllUsers";
    }

}
