package com.tcs.sinanews.bean;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
@JsonObject
public class mobile {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-12-29 20:44","title":"库克：AirPods大获成功 正在努力加快生产","description":"通信行业","picUrl":"http://cms-bucket.nosdn.127.net/37fc1f7add734aecb473d6e0a08578fa20161229204408.png","url":"http://tech.163.com/16/1229/20/C9FU4FBH00097U7S.html"},{"ctime":"2016-12-29 20:03","title":"新华社：恶评水军毁不了经典，真正危害电影市场","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/ef3ab4c220bb4ef98213d8520009c8dd20161229200251.png","url":"http://tech.163.com/16/1229/20/C9FRP5EH00097U7R.html"},{"ctime":"2016-12-29 20:16","title":"喜欢使用多个社交媒体？小心抑郁焦虑正在靠近","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/a81681f24352461abc08b4c0488533aa20161229201628.png","url":"http://tech.163.com/16/1229/20/C9FSHQGQ00097U7R.html"},{"ctime":"2016-12-29 20:23","title":"商务部回应美将阿里列入恶名市场名单：不负责不","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/5f7416cef0764d839e7986c5fe92748b20161229202257.png","url":"http://tech.163.com/16/1229/20/C9FSTJSS00097U7R.html"},{"ctime":"2016-12-29 20:38","title":"韩国亲信门主角崔顺实否认指控：未向三星索捐","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/9adf03b5d2e24d84be915e2bfa6bd77d20161229203835.png","url":"http://tech.163.com/16/1229/20/C9FTQLUI00097U7R.html"},{"ctime":"2016-12-29 20:53","title":"全牌照再下一城 拉卡拉战略入股包头农商行","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/9695f41e9a634d09a8d626b9da303f6f20161229205339.png","url":"http://tech.163.com/16/1229/20/C9FULNVB00097U7R.html"},{"ctime":"2016-12-29 17:35","title":"韩\u201c亲信门\u201d主角崔顺实否认指控：未向三星索捐","description":"通信行业","picUrl":"http://cms-bucket.nosdn.127.net/1445feeb99f745ea8281f774e25f765320161229163812.png","url":"http://tech.163.com/16/1229/17/C9FJADDB00097U7S.html"},{"ctime":"2016-12-29 17:11","title":"全球顶级富豪2016年更富有了 科技公司收获颇丰","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/06d26ce97aee429abcb4d4e350054b3920161229161509.png","url":"http://tech.163.com/16/1229/17/C9FHUBFF00097U7R.html"},{"ctime":"2016-12-29 17:41","title":"硅谷阴暗面：独角兽高估值高增长背后的造假丑闻","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/7332721157d6463bbc70fb9dfc6a025920161229174138.png","url":"http://tech.163.com/16/1229/17/C9FJM58300097U7R.html"},{"ctime":"2016-12-29 17:55","title":"用户眼中啥是好电影：风暴中的豆瓣发布2016榜单","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/5e8d91875da44a48bc9d652cd6fc0b0820161229175548.png","url":"http://tech.163.com/16/1229/17/C9FKG51600097U7R.html"}]
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
