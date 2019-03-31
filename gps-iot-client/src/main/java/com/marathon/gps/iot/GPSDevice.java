package com.marathon.gps.iot;

import com.marathon.gps.iot.util.AliyunIoTSignUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * IoTDemo
 *
 * 高级版 -产品- 物模型，属性上报
 * 教程文档 https://www.yuque.com/wongxming/iot-tech/gdrzpm
 */

public class GPSDevice {

    public static String productKey = "a1lOqjk6bWN";
    public static String deviceName = "laUIath7sswjX1sNfGUi";
    public static String deviceSecret = "CUkSzWDySoFjGNwISxdwpMZZSUyHFOPJ";
    public static String regionId = "cn-shanghai";


    private static MqttClient mqttClient;
    private static Random random = new Random();
    private static DecimalFormat df = new DecimalFormat("0.00000#");

    public static void main(String [] args) {

        mqttClient = initAliyunIoTClient();

        ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setNameFormat("thread-runner-%d").build());

       //scheduledThreadPool.scheduleAtFixedRate(()->postDeviceProperties(), 10,10, TimeUnit.SECONDS);

    }

    private static MqttClient initAliyunIoTClient() {

        try {
            String clientId = "java" + System.currentTimeMillis();

            Map<String, String> params = new HashMap<>(16);
            params.put("productKey", productKey);
            params.put("deviceName", deviceName);
            params.put("clientId", clientId);
            String timestamp = String.valueOf(System.currentTimeMillis());
            params.put("timestamp", timestamp);

            // cn-shanghai
            String targetServer = "tcp://" + productKey + ".iot-as-mqtt."+regionId+".aliyuncs.com:1883";

            String mqttclientId = clientId + "|securemode=3,signmethod=hmacsha1,timestamp=" + timestamp + "|";
            String mqttUsername = deviceName + "&" + productKey;
            String mqttPassword = AliyunIoTSignUtil.sign(params, deviceSecret, "hmacsha1");

            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient client = new MqttClient(targetServer, mqttclientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // MQTT 3.1.1
            connOpts.setMqttVersion(4);
            connOpts.setAutomaticReconnect(false);
            connOpts.setCleanSession(true);

            connOpts.setUserName(mqttUsername);
            connOpts.setPassword(mqttPassword.toCharArray());
            connOpts.setKeepAliveInterval(60);

            client.connect(connOpts);

            return client;
        } catch (Exception e) {
            System.out.println("initAliyunIoTClient error " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    private static void postDeviceProperties() {
        //物模型-属性上报topic
        String pubTopic = "/sys/" + productKey + "/" + deviceName + "/thing/event/property/post";
        //高级版 物模型-属性上报payload
        String payloadJson =
                "{" +
                        "    \"id\": %s," +
                        "    \"params\": {" +
                        "        \"longitude\": %s," +
                        "        \"latitude\": %s" +
                        "    }," +
                        "    \"method\": \"thing.event.property.post\"" +
                        "}";

        try {
            //上报数据
            String payload = String.format(payloadJson, System.currentTimeMillis(),"120.136539", "30.268076");

            MqttMessage message = new MqttMessage(payload.getBytes("utf-8"));
            message.setQos(1);
            mqttClient.publish(pubTopic, message);

            System.out.println("post :"+payload);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
