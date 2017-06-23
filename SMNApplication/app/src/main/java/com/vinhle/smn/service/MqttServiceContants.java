package com.vinhle.smn.service;

/**
 * Created by VinhLe on 6/22/2017.
 */

public final class MqttServiceContants {

    /**
     * Default QOS value
     */
    public static final int defaultQos = 1;
    /**
     * Default timeout
     */
    public static final int defaultTimeOut = 1000;
    /**
     * Default keep alive value
     */
    public static final int defaultKeepAlive = 10;
    /**
     * Default SSL enabled flag
     */
    public static final boolean defaultSsl = false;
    /**
     * Default message retained flag
     */
    public static final boolean defaultRetained = false;
    /**
     * Default port
     */
    public static final int defaultPort = 1883;

    /**
     * Advanced Connect Request Code
     **/
    public static final int advancedConnect = 1;
    /**
     * Last will Request Code
     **/
    public static final int lastWill = 2;
    /**
     * Show History Request Code
     **/
    public static final int showHistory = 3;

    /* Bundle Keys */

    public static final String subscribe = "subscribe";

    public static final String unsubscribe = "unsubscribe";

    public static final String connect = "connect";

    public static final String disconnect = "disconnect";

    public static final String publish = "publish";
    /**
     * Server Bundle Key
     **/
    public static final String server = "server";
    /**
     * Port Bundle Key
     **/
    public static final String port = "port";
    /**
     * ClientID Bundle Key
     **/
    public static final String clientId = "clientId";
    /**
     * Topic Bundle Key
     **/
    public static final String topic = "topic";
    /**
     * History Bundle Key
     **/
    public static final String history = "history";
    /**
     * Message Bundle Key
     **/
    public static final String message = "message";
    /**
     * Retained Flag Bundle Key
     **/
    public static final String retained = "retained";
    /**
     * QOS Value Bundle Key
     **/
    public static final String qos = "qos";
    /**
     * User name Bundle Key
     **/
    public static final String username = "username";
    /**
     * Password Bundle Key
     **/
    public static final String password = "password";
    /**
     * Keep Alive value Bundle Key
     **/
    public static final String keepalive = "keepalive";
    /**
     * Timeout Bundle Key
     **/
    public static final String timeout = "timeout";
    /**
     * SSL Enabled Flag Bundle Key
     **/
    public static final String ssl = "ssl";
    /**
     * SSL Key File Bundle Key
     **/
    public static final String ssl_key = "ssl_key";
    /**
     * Clean Session Flag Bundle Key
     **/
    public static final String cleanSession = "cleanSession";
    /**
     * Action Bundle Key
     **/
    public static final String action = "action";

    /**
     * Space String Literal
     **/
    public  static final String space = " ";
    /**
     * Empty String for comparisons
     **/
    public static final String empty = new String();

}
