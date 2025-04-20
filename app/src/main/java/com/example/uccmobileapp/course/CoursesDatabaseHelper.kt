package com.example.uccmobileapp.course

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// SQLite helper class to manage course-related database operations
class CoursesDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Database name and version
        private const val DATABASE_NAME = "uccMobileApp_Courses.db"
        private const val DATABASE_VERSION = 3

        // Table and column names
        private const val TABLE_NAME = "courses"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CODE = "code"
        private const val COLUMN_CREDITS = "credits"
        private const val COLUMN_PREREQS = "preRequisites"
        private const val COLUMN_DESCRIPTION = "description"
    }

    // Called when the database is first created
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_TITLE TEXT, 
            $COLUMN_CODE TEXT, 
            $COLUMN_CREDITS INT, 
            $COLUMN_PREREQS TEXT, 
            $COLUMN_DESCRIPTION TEXT)"""
        db?.execSQL(createTableQuery)
    }

    // Called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drops the existing table and recreate it
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Checks whether the courses table is empty
    fun isCoursesTableEmpty(): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count == 0
    }

    // Inserts a new course into the database
    fun insertCourse(course: Course): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CODE, course.code)
            put(COLUMN_TITLE, course.title)
            put(COLUMN_CREDITS, course.credits)
            put(COLUMN_PREREQS, course.preReqs.joinToString(",")) // Join prerequisites into a single comma-separated string
            put(COLUMN_DESCRIPTION, course.description)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    // Retrieves all courses from the database and returns them as a list
    fun getAllCourses(): List<Course> {
        val courses = mutableListOf<Course>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val code = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CODE))
                val credits = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CREDITS))
                val prerequisitesString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PREREQS))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))

                // Converts comma-separated prerequisites string back to a list
                val prerequisites = if (prerequisitesString.isNullOrEmpty())
                    emptyList()
                else
                    prerequisitesString.split(",")

                // Creates Course object and add it to the list
                courses.add(Course(code, title, credits, prerequisites, description))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return courses
    }
}
