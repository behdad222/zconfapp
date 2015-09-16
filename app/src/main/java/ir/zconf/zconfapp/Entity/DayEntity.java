package ir.zconf.zconfapp.Entity;

public class DayEntity {
    private String title;
    private SubjectEntity[] subject;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubjectEntity[] getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity[] subject) {
        this.subject = subject;
    }
}
