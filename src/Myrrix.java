

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
		 // process(request, response);'
		response.setContentType("text/html");
		PrintWriter pr = response.getWriter();
		pr.println("HELLOOOOO");
		pr.close();
		
		System.out.println("Entered Servlet");
//	    StringBuilder filePathBuilder = new StringBuilder(getServletContext().getRealPath("/").toString());
//	    System.out.println(filePathBuilder.toString());
//	    filePathBuilder.append(File.separator);
//	    filePathBuilder.append("sd");
//	    File file= new File(filePathBuilder.toString());
//	    file.createNewFile();
	      File ff = new File("/home/crap/myrrix");
	      System.out.println(ff.isDirectory());
	      ServerRecommender recom = new ServerRecommender(ff);
	      while(!recom.isReady()){}
		
	      List<RecommendedItem> ll = null;
	      
			try {
				ll = recom.recommend(705567306, 10);
			} catch (NoSuchUserException | NotReadyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	      for(RecommendedItem item : ll){
	    	  System.out.println(item.getItemID()+" , "+item.getValue());
	      }
	}

}
