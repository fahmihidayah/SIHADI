package com.framework.rest_clients;

import com.example.beans.DataSingleton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class WebRestClient {
	// ini diisi alamat ip/simple_chat_room/api/
	private static final String BASE_URL = "http://";
	// /sistem_pakar_chat/api/
//	private static final String PATH_URL = "/simple_chat_room/api/";
	private static final String PATH_URL = "/sistem_pakar_chat/api/";
	

	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL+ DataSingleton.getInstance().getServerAddress() + PATH_URL + relativeUrl;
	}
}
