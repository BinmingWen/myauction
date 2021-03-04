package com.shop.tools;

import com.shop.pojo.Auction;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : MyHandlerException  //类名
 * @date 2020/10/24 16:51
 */
@Component
public class MyHandlerExceptionResovlor implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        AuctionException ex = null;
        if(e instanceof AuctionException){
            ex = (AuctionException) e;
        }else{
            ex =  new AuctionException("未知异常");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",ex.getMessage());
        mv.setViewName("errorMsg");
        return mv;
    }
}
