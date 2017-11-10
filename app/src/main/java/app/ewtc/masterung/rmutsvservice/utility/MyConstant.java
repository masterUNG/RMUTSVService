package app.ewtc.masterung.rmutsvservice.utility;

/**
 * Created by masterung on 8/11/2017 AD.
 */

public class MyConstant {

    private String urlPostData = "http://androidthai.in.th/rmuts/addDataMaster.php";

    private String urlGetAllUser = "http://androidthai.in.th/rmuts/getAllDataMaster.php";

    private String urlDeleteData = "http://androidthai.in.th/rmuts/deleteDataMaster.php";

    private String urlEditData = "http://androidthai.in.th/rmuts/editDataMaster.php";

    public String getUrlEditData() {
        return urlEditData;
    }

    public String getUrlDeleteData() {
        return urlDeleteData;
    }

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    public String getUrlPostData() {
        return urlPostData;
    }
}   // Main Class
