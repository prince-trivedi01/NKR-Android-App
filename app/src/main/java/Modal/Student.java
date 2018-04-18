package Modal;

/**
 * Created by Prince on 18-04-2018.
 */

public class Student {

    private String std_id;
    private String std_userID;
    private String std_name;
    private String std_branch;
    private String std_password;
    private int std_attendance;


    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getStd_userID(){
        return std_userID;
    }

    public void setStd_userID(String std_userID){
        this.std_userID=std_userID;
    }

    public String getStd_name(){
        return std_name;
    }

    public void  setStd_name(String std_name){
        this.std_name=std_name;
    }

    public String getStd_branch(){
        return std_branch;
    }

    public void setStd_branch(String std_branch){
        this.std_branch=std_branch;
    }

    public String getStd_password(){
        return std_password;
    }

    public void setStd_password(String std_branch){
        this.std_password=std_password;
    }

    public int getStd_attendance(){
        return std_attendance;
    }

    public void setStd_attendance(int std_attendance){
        this.std_attendance=std_attendance;
    }


}
