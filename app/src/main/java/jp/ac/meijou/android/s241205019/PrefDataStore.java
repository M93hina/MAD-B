package jp.ac.meijou.android.s241205019;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import android.content.Context;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.datastore.preferences.core.PreferencesKeys;
import io.reactivex.rxjava3.core.Single;
import java.util.Optional;

public class PrefDataStore {
    private static PrefDataStore instance;
    private final RxDataStore<Preferences> dataStore;

    private PrefDataStore(RxDataStore<Preferences> dataStore) {
       this.dataStore = dataStore;
    }

    public static PrefDataStore getInstance(Context context) {
        if (instance == null) {
            var dataStore = new RxPreferenceDataStoreBuilder(
                context.getApplicationContext(), "settings").build();
            instance = new PrefDataStore(dataStore);
        }
       return instance;
    }

    public void setString(String key, String value) {
        dataStore.updateDataAsync(prefIn -> {
            var mutablePreferences = prefIn.toMutablePreferences();
            var prefKey = PreferencesKeys.stringKey(key);

            mutablePreferences.set(prefKey, value);
            return Single.just(mutablePreferences);
        }).subscribe();
    }

    public Optional<String> getString(String key) {
        return dataStore.data().map(prefs -> {
            var prefKey = PreferencesKeys.stringKey(key);
            return Optional.ofNullable(prefs.get(prefKey));
        })
        .blockingFirst(Optional.empty());
    }

}

