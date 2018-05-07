package cristian_sedano.androideatserver.Common;

import cristian_sedano.androideatserver.Model.User;

/**
 * Created by Christian on 26/03/2018.
 */

public class Common {

    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;
    public static String converCodeToStatus(String code)
    {
        if (code.equals("O"))
            return "Placed";
        else if (code.equals("O"))
                return "On my way";
        else
            return "Shipped";
    }

}
