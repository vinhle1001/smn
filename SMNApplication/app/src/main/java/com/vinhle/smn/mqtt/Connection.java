package com.vinhle.smn.mqtt;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 6/20/2017.
 */

public class Connection {

    /**
     * The clientId of the client associated with this <code>Connection</code> object
     **/
    private String clientId = null;
    /**
     * The host that the {@link MqttAndroidClient} represented by this <code>Connection</code> is represented by
     **/
    private String host = null;
    /**
     * The port on the server this client is connecting to
     **/
    private int port = 0;
    /**
     * The {@link Context} of the application this object is part of
     **/
    private Context context = null;
    /**
     * {@link ConnectionStatus} of the {@link MqttAndroidClient} represented by this <code>Connection</code> object. Default value is {@link ConnectionStatus#NONE}
     **/
    private ConnectionStatus status = ConnectionStatus.NONE;

    /**
     * The {@link MqttAndroidClient} instance this class represents
     **/
    private MqttAndroidClient client = null;
    /**
     * The {@link MqttConnectOptions} that were used to connect this client
     **/
    private MqttConnectOptions conOpt;

    private List<String> topics;

    /**
     * True if this connection is secured using SSL
     **/
    private boolean sslConnection = Boolean.FALSE;

    /**
     * Connections status for  a connection
     */
    enum ConnectionStatus {

        /**
         * Client is Connecting
         **/
        CONNECTING,
        /**
         * Client is Connected
         **/
        CONNECTED,
        /**
         * Client is Disconnecting
         **/
        DISCONNECTING,
        /**
         * Client is Disconnected
         **/
        DISCONNECTED,
        /**
         * Client has encountered an Error
         **/
        ERROR,
        /**
         * Status is unknown
         **/
        NONE
    }

    /**
     * Creates a connection from persisted information in the database store, attempting
     * to create a {@link MqttAndroidClient} and the client handle.
     *
     * @param clientId      The id of the client
     * @param host          the server which the client is connecting to
     * @param port          the port on the server which the client will attempt to connect to
     * @param context       the application context
     * @param sslConnection true if the connection is secured by SSL
     * @return a new instance of <code>Connection</code>
     */
    public static Connection createConnection(String clientId, String host, int port, Context context, boolean sslConnection) {
        String uri = null;
        if (sslConnection) {
            uri = "ssl://" + host + ":" + port;
        } else {
            uri = "tcp://" + host + ":" + port;
        }
        MqttAndroidClient client = new MqttAndroidClient(context, uri, clientId);
        return new Connection(clientId, host, port, context, client, sslConnection);
    }

    /**
     * Creates a connection object with the server information and the client
     * hand which is the reference used to pass the client around activities
     *
     * @param clientId      The Id of the client
     * @param host          The server which the client is connecting to
     * @param port          The port on the server which the client will attempt to connect to
     * @param context       The application context
     * @param client        The MqttAndroidClient which communicates with the service for this connection
     * @param sslConnection true if the connection is secured by SSL
     */
    public Connection(String clientId, String host, int port, Context context, MqttAndroidClient client, boolean sslConnection) {
        //generate the client handle from its hash code
        this.clientId = clientId;
        this.host = host;
        this.port = port;
        this.context = context;
        this.client = client;
        this.sslConnection = sslConnection;
        this.topics = new ArrayList<>();
    }

    /**
     * Determines if the client is connected
     *
     * @return is the client connected
     */
    public boolean isConnected() {
        return client != null && client.isConnected();
    }

    /**
     * Determines if the client is in a state of connecting or connected.
     *
     * @return if the client is connecting or connected
     */
    public boolean isConnectedOrConnecting() {
        return (status == ConnectionStatus.CONNECTED) || (status == ConnectionStatus.CONNECTING);
    }

    /**
     * Client is currently not in an error state
     *
     * @return true if the client is in not an error state
     */
    public boolean noError() {
        return status != ConnectionStatus.ERROR;
    }

    /**
     * Gets the client which communicates with the android service.
     *
     * @return the client which communicates with the android service
     */
    public MqttAndroidClient getClient() {
        return client;
    }

    /**
     * Add the connectOptions used to connect the client to the server
     *
     * @param connectOptions the connectOptions used to connect to the server
     */
    public void addConnectionOptions(MqttConnectOptions connectOptions) {
        conOpt = connectOptions;
    }

    /**
     * Get the connectOptions used to connect this client to the server
     *
     * @return The connectOptions used to connect the client to the server
     */
    public MqttConnectOptions getConnectionOptions() {
        return conOpt;
    }

    /**
     * Subscribe to a topic that the user has specified
     */
    public void subscribe(String topic) {
        if (!isConnected())
            return;

        try {
            client.subscribe(topic, ActivityConstants.defaultQos);

            topics.add(topic);
        } catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), e.getMessage(), e);
        }
    }

    /**
     * Set MqttCallback
     */
    public void setCallback(MqttCallback callback) {
        if (client == null || callback == null) return;

        client.setCallback(callback);
    }

    /**
     * UnSubscribe to a topic that the user has specified
     */
    public void unsubscribe(String topic) {
        if (!isConnected())
            return;

        try {
            client.unsubscribe(topic);

            topics.remove(topic);
        } catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), e.getMessage(), e);
        }
    }

    /**
     * Connect the client
     */
    public void connect() {
        if (client == null)
            return;

        try {
            client.connect();
        } catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to connect the client with the handle " + e.getMessage(), e);
        }
    }

    /**
     * Disconnect the client
     */
    public void disconnect() {
        //if the client is not connected, process the disconnect
        if (!isConnected()) {
            return;
        }

        try {
            topics.clear();
            client.disconnect();
        } catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to disconnect the client with the handle " + e.getMessage(), e);
        }

    }
}
