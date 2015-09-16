package ir.zconf.zconfapp.DB;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import ir.zconf.zconfapp.DB.Model.DayModel;
import ir.zconf.zconfapp.DB.Model.PictureModel;
import ir.zconf.zconfapp.DB.Model.SubjectModel;
import ir.zconf.zconfapp.Entity.DayEntity;
import ir.zconf.zconfapp.Entity.PictureEntity;

public class DBAdapter {
    private static Realm realm;

    public DBAdapter(Context context) {
        realm = Realm.getInstance(context.getApplicationContext());
    }

    public void insertOrUpdateDay(DayEntity day, String title) {
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
        dayModel.setTitle(title);
        realm.commitTransaction();
    }

    public void insertOrUpdatePictures(PictureEntity[] picture) {
        PictureModel pictureModel;
        realm.beginTransaction();

        for (int i = 0; i < picture.length; i++) {
            pictureModel = getPicture(picture[i].getId());

            if (pictureModel == null) {
                pictureModel = realm.createObject(PictureModel.class);
            }

            pictureModel.setDescription(picture[i].getDescription());
            pictureModel.setId(picture[i].getId());
            pictureModel.setUrl(picture[i].getUrl());
        }
        realm.commitTransaction();
    }

    public DayModel getDay(String day) {
        return realm
                .where(DayModel.class)
                .equalTo("title", day)
                .findFirst();
    }

    public PictureModel getPicture(String id) {
        return realm
                .where(PictureModel.class)
                .equalTo("id", id)
                .findFirst();
    }

    public List<PictureModel> getAllPicture() {
        return realm
                .where(PictureModel.class)
                .findAll();
    }
}
