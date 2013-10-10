package uy.com.group05.baasadmin.pl.controllers;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import uy.com.group05.baasadmin.pl.beans.UserSessionManagementBean;
import uy.com.group05.baasadmin.pl.models.UserModel;

@WebServlet("*.sec")
public class SecurityServlet extends HttpServlet {
 
    private static final long serialVersionUID = 8071426090770097330L;
 
    public SecurityServlet() {
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String faceCode = request.getParameter("code");
        String state = request.getParameter("state");
        String accessToken = getFacebookAccessToken(faceCode);
        JSONObject datos = getUserMailAddressFromJsonResponse(accessToken, httpSession);
        String sessionID = httpSession.getId();
        if (state.equals(sessionID)){
            
                //do some specific user data operation like saving to DB or login user
                
            	try {
        			
            		UserModel user = new UserModel();
            		
            		UserController userController = new UserController();
            		
            		user.setEmail(datos.getString("email"));
            		user.setLastname(datos.getString("last_name"));
            		user.setName(datos.getString("first_name"));
            		user.setPassword(datos.getString("id"));
            		user.setRepeatedPassword(datos.getString("id"));
            		
        			long userId = userController.registerUser(user);
        			
        			user.setUserId(userId);
        			
        			
        			UserSessionManagementBean userSessionManagementBean = 
        					(UserSessionManagementBean)request.getSession().getAttribute("userSessionManagementBean");
        			
        			userSessionManagementBean.setUser(user);
        			
        			response.sendRedirect(request.getContextPath()+  "/pages/dashboard/Index.xhtml");
        		
            	
            	
            	
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath());
                return;
            }
        } else {
            System.err.println("CSRF protection validation");
        }
    }
 
    private String getFacebookAccessToken(String faceCode){
        String token = null;
        if (faceCode != null && ! "".equals(faceCode)) {
            String appId = "641457435904811";
            String redirectUrl = "http://localhost:8080/baas-admin/index.sec";
            String faceAppSecret = "ea71cbca99628d6705f036c17aa5d5b5";
            String newUrl = "https://graph.facebook.com/oauth/access_token?client_id="
                    + appId + "&redirect_uri=" + redirectUrl + "&client_secret="
                    + faceAppSecret + "&code=" + faceCode;
            HttpClient httpclient = new DefaultHttpClient();
            try {
                HttpGet httpget = new HttpGet(newUrl);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String responseBody = httpclient.execute(httpget, responseHandler);
                token = StringUtils.removeEnd
                        (StringUtils.removeStart(responseBody, "access_token="),
                                                 "&expires=5180795");
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpclient.getConnectionManager().shutdown();
            }
        }
        return token;
    }
 
    private JSONObject getUserMailAddressFromJsonResponse(String accessToken,
             HttpSession httpSession) {
        //String email = null;
        JSONObject json = null;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            if (accessToken != null && ! "".equals(accessToken)) {
                String newUrl = "https://graph.facebook.com/me?access_token=" + accessToken;
                httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(newUrl);
                System.out.println("Get info from face --> executing request: "
                                  + httpget.getURI());
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String responseBody = httpclient.execute(httpget, responseHandler);
                json = (JSONObject)JSONSerializer.toJSON(responseBody);
//                String facebookId = json.getString("id");
//                String firstName = json.getString("first_name");
//                String lastName = json.getString("last_name");
//                email= json.getString("email");
                //put user data in session
//                httpSession.setAttribute("FACEBOOK_USER", firstName+" "
//                        +lastName+", facebookId:" + facebookId);
 
            } else {
                System.err.println("Token for facebook is null");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return json;
    }
}
