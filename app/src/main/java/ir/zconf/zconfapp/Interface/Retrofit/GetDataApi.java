package ir.zconf.zconfapp.Interface.Retrofit;

import ir.zconf.zconfapp.Entity.DataEntity;
import retrofit.Callback;
import retrofit.http.GET;

public interface GetDataApi {
    @GET("/files/zconf/data.json")
    void getData(Callback<DataEntity> callback);
}
