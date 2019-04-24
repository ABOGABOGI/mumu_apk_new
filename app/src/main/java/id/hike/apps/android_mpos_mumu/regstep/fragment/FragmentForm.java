package id.hike.apps.android_mpos_mumu.regstep.fragment;


import java.util.Map;

import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class FragmentForm extends BaseFragment {


    public Observable<Map<String, Object>> isFormComplete(){
        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {

                emitter.onError(new Exception("Empty Form"));
                emitter.onComplete();

            }
        });
    }

}
