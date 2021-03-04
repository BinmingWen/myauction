package com.shop.tools;

/**
 * @author 温明彬
 * @company cn.wyu
 * @Description : //描述
 * @ClassName : AuctionException  //类名
 * @date 2020/10/24 16:49
 */
public class AuctionException extends Exception {

    private String message;

    public AuctionException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
