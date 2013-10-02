
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.com.group05.baascore.sl.services.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValidateUser_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "validateUser");
    private final static QName _EmailAlreadyRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "EmailAlreadyRegisteredException");
    private final static QName _GetUsersResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getUsersResponse");
    private final static QName _IsUserLoggedIn_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "isUserLoggedIn");
    private final static QName _RegisterUser_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "registerUser");
    private final static QName _LoginUserResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "loginUserResponse");
    private final static QName _ValidateUserResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "validateUserResponse");
    private final static QName _LogoutUser_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "logoutUser");
    private final static QName _IsUserLoggedInResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "isUserLoggedInResponse");
    private final static QName _LogoutUserResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "logoutUserResponse");
    private final static QName _UserNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "UserNotRegisteredException");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "registerUserResponse");
    private final static QName _LoginUser_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "loginUser");
    private final static QName _GetUsers_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getUsers");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.com.group05.baascore.sl.services.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidateUser }
     * 
     */
    public ValidateUser createValidateUser() {
        return new ValidateUser();
    }

    /**
     * Create an instance of {@link EmailAlreadyRegisteredException }
     * 
     */
    public EmailAlreadyRegisteredException createEmailAlreadyRegisteredException() {
        return new EmailAlreadyRegisteredException();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link IsUserLoggedIn }
     * 
     */
    public IsUserLoggedIn createIsUserLoggedIn() {
        return new IsUserLoggedIn();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link LoginUserResponse }
     * 
     */
    public LoginUserResponse createLoginUserResponse() {
        return new LoginUserResponse();
    }

    /**
     * Create an instance of {@link ValidateUserResponse }
     * 
     */
    public ValidateUserResponse createValidateUserResponse() {
        return new ValidateUserResponse();
    }

    /**
     * Create an instance of {@link LogoutUser }
     * 
     */
    public LogoutUser createLogoutUser() {
        return new LogoutUser();
    }

    /**
     * Create an instance of {@link IsUserLoggedInResponse }
     * 
     */
    public IsUserLoggedInResponse createIsUserLoggedInResponse() {
        return new IsUserLoggedInResponse();
    }

    /**
     * Create an instance of {@link LogoutUserResponse }
     * 
     */
    public LogoutUserResponse createLogoutUserResponse() {
        return new LogoutUserResponse();
    }

    /**
     * Create an instance of {@link UserNotRegisteredException }
     * 
     */
    public UserNotRegisteredException createUserNotRegisteredException() {
        return new UserNotRegisteredException();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link LoginUser }
     * 
     */
    public LoginUser createLoginUser() {
        return new LoginUser();
    }

    /**
     * Create an instance of {@link UserRegisterDTO }
     * 
     */
    public UserRegisterDTO createUserRegisterDTO() {
        return new UserRegisterDTO();
    }

    /**
     * Create an instance of {@link UserDTO }
     * 
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "validateUser")
    public JAXBElement<ValidateUser> createValidateUser(ValidateUser value) {
        return new JAXBElement<ValidateUser>(_ValidateUser_QNAME, ValidateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailAlreadyRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "EmailAlreadyRegisteredException")
    public JAXBElement<EmailAlreadyRegisteredException> createEmailAlreadyRegisteredException(EmailAlreadyRegisteredException value) {
        return new JAXBElement<EmailAlreadyRegisteredException>(_EmailAlreadyRegisteredException_QNAME, EmailAlreadyRegisteredException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsUserLoggedIn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "isUserLoggedIn")
    public JAXBElement<IsUserLoggedIn> createIsUserLoggedIn(IsUserLoggedIn value) {
        return new JAXBElement<IsUserLoggedIn>(_IsUserLoggedIn_QNAME, IsUserLoggedIn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "loginUserResponse")
    public JAXBElement<LoginUserResponse> createLoginUserResponse(LoginUserResponse value) {
        return new JAXBElement<LoginUserResponse>(_LoginUserResponse_QNAME, LoginUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "validateUserResponse")
    public JAXBElement<ValidateUserResponse> createValidateUserResponse(ValidateUserResponse value) {
        return new JAXBElement<ValidateUserResponse>(_ValidateUserResponse_QNAME, ValidateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "logoutUser")
    public JAXBElement<LogoutUser> createLogoutUser(LogoutUser value) {
        return new JAXBElement<LogoutUser>(_LogoutUser_QNAME, LogoutUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsUserLoggedInResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "isUserLoggedInResponse")
    public JAXBElement<IsUserLoggedInResponse> createIsUserLoggedInResponse(IsUserLoggedInResponse value) {
        return new JAXBElement<IsUserLoggedInResponse>(_IsUserLoggedInResponse_QNAME, IsUserLoggedInResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "logoutUserResponse")
    public JAXBElement<LogoutUserResponse> createLogoutUserResponse(LogoutUserResponse value) {
        return new JAXBElement<LogoutUserResponse>(_LogoutUserResponse_QNAME, LogoutUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNotRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "UserNotRegisteredException")
    public JAXBElement<UserNotRegisteredException> createUserNotRegisteredException(UserNotRegisteredException value) {
        return new JAXBElement<UserNotRegisteredException>(_UserNotRegisteredException_QNAME, UserNotRegisteredException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "loginUser")
    public JAXBElement<LoginUser> createLoginUser(LoginUser value) {
        return new JAXBElement<LoginUser>(_LoginUser_QNAME, LoginUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

}
