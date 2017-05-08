package studioday02.kimda.com.studiogrils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BUTTON on 2017-05-08.
 */

public class GrilsBean {
    private String publishedAt;
    private String imgeUrl;

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    //需要的Bean
    public static class ResultsBean {
        /**
         * _id : 590fe059421aa90c83a513f2
         * createdAt : 2017-05-08T11:04:57.969Z
         * desc : 5-8
         * publishedAt : 2017-05-08T11:22:01.540Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-08-18252341_289400908178710_9137908350942445568_n.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        @SerializedName("publishedAt")
        private String publishedAtX;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAtX() {
            return publishedAtX;
        }

        public void setPublishedAtX(String publishedAtX) {
            this.publishedAtX = publishedAtX;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
