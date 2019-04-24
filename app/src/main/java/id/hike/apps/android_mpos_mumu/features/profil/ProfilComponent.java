package id.hike.apps.android_mpos_mumu.features.profil;

import android.app.Activity;
import dagger.Component;

/**
 * Created by root on 12/10/17.
 */
@Component(modules = {ProfilModule.class})
public interface ProfilComponent {
    void inject(Activity activity);
}