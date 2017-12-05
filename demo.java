package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class demo {
	public static void main(String[] args) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
		org.apache.http.HttpHost proxy = new org.apache.http.HttpHost(
				"218.14.121.229", 8080, "http");
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(2000).setConnectTimeout(1000 * 10)
				.setProxy(proxy).build();// ��������ʹ��䳬ʱʱ��
		httpGet.setConfig(requestConfig);
		try {
			HttpResponse response = client.execute(httpGet);
			String content = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			Document document = Jsoup.parse(content);
			Element elementById = document.getElementById("content");
			Elements tag = document.getElementsByTag("a");
			Elements select = document.select("img[src$=.png]");
			Element first = document.select("div.masthead").first();
			for (Element link : tag) {
				String attr = link.attr("href");
				String text = link.text();
				System.out.println(attr);
				System.out.println(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void text3() {
		try {
			File dest = new File("C:\\Users\\Administrator\\Desktop\\a.html");
			// �����ֽ�������
			InputStream is;
			// �ֽ������
			FileOutputStream fos = new FileOutputStream(dest);
			URL temp = new URL("https://www.baidu.com");
			URLConnection connection = temp.openConnection();
			connection
					.addRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
			is = temp.openStream();
			// Ϊ�ֽ��������ӻ���
			BufferedInputStream bis = new BufferedInputStream(is);
			// Ϊ�ֽ�������ӻ���
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			int length;

			byte[] bytes = new byte[1024 * 20];
			while ((length = bis.read(bytes, 0, bytes.length)) != -1) {
				fos.write(bytes, 0, length);
			}

			bos.close();
			fos.close();
			bis.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TEST1();
	// test2();

	private static void test2() throws IOException {
		Document document = Jsoup.connect("http://jw6.csmzxy.com:8088/").get();
		FileWriter fw = new FileWriter("b.txt");
		fw.write(document.toString());
		fw.close();
		System.out.println(1);
		Element elementById = document.getElementById("content");
		Elements tag = document.getElementsByTag("a");
		Elements select = document.select("img[src$=.png]");
		Element first = document.select("div.masthead").first();
		for (Element link : tag) {
			String attr = link.attr("href");
			String text = link.text();
			System.out.println(attr);
			System.out.println(text);
		}
	}

	private static void TEST1() {
		// ���弴�����ʵ�����
		String url = "http://www.baidu.com"; // ����һ���ַ��������洢��ҳ����
		String result = ""; // ����һ�������ַ�������
		BufferedReader in = null;
		try { // ��stringת��url����
			URL realUrl = new URL(url); // ��ʼ��һ�����ӵ��Ǹ�url������
			URLConnection connection = realUrl.openConnection(); // ��ʼʵ�ʵ�����
			connection.connect(); // ��ʼ�� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			// ������ʱ�洢ץȡ����ÿһ�е�����
			String line;
			while ((line = in.readLine()) != null) {
				// ����ץȡ����ÿһ�в�����洢��result����
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally���ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(result);
	}
}