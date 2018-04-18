package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Modal.Student;

/**
 * Created by Prince on 18-04-2018.
 */

public class StudentDB extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Students.db";

    // Student table name
    private static final String TABLE_StudentInfo = "StudentsRecords";

    // Student Table Columns names
    private static final String COLUMN_Student_ID = "student_id";
    private static final String COLUMN_Student_NAME = "student_name";
    private static final String COLUMN_Student_PASSWORD = "student_password";
    private static final String COLUMN_Student_Branch = "student_branch";
    private static final String COLUMN_Student_UserID = "student_userId";

    private static final String COLUMN_Student_ATTEND = "student_attendance";


    // create table sql query
    private String CREATE_StudentRecords = "CREATE TABLE " + TABLE_StudentInfo + "("
            + COLUMN_Student_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_Student_NAME + " TEXT," + COLUMN_Student_UserID + " TEXT," +
            COLUMN_Student_Branch + " TEXT," + COLUMN_Student_PASSWORD + " TEXT," + COLUMN_Student_ATTEND + " INTEGER" +
            ")";

    // drop table sql query
    private String DROP_Student_TABLE = "DROP TABLE IF EXIST " + TABLE_StudentInfo;

    public StudentDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //
        //  db.execSQL(DROP_Student_TABLE);
        db.execSQL(CREATE_StudentRecords);


        System.out.print("Table Successfully Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //  if(newVersion > oldVersion){
        //    db.execSQL("ALTER TABLE StudentsRecords ADD COLUMN student_branch TEXT");
        //  db.execSQL("ALTER TABLE StudentsRecords ADD COLUMN student_userId TEXT");
        // }

        //Drop Student Table if exist
        db.execSQL(DROP_Student_TABLE);

        // Create tables again
        onCreate(db);


    }




    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Student_NAME, student.getStd_name());
        values.put(COLUMN_Student_UserID,student.getStd_userID());
        values.put(COLUMN_Student_Branch, student.getStd_branch());
        values.put(COLUMN_Student_PASSWORD, student.getStd_password());

        // Inserting Row
        db.insert(TABLE_StudentInfo, null, values);
       // System.out.println(getAllStudent());//add new
        db.close();
    }


    public List<Student> getAllStudent() {
        // array of columns to fetch
        String[] columns = {

                COLUMN_Student_NAME,
                COLUMN_Student_UserID,
                COLUMN_Student_Branch,

        };
        // sorting orders
        String sortOrder =
                COLUMN_Student_NAME + " ASC";
        List<Student> StudentList = new ArrayList<Student>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the Student table
        /**
         * Here query function is used to fetch records from Student table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Student_id,Student_name,Student_email,Student_password FROM Student ORDER BY Student_name;
         */
        Cursor cursor = db.query(TABLE_StudentInfo, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();

                student.setStd_name(cursor.getString(cursor.getColumnIndex(COLUMN_Student_NAME)));
                student.setStd_userID(cursor.getString(cursor.getColumnIndex(COLUMN_Student_UserID)));
                student.setStd_branch(cursor.getString(cursor.getColumnIndex(COLUMN_Student_Branch)));

                // Adding Student record to list
                StudentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return Student list
        return StudentList;
    }



    public void updateStudent(Student Student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Student_NAME, Student.getStd_name());
        values.put(COLUMN_Student_UserID,Student.getStd_userID());
        values.put(COLUMN_Student_Branch, Student.getStd_branch());
        values.put(COLUMN_Student_PASSWORD, Student.getStd_password());

        // updating row
        db.update(TABLE_StudentInfo, values, COLUMN_Student_UserID + " = ?",
                new String[]{String.valueOf(Student.getStd_userID())});
        db.close();
    }

    /**
     * This method is to delete Student record
     *
     * @param student
     */
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete Student record by id
        db.delete(TABLE_StudentInfo, COLUMN_Student_UserID + " = ?",
                new String[]{String.valueOf(student.getStd_userID())});
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
        db.execSQL("TRUNCATE table" + TABLE_StudentInfo);
        db.close();
    }




    /**
     * This method to check Student exist or not
     *
     * @param UserID
     * @return true/false
     */
    public boolean checkStudent(String UserID) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_Student_UserID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_Student_UserID + " = ?";

        // selection argument
        String[] selectionArgs = {UserID};

        // query Student table with condition
        /**
         * Here query function is used to fetch records from Student table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Student_id FROM Student WHERE Student_UserID = 'jack';
         */
        Cursor cursor = db.query(TABLE_StudentInfo, //Table to query
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
     * This method to check Student exist or not
     *
     * @param UserID
     * @param password
     * @return true/false
     */
    public boolean checkStudent(String UserID, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_Student_UserID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_Student_UserID + " = ?" + " AND " + COLUMN_Student_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {UserID, password};

        // query Student table with conditions
        /**
         * Here query function is used to fetch records from Student table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Student_userid FROM Student WHERE Student_UserID = 'jack@androidtutorialshub.com' AND Student_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_StudentInfo, //Table to query
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
            //query where Student=this set =attend=true;

            return true;
        }

        return false;
    }




}
