package com.superdroid.base.entities;

/**
 * Created by GT on 2015/8/24.
 */
public class Banner{
    /**
     * "title": "武林风走进荥阳",
     * "type": "1",
     * "share": {
     * "title": "武林风走进荥阳",
     * "imgPath": "http://xyapp.ikinvin.net/upload/gallery/thumbnail/735C90F2-D39A-6654-D47787212666-tbl.jpg",
     * "url": "http://xyapp.ikinvin.net/post/detail/id/257"
     * },
     * "value": "257",
     * "image": "http://xyapp.ikinvin.net/upload/gallery/thumbnail/735C90F2-D39A-6654-D47787212666-tbl.jpg"
     */

    private String title;
    private String type;
    private Share share;
    private String value;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public class Share {
        private String title;
        private String imgPath;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
