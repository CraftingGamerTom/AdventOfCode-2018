package com.craftinggamertom.advent_of_code_2018.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Class to read input from the source
 * 
 * @author tcrokicki
 *
 */
public class InputReader {

	final static Logger logger = Logger.getLogger(InputReader.class);

	private static final String COOKIE_VALUE_1 = "";
	private static final String COOKIE_VALUE_2 = "";
	private static final String COOKIE_VALUE_3 = "";

	public static BufferedReader getInputAsBufferedReader(int year, int day) throws IOException {
		try {

			HttpClientContext localContext = HttpClientContext.create();
			localContext.setCookieStore(getCookies());
			HttpGet challengeInputGET = new HttpGet("https://adventofcode.com/" + year + "/day/" + day + "/input");

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(challengeInputGET, localContext);
			// logger.debug(EntityUtils.toString(response.getEntity()));

			// Read input
			BufferedReader input = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			return input;
		} catch (IOException e) {
			logger.error("Error getting input.");
			throw e;
		}
	}

	private static BasicCookieStore getCookies() {
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie cookie1 = new BasicClientCookie("_ga", "GA1.2.284930251.1543864063");
		cookie1.setDomain(".adventofcode.com");
		cookie1.setPath("/");
		cookieStore.addCookie(cookie1);

		BasicClientCookie cookie2 = new BasicClientCookie("_gid", "GA1.2.135072629.1544158994");
		cookie2.setDomain(".adventofcode.com");
		cookie2.setPath("/");
		cookieStore.addCookie(cookie2);

		BasicClientCookie cookie3 = new BasicClientCookie("session",
				"53616c7465645f5f184cb9eb8cedbd1e42b23658bf62c19c95ec0da2d8a6b82a3c9005aa9f0731aab7d26b8d410f9c7b");
		cookie3.setDomain(".adventofcode.com");
		cookie3.setPath("/");
		cookieStore.addCookie(cookie3);
		return cookieStore;
	}
}
