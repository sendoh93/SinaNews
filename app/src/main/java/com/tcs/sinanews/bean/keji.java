package com.tcs.sinanews.bean;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
@JsonObject
public class keji {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-12-29 18:19","title":"国外报告称中国电信资费处于全球\u201c最便宜\u201d阵营","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/969937145_300240/0","url":"http://tech.qq.com/a/20161229/032981.htm"},{"ctime":"2016-12-29 17:56","title":"工信部：宽带平均网速已达48M 4G用户超7亿","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/969720776_300240/0","url":"http://tech.qq.com/a/20161229/032245.htm"},{"ctime":"2016-12-29 16:36","title":"韩亲信门主角承认获得三星赞助 但否认逼捐","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/969153948_300240/0","url":"http://tech.qq.com/a/20161229/029431.htm"},{"ctime":"2016-12-29 16:30","title":"库克终于开口谈了AirPods：销量好到难以置信","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/969225389_300240/0","url":"http://tech.qq.com/a/20161229/029141.htm"},{"ctime":"2016-12-29 16:54","title":"三星公布最新Note 7召回政策：取消补贴 元旦开始实施","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/969196864_300240/0","url":"http://tech.qq.com/a/20161229/030128.htm"},{"ctime":"2016-12-29 12:12","title":"百度收购\u201c李叫兽\u201d公司 创始人李靖出任百度副总裁","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/968087308_300240/0","url":"http://tech.qq.com/a/20161229/020403.htm"},{"ctime":"2016-12-29 10:57","title":"迪拜警方利用AI技术预测违法犯罪事件","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/967742453_300240/0","url":"http://tech.qq.com/a/20161229/016833.htm"},{"ctime":"2016-12-29 07:29","title":"公安部A级通缉嫌犯落网：流窜9地高校偷200部手机","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/967180228_300240/0","url":"http://tech.qq.com/a/20161229/003762.htm"},{"ctime":"2016-12-29 07:35","title":"\u201c同道大叔\u201d蔡跃栋：28岁赚上亿不算成功","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/967180094_300240/0","url":"http://tech.qq.com/a/20161229/004115.htm"},{"ctime":"2016-12-29 07:35","title":"苹果太空船总部一期工程完建时间推迟 不过这并不意外","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/967180149_300240/0","url":"http://tech.qq.com/a/20161229/004071.htm"}]
     */
    @JsonField
    private int code;
    @JsonField
    private String msg;
    @JsonField
    private List<NewsList> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsList> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsList> newslist) {
        this.newslist = newslist;
    }
}
