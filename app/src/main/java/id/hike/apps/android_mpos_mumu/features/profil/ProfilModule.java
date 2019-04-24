package id.hike.apps.android_mpos_mumu.features.profil;

import dagger.Module;
import dagger.Provides;

/**
 * Created by root on 12/10/17.
 */

@Module
public class ProfilModule {


    @Provides
    public ProfilTest providesFProfil(){
        return new ProfilTest();
    }


}
