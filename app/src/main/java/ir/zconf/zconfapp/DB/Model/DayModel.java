package ir.zconf.zconfapp.DB.Model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class DayModel extends RealmObject {
    private String title;
    private RealmList<SubjectModel> subject;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<SubjectModel> getSubject() {
        return subject;
    }

    public void setSubject(RealmList<SubjectModel> subject) {
        this.subject = subject;
    }
}
