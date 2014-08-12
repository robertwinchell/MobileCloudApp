

package com.intellio.tesa.core;

/**
 * Bootstrap constants
 */
public final class Constants {
    private Constants() {}

    public static final class Auth {
        private Auth() {}

        /**
         * Account type id
         */
        public static final String BOOTSTRAP_ACCOUNT_TYPE = "com.intellio.tesa";

        /**
         * Account name
         */
        public static final String BOOTSTRAP_ACCOUNT_NAME = "Tesa";

        /**
         * Provider id
         */
        public static final String BOOTSTRAP_PROVIDER_AUTHORITY = "com.intellio.tesa.sync";

        /**
         * Auth token type
         */
        public static final String AUTHTOKEN_TYPE = BOOTSTRAP_ACCOUNT_TYPE;
    }

    /**
     * All HTTP is done through a REST style API built for demonstration purposes on Parse.com
     * Thanks to the nice people at Parse for creating such a nice system for us to use for listmenu!
     */
    public static final class Http {


        public static final String URL_PRODUCTS_DELETE_FRAG = "/products/{name}.json";
        public static final String URL_PRODUCTS_UPDATE_FRAG = "/products/{id}.json";
        private Http() {}


        /**
         * Base URL for all requests
         */
        public static final String URL_BASE = "http://beta.zaruku.cz/app_dev.php/api";


        /**
         * Authentication URL
         */
        public static final String URL_AUTH_FRAG = "/1/login";
        public static final String URL_AUTH = URL_BASE + URL_AUTH_FRAG;

        /**
         * List Users URL
         */
        public static final String URL_USERS_FRAG =  "/1/users";
        public static final String URL_USERS = URL_BASE + URL_USERS_FRAG;

        /**
         * Url for registration
         * */
        public static final String URl_USER_REGISTRATION="";
        /**
         * List Products URL
         * */
        public static final String  URL_PRODUCTS_FRAG="/products.json";

        public static final String URL_PRODUCTS=URL_BASE+URL_PRODUCTS_FRAG;




        /**
         * PARAMS for auth
         */
        public static final String PARAM_USERNAME = "username";
        public static final String PARAM_PASSWORD = "password";


        public static final String PARSE_APP_ID = "zHb2bVia6kgilYRWWdmTiEJooYA17NnkBSUVsr4H";
        public static final String PARSE_REST_API_KEY = "N2kCY1T3t3Jfhf9zpJ5MCURn3b25UpACILhnf5u9";
        public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
        public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
        public static final String CONTENT_TYPE_JSON = "application/json";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String SESSION_TOKEN = "sessionToken";


    }


    public static final class Extra {
        private Extra() {}

        public static final String PRODUCT_ITEM = "product_item";

        public static final String USER = "user";
        public static final String IMAGES = "com.intellio.tesa.imageloader.IMAGES";
        public static final String IMAGE_POSITION = "com.intellio.tesa.imageloader.IMAGE_POSITION";
    }

    public static final class Intent {
        private Intent() {}

        /**
         * Action prefix for all intents created
         */
        public static final String INTENT_PREFIX = "com.intellio.tesa.";

    }

    public static class Notification {
        private Notification() {
        }

        public static final int TIMER_NOTIFICATION_ID = 1000; // Why 1000? Why not? :)
    }

}


