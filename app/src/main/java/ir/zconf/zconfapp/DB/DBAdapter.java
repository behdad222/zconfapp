package ir.zconf.zconfapp.DB;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmList;
import ir.zconf.zconfapp.DB.Model.DayModel;
import ir.zconf.zconfapp.DB.Model.SubjectModel;
import ir.zconf.zconfapp.Entity.DayEntity;

public class DBAdapter {
    private static Realm realm;

    public DBAdapter(Context context) {
        realm = Realm.getInstance(context.getApplicationContext());
    }


    public void insertOrUpdateDay(DayEntity day) {
        DayModel dayModel;

        realm.beginTransaction();
        dayModel = getDay(day.getTitle());

        if (dayModel == null) {
            dayModel = realm.createObject(DayModel.class);
        }

        RealmList<SubjectModel> subjectModels = new RealmList<>();
        for (int i = 0; i < dayModel.getSubject().size(); i++) { //// TODO: 9/16/15 subjectha tekrari bevojod miad!
            SubjectModel subjectModel = realm.createObject(SubjectModel.class);
            subjectModel.setImage(day.getSubject()[i].getImage());
            subjectModel.setSpeaker(day.getSubject()[i].getSpeaker());
            subjectModel.setStart(day.getSubject()[i].getStart());
            subjectModel.setTime(day.getSubject()[i].getTime());
            subjectModel.setTitle(day.getSubject()[i].getTitle());
            subjectModels.add(subjectModel);
        }

        dayModel.setSubject(subjectModels);
        realm.commitTransaction();
    }


    public DayModel getDay(String day) {
        return realm
                .where(DayModel.class)
                .equalTo("title", day)
                .findFirst();
    }
}
