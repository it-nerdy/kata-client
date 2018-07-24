package com.carfax.service.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client to test the Kata REST Service.
 * 
 * @author Mallikarjun Nuti
 *
 */
public class ServiceClient {
	private static final Logger logger = LoggerFactory.getLogger(ServiceClient.class);

	/**
	 * Main method to start the REST service client. Pass input file path to
	 * invoke the REST service.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 2) {
			// if parameters are not passed show the usage
			System.out.println("Usage client.jar <path of the input file> <service url - like http://localhost:8080>");
		} else {
			String url = args[1] + "/uploadFile";
			File inputFile = new File(args[0]);
			if (!inputFile.isDirectory() && inputFile.isFile()) {
				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream(inputFile);
					HttpClient httpclient = HttpClientBuilder.create().build();

					HttpPost httppost = new HttpPost(url);
					MultipartEntityBuilder builder = MultipartEntityBuilder.create();

					builder.addPart("file", new InputStreamBody(fileInputStream, inputFile.getName()));
					httppost.setEntity(builder.build());
					// invoke the service with given input file
					HttpResponse response = httpclient.execute(httppost);

					int statusCode = response.getStatusLine().getStatusCode();
					HttpEntity responseEntity = response.getEntity();
					String responseString = EntityUtils.toString(responseEntity, "UTF-8");

					logger.info(responseString);

				} catch (ClientProtocolException e) {
					logger.error("Exception in makeing connection to service", e);
				} catch (IOException e) {
					logger.error("Excetion in uploading file", e);
				} finally {
					try {
						if (fileInputStream != null) {
							fileInputStream.close();
						}
					} catch (IOException e) {
						logger.error("Issue in closing the file", e);
					}
				}

			} else {
				logger.info("Please enter a valid file path");
			}
		}
	}
}
