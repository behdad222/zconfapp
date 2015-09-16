package ir.zconf.zconfapp.Entity;

public class SubjectEntity {
    private String title;
    private String time;
    private String start;
    private String speaker;
    private String image;
    private String type;

    public String getTitle() {
        return title + "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time + "";
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStart() {
        return start + "";
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSpeaker() {
        return speaker + "";
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
