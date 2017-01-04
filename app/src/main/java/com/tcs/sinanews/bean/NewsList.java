package com.tcs.sinanews.bean;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Administrator on 2017/1/4.
 */
@JsonObject
public  class NewsList {

        /**
         * ctime : 2016-12-29 14:42
         * title : 文咏珊忙拍戏无暇庆生 实力打拼全年无休
         * description : 腾讯明星
         * picUrl : http://img1.gtimg.com/18/1862/186250/18625017_small.jpg
         * url : http://ent.qq.com/a/20161229/024796.htm
         */
        @JsonField
        private String ctime;
        @JsonField
        private String title;
        @JsonField
        private String description;
        @JsonField
        private String picUrl;
        @JsonField
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

