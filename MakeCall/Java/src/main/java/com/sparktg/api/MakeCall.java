package com.sparktg.api;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MakeCall {
    private static final String PROP_FILE = "/config.properties";

    public static void main(String[] args) throws Exception {
        InputStream is = MakeCall.class.getResourceAsStream(PROP_FILE);
        Properties prop = new Properties();
        prop.load(is);
        String url = prop.getProperty("url");
        String user = prop.getProperty("userName");
        String password = prop.getProperty("password");
        String number=prop.getProperty("number");
        is.close();
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        byte[] authEncBytes = Base64.encodeBase64((user+":"+password).getBytes(Charset.forName("ISO-8859-1")));
        post.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + new String(authEncBytes));
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("number", number));
        post.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = client.execute(post);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }
}
