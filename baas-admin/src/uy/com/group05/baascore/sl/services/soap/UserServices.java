package uy.com.group05.baascore.sl.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-28T05:43:51.325-02:00
 * Generated source version: 2.4.6
 * 
 */
@WebService(targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "UserServices")
@XmlSeeAlso({ObjectFactory.class})
public interface UserServices {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "isUserLoggedIn", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.IsUserLoggedIn")
    @WebMethod
    @ResponseWrapper(localName = "isUserLoggedInResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.IsUserLoggedInResponse")
    public boolean isUserLoggedIn(
        @WebParam(name = "email", targetNamespace = "")
        java.lang.String email
    ) throws UserNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getUsers", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetUsers")
    @WebMethod
    @ResponseWrapper(localName = "getUsersResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetUsersResponse")
    public java.util.List<uy.com.group05.baascore.sl.services.soap.UserDTO> getUsers();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "registerUser", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.RegisterUser")
    @WebMethod
    @ResponseWrapper(localName = "registerUserResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.RegisterUserResponse")
    public uy.com.group05.baascore.sl.services.soap.UserDTO registerUser(
        @WebParam(name = "user", targetNamespace = "")
        uy.com.group05.baascore.sl.services.soap.UserRegisterDTO user
    ) throws EmailAlreadyRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "logoutUser", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.LogoutUser")
    @WebMethod
    @ResponseWrapper(localName = "logoutUserResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.LogoutUserResponse")
    public boolean logoutUser(
        @WebParam(name = "email", targetNamespace = "")
        java.lang.String email
    ) throws UserNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "loginUserFacebook", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.LoginUserFacebook")
    @WebMethod
    @ResponseWrapper(localName = "loginUserFacebookResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.LoginUserFacebookResponse")
    public uy.com.group05.baascore.sl.services.soap.UserDTO loginUserFacebook(
        @WebParam(name = "email", targetNamespace = "")
        java.lang.String email,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "lastname", targetNamespace = "")
        java.lang.String lastname,
        @WebParam(name = "fbId", targetNamespace = "")
        java.lang.String fbId
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "validateUser", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.ValidateUser")
    @WebMethod
    @ResponseWrapper(localName = "validateUserResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.ValidateUserResponse")
    public boolean validateUser(
        @WebParam(name = "email", targetNamespace = "")
        java.lang.String email,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    ) throws UserNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "loginUser", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.LoginUser")
    @WebMethod
    @ResponseWrapper(localName = "loginUserResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.LoginUserResponse")
    public uy.com.group05.baascore.sl.services.soap.UserDTO loginUser(
        @WebParam(name = "email", targetNamespace = "")
        java.lang.String email,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    ) throws UserNotRegisteredException_Exception;
}
