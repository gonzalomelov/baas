
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

    private final static QName _AssignRoleToClientResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignRoleToClientResponse");
    private final static QName _ApplicationDTO_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "applicationDTO");
    private final static QName _GetRolesFromClient_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getRolesFromClient");
    private final static QName _GetRolesFromClientResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getRolesFromClientResponse");
    private final static QName _AppNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "AppNotRegisteredException");
    private final static QName _AssignRoleToClientsResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignRoleToClientsResponse");
    private final static QName _UserCantAccessAppException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "UserCantAccessAppException");
    private final static QName _AssignRoleToClient_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignRoleToClient");
    private final static QName _UserDTO_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "userDTO");
    private final static QName _AssignRoleToClients_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignRoleToClients");
    private final static QName _GetClient_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getClient");
    private final static QName _GetClientResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getClientResponse");
    private final static QName _ClientNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "ClientNotRegisteredException");
    private final static QName _EntityNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "EntityNotRegisteredException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.com.group05.baascore.sl.services.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AssignRoleToClientResponse }
     * 
     */
    public AssignRoleToClientResponse createAssignRoleToClientResponse() {
        return new AssignRoleToClientResponse();
    }

    /**
     * Create an instance of {@link ApplicationDTO }
     * 
     */
    public ApplicationDTO createApplicationDTO() {
        return new ApplicationDTO();
    }

    /**
     * Create an instance of {@link GetRolesFromClient }
     * 
     */
    public GetRolesFromClient createGetRolesFromClient() {
        return new GetRolesFromClient();
    }

    /**
     * Create an instance of {@link GetRolesFromClientResponse }
     * 
     */
    public GetRolesFromClientResponse createGetRolesFromClientResponse() {
        return new GetRolesFromClientResponse();
    }

    /**
     * Create an instance of {@link AppNotRegisteredException }
     * 
     */
    public AppNotRegisteredException createAppNotRegisteredException() {
        return new AppNotRegisteredException();
    }

    /**
     * Create an instance of {@link AssignRoleToClientsResponse }
     * 
     */
    public AssignRoleToClientsResponse createAssignRoleToClientsResponse() {
        return new AssignRoleToClientsResponse();
    }

    /**
     * Create an instance of {@link UserCantAccessAppException }
     * 
     */
    public UserCantAccessAppException createUserCantAccessAppException() {
        return new UserCantAccessAppException();
    }

    /**
     * Create an instance of {@link AssignRoleToClient }
     * 
     */
    public AssignRoleToClient createAssignRoleToClient() {
        return new AssignRoleToClient();
    }

    /**
     * Create an instance of {@link UserDTO }
     * 
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link AssignRoleToClients }
     * 
     */
    public AssignRoleToClients createAssignRoleToClients() {
        return new AssignRoleToClients();
    }

    /**
     * Create an instance of {@link GetClient }
     * 
     */
    public GetClient createGetClient() {
        return new GetClient();
    }

    /**
     * Create an instance of {@link GetClientResponse }
     * 
     */
    public GetClientResponse createGetClientResponse() {
        return new GetClientResponse();
    }

    /**
     * Create an instance of {@link ClientNotRegisteredException }
     * 
     */
    public ClientNotRegisteredException createClientNotRegisteredException() {
        return new ClientNotRegisteredException();
    }

    /**
     * Create an instance of {@link EntityNotRegisteredException }
     * 
     */
    public EntityNotRegisteredException createEntityNotRegisteredException() {
        return new EntityNotRegisteredException();
    }

    /**
     * Create an instance of {@link OperationDTO }
     * 
     */
    public OperationDTO createOperationDTO() {
        return new OperationDTO();
    }

    /**
     * Create an instance of {@link SimpleRoleDTO }
     * 
     */
    public SimpleRoleDTO createSimpleRoleDTO() {
        return new SimpleRoleDTO();
    }

    /**
     * Create an instance of {@link PermissionDTO }
     * 
     */
    public PermissionDTO createPermissionDTO() {
        return new PermissionDTO();
    }

    /**
     * Create an instance of {@link RolesClientDTO }
     * 
     */
    public RolesClientDTO createRolesClientDTO() {
        return new RolesClientDTO();
    }

    /**
     * Create an instance of {@link RoleDTO }
     * 
     */
    public RoleDTO createRoleDTO() {
        return new RoleDTO();
    }

    /**
     * Create an instance of {@link ClientDTO }
     * 
     */
    public ClientDTO createClientDTO() {
        return new ClientDTO();
    }

    /**
     * Create an instance of {@link EntityDTO }
     * 
     */
    public EntityDTO createEntityDTO() {
        return new EntityDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignRoleToClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignRoleToClientResponse")
    public JAXBElement<AssignRoleToClientResponse> createAssignRoleToClientResponse(AssignRoleToClientResponse value) {
        return new JAXBElement<AssignRoleToClientResponse>(_AssignRoleToClientResponse_QNAME, AssignRoleToClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "applicationDTO")
    public JAXBElement<ApplicationDTO> createApplicationDTO(ApplicationDTO value) {
        return new JAXBElement<ApplicationDTO>(_ApplicationDTO_QNAME, ApplicationDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesFromClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getRolesFromClient")
    public JAXBElement<GetRolesFromClient> createGetRolesFromClient(GetRolesFromClient value) {
        return new JAXBElement<GetRolesFromClient>(_GetRolesFromClient_QNAME, GetRolesFromClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesFromClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getRolesFromClientResponse")
    public JAXBElement<GetRolesFromClientResponse> createGetRolesFromClientResponse(GetRolesFromClientResponse value) {
        return new JAXBElement<GetRolesFromClientResponse>(_GetRolesFromClientResponse_QNAME, GetRolesFromClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppNotRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "AppNotRegisteredException")
    public JAXBElement<AppNotRegisteredException> createAppNotRegisteredException(AppNotRegisteredException value) {
        return new JAXBElement<AppNotRegisteredException>(_AppNotRegisteredException_QNAME, AppNotRegisteredException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignRoleToClientsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignRoleToClientsResponse")
    public JAXBElement<AssignRoleToClientsResponse> createAssignRoleToClientsResponse(AssignRoleToClientsResponse value) {
        return new JAXBElement<AssignRoleToClientsResponse>(_AssignRoleToClientsResponse_QNAME, AssignRoleToClientsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserCantAccessAppException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "UserCantAccessAppException")
    public JAXBElement<UserCantAccessAppException> createUserCantAccessAppException(UserCantAccessAppException value) {
        return new JAXBElement<UserCantAccessAppException>(_UserCantAccessAppException_QNAME, UserCantAccessAppException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignRoleToClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignRoleToClient")
    public JAXBElement<AssignRoleToClient> createAssignRoleToClient(AssignRoleToClient value) {
        return new JAXBElement<AssignRoleToClient>(_AssignRoleToClient_QNAME, AssignRoleToClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "userDTO")
    public JAXBElement<UserDTO> createUserDTO(UserDTO value) {
        return new JAXBElement<UserDTO>(_UserDTO_QNAME, UserDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignRoleToClients }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignRoleToClients")
    public JAXBElement<AssignRoleToClients> createAssignRoleToClients(AssignRoleToClients value) {
        return new JAXBElement<AssignRoleToClients>(_AssignRoleToClients_QNAME, AssignRoleToClients.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getClient")
    public JAXBElement<GetClient> createGetClient(GetClient value) {
        return new JAXBElement<GetClient>(_GetClient_QNAME, GetClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getClientResponse")
    public JAXBElement<GetClientResponse> createGetClientResponse(GetClientResponse value) {
        return new JAXBElement<GetClientResponse>(_GetClientResponse_QNAME, GetClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientNotRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "ClientNotRegisteredException")
    public JAXBElement<ClientNotRegisteredException> createClientNotRegisteredException(ClientNotRegisteredException value) {
        return new JAXBElement<ClientNotRegisteredException>(_ClientNotRegisteredException_QNAME, ClientNotRegisteredException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityNotRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "EntityNotRegisteredException")
    public JAXBElement<EntityNotRegisteredException> createEntityNotRegisteredException(EntityNotRegisteredException value) {
        return new JAXBElement<EntityNotRegisteredException>(_EntityNotRegisteredException_QNAME, EntityNotRegisteredException.class, null, value);
    }

}
