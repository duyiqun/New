package com.qun.news360demo;

import java.util.List;

/**
 * Created by Qun on 2017/4/27.
 */

public class JsonBean {

    /**
     * data : [{"id":"1001","title":"李小璐带甜馨为爷爷祝寿 美拍晒温馨视频","weburl":"http://192.168.0.106:8080/zhbj/10007/BFUGKA5N00964J4O.html","imgs":["http://192.168.0.106:8080/360/t0124872170f1bc2e86.jpg","http://192.168.0.106:8080/360/t01ccd3d190a4f01aac.jpg","http://192.168.0.106:8080/360/t01223e7afa3f104da0.jpg"]},{"id":"1002","title":"交警执勤意外查获网逃人员","imgs":[]},{"id":"1003","title":"尼可儿童摄影落户长沙","imgs":["http://192.168.0.106:8080/360/t01a1bd3f34e1ea4059.jpg"]},{"id":"1004","title":"创业板黑天鹅?金亚科技成其退市第一股?NO NO NO","imgs":["http://192.168.0.106:8080/360/t017c05cb5e843300da.jpg","http://192.168.0.106:8080/360/t01d94eec3fa1db3d72.jpg","http://192.168.0.106:8080/360/t01f06bf71e629028bf.jpg"]},{"id":"1004","title":"2015数据中心宕机20%是由DDoS攻击引起","imgs":["http://192.168.0.106:8080/360/t01125229f42a277bcf.png","http://192.168.0.106:8080/360/t0132a413a541937d2e.jpg","http://192.168.0.106:8080/360/t01ba1cf58f92fad2d0.jpg"]},{"id":"1004","title":"股价倒挂风起 跌破定增价掩映投资价值","imgs":[]},{"id":"1004","title":"锁骨有它的女人干啥都发财","imgs":["http://192.168.0.106:8080/360/t01c7efd38deb67814a.jpg"]},{"id":"1004","title":"新年首月长沙商品住宅量价齐涨 成交创5年来新高","imgs":["http://192.168.0.106:8080/360/t010efbfef2b8729d83.jpg"]}]
     * error : 0
     */

    private int error;
    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1001
         * title : 李小璐带甜馨为爷爷祝寿 美拍晒温馨视频
         * weburl : http://192.168.0.106:8080/zhbj/10007/BFUGKA5N00964J4O.html
         * imgs : ["http://192.168.0.106:8080/360/t0124872170f1bc2e86.jpg","http://192.168.0.106:8080/360/t01ccd3d190a4f01aac.jpg","http://192.168.0.106:8080/360/t01223e7afa3f104da0.jpg"]
         */

        private String id;
        private String title;
        private String weburl;
        private List<String> imgs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWeburl() {
            return weburl;
        }

        public void setWeburl(String weburl) {
            this.weburl = weburl;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
