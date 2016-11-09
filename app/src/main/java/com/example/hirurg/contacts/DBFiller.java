package com.example.hirurg.contacts;

import android.content.Context;

/**
 * Created by hirurg on 09.11.16.
 */

public class DBFiller {
    public static void fillDB(Context ctx){
        DB db = new DB(ctx);
        db.open();

        db.addOrUpd(false
                , "Jora"
                , "Torbu"
                , "+380672361748"
                , "jorik@mail.md"
                , "05.03.1982"
                , "fb"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Moyshe"
                , "Shniperson"
                , "+380672361749"
                , "moyshik@mail.il"
                , "15.08.1981"
                , "fb1"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Vova"
                , "Zadov"
                , "+380672361747"
                , "vovik@mail.ru"
                , "25.06.1980"
                , "fb2"
                , null
                , 0
                , 0);

        db.close();
    }
}
