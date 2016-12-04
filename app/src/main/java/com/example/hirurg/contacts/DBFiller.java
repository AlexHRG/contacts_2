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

        db.addOrUpd(false
                , "Iosik"
                , "Aisberg"
                , "+380672361601"
                , "iosik@mail.il"
                , "15.02.1981"
                , "fb3"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Ion"
                , "Hinku"
                , "+380672361602"
                , "ionel@mail.md"
                , "16.08.1980"
                , "fb4"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Vasilisa"
                , "Kirilova"
                , "+380672361603"
                , "vovik@mail.ru"
                , "25.07.1984"
                , "fb5"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Slava"
                , "Bogu"
                , "+380672361604"
                , "slavik@mail.md"
                , "01.03.1978"
                , "fb6"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Abraham"
                , "Doberman"
                , "+380672361605"
                , "abrasha@mail.il"
                , "15.10.1981"
                , "fb7"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Masha"
                , "Belova"
                , "+380672361606"
                , "masha@mail.ru"
                , "25.01.1980"
                , "fb11"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Sarah"
                , "Feldman"
                , "+380672361607"
                , "sarah@mail.il"
                , "16.05.1981"
                , "fb8"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Tudor"
                , "Hovesku"
                , "+380672361608"
                , "tudor@mail.md"
                , "16.09.1979"
                , "fb9"
                , null
                , 0
                , 0);

        db.addOrUpd(false
                , "Natasha"
                , "Rostova"
                , "+380672361609"
                , "natali@mail.ru"
                , "27.09.1984"
                , "fb10"
                , null
                , 0
                , 0);

        db.close();
    }
}
