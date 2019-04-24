package id.hike.apps.android_mpos_mumu.util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 28/09/17.
 */

public class MyCallback implements Callback {

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {
//        Toast.makeText(ctx, MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
    }
}
