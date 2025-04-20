package com.example.uccmobileapp.faculty

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.uccmobileapp.misc.Title

// SQLite helper class for managing faculty members' data in the database
class FacultyMembersDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Database name and version
        private const val DATABASE_NAME = "uccMobileApp_facultyMembers.db"
        private const val DATABASE_VERSION = 3

        // Table name and column names
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

    // Called when the database is first created
    override fun onCreate(db: SQLiteDatabase?) {
        // SQL query to create the facultyMembers table
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY, 
            $COLUMN_TITLE TEXT, 
            $COLUMN_FIRST TEXT, 
            $COLUMN_LAST TEXT, 
            $COLUMN_EMAIL TEXT, 
            $COLUMN_TELE TEXT,
            $COLUMN_ROLE TEXT,
            $COLUMN_ABOUT TEXT)""" // Potentially could add PHOTO column (currently commented out)
        db?.execSQL(createTableQuery)  // Execute the create table query
    }

    // Called when the database version is upgraded
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drops the existing table and recreate it
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db) // Recreate the table after dropping it
    }

    // Checks whether the faculty members table is empty
    fun isFacultyTableEmpty(): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null) // Get the count of rows in the table
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count == 0 // Returns true if the table is empty
    }

    // Inserts a new faculty member into the database
    fun insertFacultyMembers(facultyMember: FacultyMember): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, facultyMember.title.toString())  // Store title as string
            put(COLUMN_FIRST, facultyMember.first)
            put(COLUMN_LAST, facultyMember.last)
            put(COLUMN_EMAIL, facultyMember.email)  // Email stored as string
            put(COLUMN_TELE, facultyMember.tele)
            put(COLUMN_ROLE, facultyMember.role)
            put(COLUMN_ABOUT, facultyMember.about)
        }
        // Inserts the new faculty member and return the inserted row's ID
        return db.insert(TABLE_NAME, null, values)
    }

    // Retrieves all faculty members from the database and returns them as a list
    fun getAllFacultyMembers(): List<FacultyMember> {
        val facultyMembers = mutableListOf<FacultyMember>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null) // Query to get all rows from the table

        if (cursor.moveToFirst()) {
            do {
                // Retrieves data from the cursor for each column
                val titleString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val title = Title.valueOf(titleString.uppercase()) // Convert the title to the Title enum
                val first = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST))
                val last = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
                val tele = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELE))
                val role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                val about = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ABOUT))

                // Adds a new FacultyMember object to the list
                facultyMembers.add(FacultyMember(title, first, last, email, tele, role, about))
            } while (cursor.moveToNext())  // Move to the next row
        }
        cursor.close() // Closes the cursor after usage
        return facultyMembers // Returns the list of faculty members
    }
}
