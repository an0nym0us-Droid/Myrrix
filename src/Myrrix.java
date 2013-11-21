

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.myrrix.common.NotReadyException;
import net.myrrix.online.ServerRecommender;

import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

/**
 * Servlet implementation class Myrrix
 */
@WebServlet("/helloworld")
public class Myrrix extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data = request.getParameter("data");
		System.out.println(data);
		String dataContents[] = data.split(",");
		String id = dataContents[0];
		
		
		
		
		 // process(request, response);'
		response.setContentType("text/html");
	      File ff = new File("/home/crap/myrrix");
	      System.out.println(ff.isDirectory());
	      ServerRecommender recom = new ServerRecommender(ff);
	      while(!recom.isReady()){}
		
	      List<RecommendedItem> ll = null;
	      for(int i=1;i<dataContents.length;i++){
	    	  recom.setPreference(Long.parseLong(id), Long.parseLong(dataContents[i]));
	      }
	      
		try {
			ll = recom.recommend(Long.parseLong(id), 10);
		} catch (NoSuchUserException | NotReadyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      File itemid = new File("/home/crap/Training_Data/itemName.txt");
	      String currentLine;
	      StringBuffer buff = new StringBuffer();
	      StringBuilder build = new StringBuilder();
	      BufferedReader reader = new BufferedReader(new FileReader(itemid));
	      ArrayList<String> items = new ArrayList<String>();
	      while((currentLine=reader.readLine())!=null){
	    	  String contents[] = currentLine.split(",");
	    	  for(RecommendedItem item:ll){
	    		  if(String.valueOf(item.getItemID()).equals(contents[0])){
	    			  build.delete(0, build.length());
	    			  for(int i=1;i<contents.length;i++){
	    				  build.append(contents[i]+",");
	    			  }
	    			  items.add(build.toString());
	    		  }
	    	  }
	      }
	      
//	      // set the attribute in the request to access it on the JSP
	      String showRecom = "true";
	      request.setAttribute("listData", items);
	      request.setAttribute("showRecom", showRecom);
	      RequestDispatcher rd = getServletContext()
	                                 .getRequestDispatcher("/index.jsp");
	      rd.forward(request, response);
	      
	      
	}

}
