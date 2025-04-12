package com.example.uccmobileapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FacultyMembersDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "uccMobileApp.db"
        private const val DATABASE_VERSION = 3

        private const val TABLE_NAME = "facultyMembers"

        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_FIRST = "first"
        private const val COLUMN_LAST = "last"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_TELE = "tele"
        private const val COLUMN_PHOTO = "photo"
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
            $COLUMN_PHOTO BLOB)"""
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}