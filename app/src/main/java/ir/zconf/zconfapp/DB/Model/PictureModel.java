package ir.zconf.zconfapp.DB.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PictureModel extends RealmObject {
    @PrimaryKey
    private String id;
    private String description;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
