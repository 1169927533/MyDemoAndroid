package com.example.a11699.mydemo.ui.shopflipper;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：余智强
 * 2019/9/20
 */
public class Shopbean  {

    private List<ShopBean> shop;
    int pageallnum;

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public static class ShopBean implements Serializable {

        /**
         * shopId : 1
         * shopName : 未来科技城店铺
         * shopAddress : 余杭临平
         * shopPhone : 13738097678
         * shopImg : https://pic1.ajkimg.com/display/anjuke/8916916c4458ac40a2305edc44671681/220x165c.jpg?t=1
         * shopLongitude : 100
         * shopLatitude : 10
         * shopType : 商业
         * shopDesc : 未来广场地铁口，大学旁
         * shopSpend : 100
         */
        private int shopId;
        private String shopName;
        private String shopAddress;
        private String shopPhone;
        private String shopImg;
        private String shopLongitude;
        private String shopLatitude;
        private String shopType;
        private String shopDesc;
        private String shopSpend;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        public String getShopImg() {
            return shopImg;
        }

        public void setShopImg(String shopImg) {
            this.shopImg = shopImg;
        }

        public String getShopLongitude() {
            return shopLongitude;
        }

        public void setShopLongitude(String shopLongitude) {
            this.shopLongitude = shopLongitude;
        }

        public String getShopLatitude() {
            return shopLatitude;
        }

        public void setShopLatitude(String shopLatitude) {
            this.shopLatitude = shopLatitude;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getShopDesc() {
            return shopDesc;
        }

        public void setShopDesc(String shopDesc) {
            this.shopDesc = shopDesc;
        }

        public String getShopSpend() {
            return shopSpend;
        }

        public void setShopSpend(String shopSpend) {
            this.shopSpend = shopSpend;
        }
    }

    public int getPageallnum() {
        return pageallnum;
    }

    public void setPageallnum(int pageallnum) {
        this.pageallnum = pageallnum;
    }
}
