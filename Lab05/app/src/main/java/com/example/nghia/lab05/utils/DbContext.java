package com.example.nghia.lab05.utils;


import android.content.Context;

import com.example.nghia.lab05.models.ThirtyShineResponseBody;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;

/**
 * Created by Nghia on 12/11/2016.
 */

public class DbContext {
    private Realm realm;

    private static DbContext instance  ;

    public static DbContext getInstance() {
        return instance;
    }
    public DbContext(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }


    public void copyOrUpdateResponseBody(ThirtyShineResponseBody body) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(body);
        realm.commitTransaction();
    }

    public void insertResponseBody(ThirtyShineResponseBody body) {
        realm.beginTransaction();
        realm.insert(body);
        realm.commitTransaction();
    }

    public ThirtyShineResponseBody selectBody() {
        return realm.where(ThirtyShineResponseBody.class).findFirst();
    }

    public int getBodiesSize() {
        return realm.where(ThirtyShineResponseBody.class).findAll().size();
    }

    public List<ThirtyShineResponseBody> selectAllBodies() {
        return realm.where(ThirtyShineResponseBody.class).findAll();
    }
    public void deleteObjectFromRealm(RealmObject realmObject) {
        realm.beginTransaction();
        realmObject.deleteFromRealm();
        realm.commitTransaction();
    }

    public static void init(Context context) {

        instance = new DbContext(context);
    }
}
