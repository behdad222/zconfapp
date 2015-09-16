package ir.zconf.zconfapp.Interface.Retrofit;

import ir.zconf.zconfapp.Entity.PictureRespunse;
import retrofit.Callback;
import retrofit.http.GET;

public interface GetPictureApi {
    @GET("/files/zconf/picture.json")
    void getPicture(Callback<PictureRespunse> callback);
}
