package com.example.bitzone;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.bitzone.DatabaseSchemas.KlassTable.Cols;

import java.util.UUID;


public class KlassCursorWrapper extends CursorWrapper {

    public KlassCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Klass getKlass(){

        String name=getString(getColumnIndex(Cols.KLASS_NAME));
        String uuid=getString(getColumnIndex(Cols.ID));
        int nos=getInt(getColumnIndex(Cols.NUM_OF_STUDENTS));

        return new Klass(name,nos, UUID.fromString(uuid));
    }
}

