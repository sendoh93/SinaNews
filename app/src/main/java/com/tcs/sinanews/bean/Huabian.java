package com.tcs.sinanews.bean;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
@JsonObject
public class Huabian {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-12-29 14:42","title":"文咏珊忙拍戏无暇庆生 实力打拼全年无休","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1862/186250/18625017_small.jpg","url":"http://ent.qq.com/a/20161229/024796.htm"},{"ctime":"2016-12-29 14:48","title":"张艺兴微博粉丝破2000万 晒视频送福利","description":"腾讯明星","picUrl":"http://img1.gtimg.com/ent/pics/hv1/211/212/2173/141353596_small.jpg","url":"http://ent.qq.com/a/20161229/025012.htm"},{"ctime":"2016-12-26 06:22","title":"孙志浩大婚梧桐妹认新妈 前妻贾静雯隔空秀恩爱","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1858/185831/18583108_small.jpg","url":"http://ent.qq.com/a/20161226/002052.htm"},{"ctime":"2016-12-26 06:52","title":"林心如首度公开秀恩爱 晒夫妇合照为霍建华庆生","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1858/185831/18583152_small.jpg","url":"http://ent.qq.com/a/20161226/002343.htm"},{"ctime":"2016-12-25 00:02","title":"萧淑慎与小15岁男友曝吻照 曾否认做小三","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000067.htm"},{"ctime":"2016-12-25 00:02","title":"42岁陈德容蕾丝裙肉感十足 昔日女神依旧优雅","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000072.htm"},{"ctime":"2016-12-25 00:02","title":"【存照】虽未领证，他们的爱情足以惊世骇俗","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000075.htm"},{"ctime":"2016-12-25 00:33","title":"查杰熊梓淇沪上欢度圣诞 《刺客2》仍组CP","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000332.htm"},{"ctime":"2016-12-25 06:41","title":"圣诞节到了，美人鱼林允扮性感小鹿","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/001771.htm"},{"ctime":"2016-12-25 06:51","title":"林志玲平安夜穿深V洋装 带神秘男见家长吃团圆饭","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/001820.htm"},{"ctime":"2016-12-25 06:59","title":"组图：阿拉蕾助阵电影首映 穿红披风嘟嘴卖萌","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/001876.htm"},{"ctime":"2016-12-25 07:19","title":"\u201c关关\u201d乔欣穿深V纱裙仙气足 裹羽绒服御寒","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/002103.htm"},{"ctime":"2016-12-25 07:34","title":"组图：郭敬明独自现身机场 玩混搭似霸道总裁","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/002452.htm"},{"ctime":"2016-12-25 07:37","title":"45岁杨钰莹修身裤苗条似少女 皮肤水嫩送甜笑","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/002549.htm"},{"ctime":"2016-12-25 07:22","title":"着急回家陪舒淇？冯德伦遮墨镜微笑侧颜帅气","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1857/185778/18577862_small.jpg","url":"http://ent.qq.com/a/20161225/002161.htm"},{"ctime":"2016-12-25 07:27","title":"老公不疼粉丝疼！何洁圣诞独自抵沪神情落寞","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1857/185778/18577899_small.jpg","url":"http://ent.qq.com/a/20161225/002246.htm"},{"ctime":"2016-12-25 07:29","title":"组图：蜜恋中的唐嫣更美了 涂大红唇甜笑心情靓","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1857/185779/18577911_small.jpg","url":"http://ent.qq.com/a/20161225/002344.htm"},{"ctime":"2016-12-22 10:57","title":"玖月奇迹婚后首次亮相 她看他的眼神含情脉脉","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1855/185547/18554771_small.jpg","url":"http://ent.qq.com/a/20161222/018370.htm"},{"ctime":"2016-12-19 07:25","title":"宅男女神岑丽香荣升\u201c强嫂\u201d 新郎移居香港过二人世","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/003588.htm"},{"ctime":"2016-12-19 07:34","title":"和长腿女神如何合影？陈奕迅教你最正确的方式","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/004027.htm"},{"ctime":"2016-12-19 07:43","title":"光良爱犬瘫痪离世 发文称你离开前还努力忍痛等我回","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/004538.htm"},{"ctime":"2016-12-19 07:46","title":"小&#29605;儿越长越像大S了，在老爸身旁这笑容超甜","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/004733.htm"},{"ctime":"2016-12-19 07:52","title":"女神岑丽香荣升强嫂 两人移居香港过二人世界","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/005069.htm"},{"ctime":"2016-12-19 08:02","title":"关晓彤作为学生代表上台致辞 挥手鞠躬显谦卑","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/005620.htm"},{"ctime":"2016-12-19 08:04","title":"谭晶晒照为父母庆祝结婚40年 一家都是高颜值","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/005761.htm"},{"ctime":"2016-12-19 08:11","title":"张柏芝大方任拍亲切挥手 裹紧羽绒服御寒","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/006045.htm"},{"ctime":"2016-12-19 08:19","title":"陪孩子放假的好妈妈，柏芝回港携两子过节","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/006440.htm"},{"ctime":"2016-12-19 08:28","title":"高大阳光颜值高！陈红16岁小儿子真是帅一脸","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/006871.htm"},{"ctime":"2016-12-19 08:37","title":"星二代曾遭同学霸凌五年 患抑郁症险些自杀","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/007378.htm"},{"ctime":"2016-12-19 08:39","title":"\u201c大长今\u201d李英爱携龙风胎外出 绝美素颜被赞爆","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/007420.htm"},{"ctime":"2016-12-19 08:45","title":"陈紫函夫妇婚后合体现身 十指紧扣甜笑不断","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/007842.htm"},{"ctime":"2016-12-19 08:49","title":"组图：秀腿狂魔杨幂你到底冷不冷啊冷啊","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/008109.htm"},{"ctime":"2016-12-19 08:51","title":"组图：吴奇隆紫发抢镜 新造型刘诗诗满意否？","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/008272.htm"},{"ctime":"2016-12-19 08:54","title":"来自马栏山的时尚！谢娜红大衣抢眼","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/008481.htm"},{"ctime":"2016-12-19 09:13","title":"组图：王晓晨变\u201c蛇精脸\u201d 自拍神似尹恩惠","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/009884.htm"},{"ctime":"2016-12-19 09:16","title":"黄晓明为兄弟一口闷酱油，仗义\u201c黄担当\u201d上线","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/010182.htm"},{"ctime":"2016-12-19 09:18","title":"章子怡巨幅封面闪耀上海外滩 网友赞醒宝","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/010335.htm"},{"ctime":"2016-12-19 09:21","title":"田亮洁癖被治愈？护妻细节惹叶一茜好感动","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/010588.htm"},{"ctime":"2016-12-19 09:28","title":"王中磊下厨显居家 女儿夸赞：王厨师长非常厉害","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/011222.htm"},{"ctime":"2016-12-19 09:40","title":"韩媒：受年轻人欢迎，\u201c中流\u201d逆袭韩国！","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/012263.htm"},{"ctime":"2016-12-19 09:43","title":"自拍狂魔！雪莉与男友逛街仍不忘自拍","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/012541.htm"},{"ctime":"2016-12-19 09:45","title":"朴宝剑辱华广告后转战香港 见面会痛哭","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/012697.htm"},{"ctime":"2016-12-19 09:47","title":"组图：\u201c最富总统千金\u201d伊万卡上班 长腿撩人","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/012825.htm"},{"ctime":"2016-12-19 09:54","title":"蒋欣成自黑界小能手 P图百变造型笑翻网友","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/013381.htm"},{"ctime":"2016-12-19 09:54","title":"3个月瘦了20斤，可阿娇的小粗腿还是小粗腿","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/013389.htm"},{"ctime":"2016-12-19 10:03","title":"《布拉德广场》的那个Rap小童星长大了 颜值爆表","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/014048.htm"},{"ctime":"2016-12-19 10:09","title":"少时黄美英暴瘦！竹竿腿皮包骨吓坏粉丝","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/014482.htm"},{"ctime":"2016-12-19 10:17","title":"义气兄妹！智孝钟国将捐赠剩余RM出演费","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/015121.htm"},{"ctime":"2016-12-19 10:25","title":"组图：李小璐晒美颜 唇红肤白好似少女","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/015661.htm"},{"ctime":"2016-12-19 10:32","title":"金秀贤与原东家续约 延续七年情谊","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161219/015982.htm"}]
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
