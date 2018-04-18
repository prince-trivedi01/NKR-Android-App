package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Modal.Teacher;
import nkr.collegeproject.com.nkr.LectureDetails;

/**
 * Created by Prince on 18-04-2018.
 */

public class TeacherDB extends SQLiteOpenHelper {




    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Teacher.db";

    // Teacher table name
    private static final String TABLE_TeacherInfo = "TeacherRecords";

    // Teacher Table Columns names
    private static final String COLUMN_Teacher_ID = "Teacher_id";
    private static final String COLUMN_Teacher_NAME = "Teacher_name";
    private static final String COLUMN_Teacher_Branch = "Teacher_branch";
    private static final String COLUMN_Teacher_PASSWORD = "Teacher_password";
    private static final String COLUMN_Teacher_Semester="Teacher_semester";
    private static final String COLUMN_Teacher_Subject="Teacher_subject";
    private static final String COLUMN_Teacher_Time="Teacher_time";
    private static final String COLUMN_Teacher_UserID="Teacher_userId";
    // create table sql query
    private String CREATE_TeacherDetails = "CREATE TABLE " + TABLE_TeacherInfo + "("
            + COLUMN_Teacher_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_Teacher_NAME + " TEXT," + COLUMN_Teacher_UserID + " TEXT,"
            + COLUMN_Teacher_Branch + " TEXT," + COLUMN_Teacher_PASSWORD + " TEXT," + COLUMN_Teacher_Semester + " TEXT,"+
            COLUMN_Teacher_Subject + " TEXT,"+ COLUMN_Teacher_Time + " TEXT" + ")";

    // drop table sql query
    private String DROP_Teacher_TABLE = "DROP TABLE IF EXISTS " + TABLE_TeacherInfo;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TeacherDetails);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_Teacher_TABLE);

        // Create tables again
        onCreate(db);

    }

    public TeacherDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public void addTeacher(Teacher Teacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Teacher_NAME, Teacher.getT_name());
        values.put(COLUMN_Teacher_UserID,Teacher.getT_userID());

        values.put(COLUMN_Teacher_PASSWORD, Teacher.getT_password());
        // values.put(COLUMN_Teacher_Time, String.valueOf(Teacher.getT_time()));

        // Inserting Row
        db.insert(TABLE_TeacherInfo, null, values);
        db.close();
    }

    public void addLectureDetails(Teacher Teacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_Teacher_Branch, Teacher.getT_branch());
        values.put(COLUMN_Teacher_Semester, Teacher.getT_semester());
        values.put(COLUMN_Teacher_Subject, Teacher.getT_subject());

         values.put(COLUMN_Teacher_Time, String.valueOf(Teacher.getT_time()));

        // Inserting Row
        db.insert(TABLE_TeacherInfo, null, values);
        db.close();
    }


    public Boolean addTime(String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

         values.put(COLUMN_Teacher_Time, time);

        // Inserting Row
        db.insert(TABLE_TeacherInfo, null, values);
        db.close();
        return true;
    }



    public void updateTeacher(Teacher Teacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Teacher_NAME, Teacher.getT_name());
        values.put(COLUMN_Teacher_UserID,Teacher.getT_userID());
        values.put(COLUMN_Teacher_Branch, Teacher.getT_branch());
        values.put(COLUMN_Teacher_Semester, Teacher.getT_semester());
        values.put(COLUMN_Teacher_Subject, Teacher.getT_subject());
        values.put(COLUMN_Teacher_PASSWORD, Teacher.getT_password());
        //values.put(COLUMN_Teacher_Time, String.valueOf(Teacher.getT_time()));

        // updating row
        db.update(TABLE_TeacherInfo, values, COLUMN_Teacher_UserID + " = ?",
                new String[]{String.valueOf(Teacher.getT_userID())});
        db.close();
    }

    /**
     * This method is to delete Teacher record
     *
     * @param Teacher
     */
    public void deleteTeacher(Teacher Teacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete Teacher record by id
        db.delete(TABLE_TeacherInfo, COLUMN_Teacher_UserID + " = ?",
                new String[]{String.valueOf(Teacher.getT_userID())});
        db.close();
    }


    public List<Teacher> getAllTeacher() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_Teacher_ID,

                COLUMN_Teacher_NAME,
                COLUMN_Teacher_UserID,
                COLUMN_Teacher_Branch,
                COLUMN_Teacher_Semester,

                COLUMN_Teacher_Subject,

                COLUMN_Teacher_PASSWORD
                //      COLUMN_Teacher_Time
        };
        // sorting orders
        String sortOrder =
                COLUMN_Teacher_NAME + " ASC";
        List<Teacher> TeacherList = new ArrayList<Teacher>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the Teacher table
        /**
         * Here query function is used to fetch records from Teacher table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Teacher_id,Teacher_name,Teacher_email,Teacher_password FROM Teacher ORDER BY Teacher_name;
         */
        Cursor cursor = db.query(TABLE_TeacherInfo, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Teacher Teacher = new Teacher();
                Teacher.setT_name(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_NAME)));
                Teacher.setT_userID(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_UserID)));

                Teacher.setT_branch(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_Branch)));

                Teacher.setT_semester(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_Semester)));
                Teacher.setT_subject(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_Subject)));
                Teacher.setT_password(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_PASSWORD)));
                //    Teacher.setT_time(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_Time)));
                // Adding Teacher record to list
                TeacherList.add(Teacher);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return Teacher list
        return TeacherList;
    }


    /**
     * This method to check Teacher exist or not
     *
     * @param UserID
     * @return true/false
     */
    public boolean checkTeacher(String UserID) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_Teacher_UserID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_Teacher_UserID + " = ?";

        // selection argument
        String[] selectionArgs = {UserID};

        // query Teacher table with condition
        /**
         * Here query function is used to fetch records from Teacher table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Teacher_id FROM Teacher WHERE Teacher_UserID = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_TeacherInfo, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;

    }

    /**
     * This method to check Teacher exist or not
     *
     * @param UserID
     * @param password
     * @return true/false
     */
    public boolean checkTeacher(String UserID, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_Teacher_UserID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_Teacher_UserID + " = ?" + " AND " + COLUMN_Teacher_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {UserID, password};

        // query Teacher table with conditions
        /**
         * Here query function is used to fetch records from Teacher table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Teacher_id FROM Teacher WHERE Teacher_UserID = 'jack@androidtutorialshub.com' AND Teacher_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_TeacherInfo, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            //query where Teacher=this set =attend=true;
            return true;
        }

        return false;
    }
/*
@param UserId

*/



    public Cursor getLectureDetails(){


        // array of columns to fetch
        String[] columns = {


                COLUMN_Teacher_Branch,
                COLUMN_Teacher_Semester,

                COLUMN_Teacher_Subject



        };

        List<Teacher> TeacherList = new ArrayList<Teacher>();

        SQLiteDatabase db = this.getReadableDatabase();


        // selection criteria
        String selection = COLUMN_Teacher_UserID + " = ?";



        /**
         * Here query function is used to fetch records from Teacher table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Teacher_id,Teacher_name,Teacher_email,Teacher_password FROM Teacher ORDER BY Teacher_name;
         */
        Cursor cursor = db.query(TABLE_TeacherInfo, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
             null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Teacher Teacher = new Teacher();


                Teacher.setT_branch(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_Branch)));

                Teacher.setT_semester(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_Semester)));
                Teacher.setT_subject(cursor.getString(cursor.getColumnIndex(COLUMN_Teacher_Subject)));

                // Adding Teacher record to list
                TeacherList.add(Teacher);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return Teacher list
        return cursor;



    }


}
