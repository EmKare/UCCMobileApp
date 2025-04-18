package com.example.uccmobileapp.faculty

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.uccmobileapp.misc.Title

class FacultyMembersDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "uccMobileApp_facultyMembers.db"
        private const val DATABASE_VERSION = 3

        private const val TABLE_NAME = "facultyMembers"

        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_FIRST = "first"
        private const val COLUMN_LAST = "last"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_TELE = "tele"
        private const val COLUMN_ROLE = "role"
        private const val COLUMN_ABOUT = "about"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY, 
            $COLUMN_TITLE TEXT, 
            $COLUMN_FIRST TEXT, 
            $COLUMN_LAST TEXT, 
            $COLUMN_EMAIL TEXT, 
            $COLUMN_TELE TEXT,
            $COLUMN_ROLE TEXT,
            $COLUMN_ABOUT TEXT)"""//,
            //$COLUMN_PHOTO BLOB)"""
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun isFacultyTableEmpty(): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count == 0
    }

    fun insertFacultyMembers(facultyMember: FacultyMember): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, facultyMember.title.toString())
            put(COLUMN_FIRST, facultyMember.first)
            put(COLUMN_LAST, facultyMember.last)
            put(COLUMN_EMAIL, facultyMember.email)  // stored as comma-separated string
            put(COLUMN_TELE, facultyMember.tele)
            put(COLUMN_ROLE, facultyMember.role)
            put(COLUMN_ABOUT, facultyMember.about)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllFacultyMembers(): List<FacultyMember> {
        val facultyMember = mutableListOf<FacultyMember>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val titleString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val title = Title.valueOf(titleString.uppercase())
                val first = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST))
                val last = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
                val tele = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELE))
                val role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                val about = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ABOUT))

                facultyMember.add(FacultyMember(title, first, last, email, tele, role, about))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return facultyMember
    }
}