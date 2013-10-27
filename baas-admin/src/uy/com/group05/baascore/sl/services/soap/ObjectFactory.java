
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

    private final static QName _GetClientsFromPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getClientsFromPushChannelResponse");
    private final static QName _ExistsPushChannelApplicationResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsPushChannelApplicationResponse");
    private final static QName _CreatePushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "createPushChannel");
    private final static QName _ExistsPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsPushChannel");
    private final static QName _ApplicationDTO_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "applicationDTO");
    private final static QName _SendNotificationToPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "sendNotificationToPushChannel");
    private final static QName _GetEntitiesAssociatedWithPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getEntitiesAssociatedWithPushChannel");
    private final static QName _UnassignEntityToPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "unassignEntityToPushChannelResponse");
    private final static QName _DeletePushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "deletePushChannelResponse");
    private final static QName _GetPushChannelsOfApplicationResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getPushChannelsOfApplicationResponse");
    private final static QName _SendNotificationToPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "sendNotificationToPushChannelResponse");
    private final static QName _UserDTO_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "userDTO");
    private final static QName _ExistsPushChannelApplication_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsPushChannelApplication");
    private final static QName _UnassignClientFromPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "unassignClientFromPushChannelResponse");
    private final static QName _ExistsPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsPushChannelResponse");
    private final static QName _UnassignEntityToPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "unassignEntityToPushChannel");
    private final static QName _AssignClientToPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignClientToPushChannel");
    private final static QName _AssignEntityToPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignEntityToPushChannel");
    private final static QName _GetPushChannelsOfApplication_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getPushChannelsOfApplication");
    private final static QName _GetEntitiesAssociatedWithPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getEntitiesAssociatedWithPushChannelResponse");
    private final static QName _PushChanAlreadyRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "PushChanAlreadyRegisteredException");
    private final static QName _GetClientsFromPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "getClientsFromPushChannel");
    private final static QName _AppNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "AppNotRegisteredException");
    private final static QName _AssignClientToPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignClientToPushChannelResponse");
    private final static QName _AssignEntityToPushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "assignEntityToPushChannelResponse");
    private final static QName _CreatePushChannelResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "createPushChannelResponse");
    private final static QName _DeletePushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "deletePushChannel");
    private final static QName _ClientNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "ClientNotRegisteredException");
    private final static QName _PushChanNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "PushChanNotRegisteredException");
    private final static QName _UnassignClientFromPushChannel_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "unassignClientFromPushChannel");
    private final static QName _EntityNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "EntityNotRegisteredException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.com.group05.baascore.sl.services.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetClientsFromPushChannelResponse }
     * 
     */
    public GetClientsFromPushChannelResponse createGetClientsFromPushChannelResponse() {
        return new GetClientsFromPushChannelResponse();
    }

    /**
     * Create an instance of {@link ExistsPushChannelApplicationResponse }
     * 
     */
    public ExistsPushChannelApplicationResponse createExistsPushChannelApplicationResponse() {
        return new ExistsPushChannelApplicationResponse();
    }

    /**
     * Create an instance of {@link CreatePushChannel }
     * 
     */
    public CreatePushChannel createCreatePushChannel() {
        return new CreatePushChannel();
    }

    /**
     * Create an instance of {@link ExistsPushChannel }
     * 
     */
    public ExistsPushChannel createExistsPushChannel() {
        return new ExistsPushChannel();
    }

    /**
     * Create an instance of {@link ApplicationDTO }
     * 
     */
    public ApplicationDTO createApplicationDTO() {
        return new ApplicationDTO();
    }

    /**
     * Create an instance of {@link GetEntitiesAssociatedWithPushChannel }
     * 
     */
    public GetEntitiesAssociatedWithPushChannel createGetEntitiesAssociatedWithPushChannel() {
        return new GetEntitiesAssociatedWithPushChannel();
    }

    /**
     * Create an instance of {@link SendNotificationToPushChannel }
     * 
     */
    public SendNotificationToPushChannel createSendNotificationToPushChannel() {
        return new SendNotificationToPushChannel();
    }

    /**
     * Create an instance of {@link UnassignEntityToPushChannelResponse }
     * 
     */
    public UnassignEntityToPushChannelResponse createUnassignEntityToPushChannelResponse() {
        return new UnassignEntityToPushChannelResponse();
    }

    /**
     * Create an instance of {@link DeletePushChannelResponse }
     * 
     */
    public DeletePushChannelResponse createDeletePushChannelResponse() {
        return new DeletePushChannelResponse();
    }

    /**
     * Create an instance of {@link GetPushChannelsOfApplicationResponse }
     * 
     */
    public GetPushChannelsOfApplicationResponse createGetPushChannelsOfApplicationResponse() {
        return new GetPushChannelsOfApplicationResponse();
    }

    /**
     * Create an instance of {@link SendNotificationToPushChannelResponse }
     * 
     */
    public SendNotificationToPushChannelResponse createSendNotificationToPushChannelResponse() {
        return new SendNotificationToPushChannelResponse();
    }

    /**
     * Create an instance of {@link UserDTO }
     * 
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link ExistsPushChannelApplication }
     * 
     */
    public ExistsPushChannelApplication createExistsPushChannelApplication() {
        return new ExistsPushChannelApplication();
    }

    /**
     * Create an instance of {@link UnassignClientFromPushChannelResponse }
     * 
     */
    public UnassignClientFromPushChannelResponse createUnassignClientFromPushChannelResponse() {
        return new UnassignClientFromPushChannelResponse();
    }

    /**
     * Create an instance of {@link ExistsPushChannelResponse }
     * 
     */
    public ExistsPushChannelResponse createExistsPushChannelResponse() {
        return new ExistsPushChannelResponse();
    }

    /**
     * Create an instance of {@link UnassignEntityToPushChannel }
     * 
     */
    public UnassignEntityToPushChannel createUnassignEntityToPushChannel() {
        return new UnassignEntityToPushChannel();
    }

    /**
     * Create an instance of {@link AssignClientToPushChannel }
     * 
     */
    public AssignClientToPushChannel createAssignClientToPushChannel() {
        return new AssignClientToPushChannel();
    }

    /**
     * Create an instance of {@link AssignEntityToPushChannel }
     * 
     */
    public AssignEntityToPushChannel createAssignEntityToPushChannel() {
        return new AssignEntityToPushChannel();
    }

    /**
     * Create an instance of {@link GetPushChannelsOfApplication }
     * 
     */
    public GetPushChannelsOfApplication createGetPushChannelsOfApplication() {
        return new GetPushChannelsOfApplication();
    }

    /**
     * Create an instance of {@link GetEntitiesAssociatedWithPushChannelResponse }
     * 
     */
    public GetEntitiesAssociatedWithPushChannelResponse createGetEntitiesAssociatedWithPushChannelResponse() {
        return new GetEntitiesAssociatedWithPushChannelResponse();
    }

    /**
     * Create an instance of {@link PushChanAlreadyRegisteredException }
     * 
     */
    public PushChanAlreadyRegisteredException createPushChanAlreadyRegisteredException() {
        return new PushChanAlreadyRegisteredException();
    }

    /**
     * Create an instance of {@link GetClientsFromPushChannel }
     * 
     */
    public GetClientsFromPushChannel createGetClientsFromPushChannel() {
        return new GetClientsFromPushChannel();
    }

    /**
     * Create an instance of {@link AppNotRegisteredException }
     * 
     */
    public AppNotRegisteredException createAppNotRegisteredException() {
        return new AppNotRegisteredException();
    }

    /**
     * Create an instance of {@link AssignEntityToPushChannelResponse }
     * 
     */
    public AssignEntityToPushChannelResponse createAssignEntityToPushChannelResponse() {
        return new AssignEntityToPushChannelResponse();
    }

    /**
     * Create an instance of {@link AssignClientToPushChannelResponse }
     * 
     */
    public AssignClientToPushChannelResponse createAssignClientToPushChannelResponse() {
        return new AssignClientToPushChannelResponse();
    }

    /**
     * Create an instance of {@link CreatePushChannelResponse }
     * 
     */
    public CreatePushChannelResponse createCreatePushChannelResponse() {
        return new CreatePushChannelResponse();
    }

    /**
     * Create an instance of {@link DeletePushChannel }
     * 
     */
    public DeletePushChannel createDeletePushChannel() {
        return new DeletePushChannel();
    }

    /**
     * Create an instance of {@link ClientNotRegisteredException }
     * 
     */
    public ClientNotRegisteredException createClientNotRegisteredException() {
        return new ClientNotRegisteredException();
    }

    /**
     * Create an instance of {@link PushChanNotRegisteredException }
     * 
     */
    public PushChanNotRegisteredException createPushChanNotRegisteredException() {
        return new PushChanNotRegisteredException();
    }

    /**
     * Create an instance of {@link EntityNotRegisteredException }
     * 
     */
    public EntityNotRegisteredException createEntityNotRegisteredException() {
        return new EntityNotRegisteredException();
    }

    /**
     * Create an instance of {@link UnassignClientFromPushChannel }
     * 
     */
    public UnassignClientFromPushChannel createUnassignClientFromPushChannel() {
        return new UnassignClientFromPushChannel();
    }

    /**
     * Create an instance of {@link OperationDTO }
     * 
     */
    public OperationDTO createOperationDTO() {
        return new OperationDTO();
    }

    /**
     * Create an instance of {@link SimplePushChannelDTO }
     * 
     */
    public SimplePushChannelDTO createSimplePushChannelDTO() {
        return new SimplePushChannelDTO();
    }

    /**
     * Create an instance of {@link EntityDTO }
     * 
     */
    public EntityDTO createEntityDTO() {
        return new EntityDTO();
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
     * Create an instance of {@link RoleDTO }
     * 
     */
    public RoleDTO createRoleDTO() {
        return new RoleDTO();
    }

    /**
     * Create an instance of {@link SimpleEntityDTO }
     * 
     */
    public SimpleEntityDTO createSimpleEntityDTO() {
        return new SimpleEntityDTO();
    }

    /**
     * Create an instance of {@link ClientDTO }
     * 
     */
    public ClientDTO createClientDTO() {
        return new ClientDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientsFromPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getClientsFromPushChannelResponse")
    public JAXBElement<GetClientsFromPushChannelResponse> createGetClientsFromPushChannelResponse(GetClientsFromPushChannelResponse value) {
        return new JAXBElement<GetClientsFromPushChannelResponse>(_GetClientsFromPushChannelResponse_QNAME, GetClientsFromPushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsPushChannelApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsPushChannelApplicationResponse")
    public JAXBElement<ExistsPushChannelApplicationResponse> createExistsPushChannelApplicationResponse(ExistsPushChannelApplicationResponse value) {
        return new JAXBElement<ExistsPushChannelApplicationResponse>(_ExistsPushChannelApplicationResponse_QNAME, ExistsPushChannelApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "createPushChannel")
    public JAXBElement<CreatePushChannel> createCreatePushChannel(CreatePushChannel value) {
        return new JAXBElement<CreatePushChannel>(_CreatePushChannel_QNAME, CreatePushChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsPushChannel")
    public JAXBElement<ExistsPushChannel> createExistsPushChannel(ExistsPushChannel value) {
        return new JAXBElement<ExistsPushChannel>(_ExistsPushChannel_QNAME, ExistsPushChannel.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SendNotificationToPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "sendNotificationToPushChannel")
    public JAXBElement<SendNotificationToPushChannel> createSendNotificationToPushChannel(SendNotificationToPushChannel value) {
        return new JAXBElement<SendNotificationToPushChannel>(_SendNotificationToPushChannel_QNAME, SendNotificationToPushChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEntitiesAssociatedWithPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getEntitiesAssociatedWithPushChannel")
    public JAXBElement<GetEntitiesAssociatedWithPushChannel> createGetEntitiesAssociatedWithPushChannel(GetEntitiesAssociatedWithPushChannel value) {
        return new JAXBElement<GetEntitiesAssociatedWithPushChannel>(_GetEntitiesAssociatedWithPushChannel_QNAME, GetEntitiesAssociatedWithPushChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnassignEntityToPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "unassignEntityToPushChannelResponse")
    public JAXBElement<UnassignEntityToPushChannelResponse> createUnassignEntityToPushChannelResponse(UnassignEntityToPushChannelResponse value) {
        return new JAXBElement<UnassignEntityToPushChannelResponse>(_UnassignEntityToPushChannelResponse_QNAME, UnassignEntityToPushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "deletePushChannelResponse")
    public JAXBElement<DeletePushChannelResponse> createDeletePushChannelResponse(DeletePushChannelResponse value) {
        return new JAXBElement<DeletePushChannelResponse>(_DeletePushChannelResponse_QNAME, DeletePushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPushChannelsOfApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getPushChannelsOfApplicationResponse")
    public JAXBElement<GetPushChannelsOfApplicationResponse> createGetPushChannelsOfApplicationResponse(GetPushChannelsOfApplicationResponse value) {
        return new JAXBElement<GetPushChannelsOfApplicationResponse>(_GetPushChannelsOfApplicationResponse_QNAME, GetPushChannelsOfApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendNotificationToPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "sendNotificationToPushChannelResponse")
    public JAXBElement<SendNotificationToPushChannelResponse> createSendNotificationToPushChannelResponse(SendNotificationToPushChannelResponse value) {
        return new JAXBElement<SendNotificationToPushChannelResponse>(_SendNotificationToPushChannelResponse_QNAME, SendNotificationToPushChannelResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsPushChannelApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsPushChannelApplication")
    public JAXBElement<ExistsPushChannelApplication> createExistsPushChannelApplication(ExistsPushChannelApplication value) {
        return new JAXBElement<ExistsPushChannelApplication>(_ExistsPushChannelApplication_QNAME, ExistsPushChannelApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnassignClientFromPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "unassignClientFromPushChannelResponse")
    public JAXBElement<UnassignClientFromPushChannelResponse> createUnassignClientFromPushChannelResponse(UnassignClientFromPushChannelResponse value) {
        return new JAXBElement<UnassignClientFromPushChannelResponse>(_UnassignClientFromPushChannelResponse_QNAME, UnassignClientFromPushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsPushChannelResponse")
    public JAXBElement<ExistsPushChannelResponse> createExistsPushChannelResponse(ExistsPushChannelResponse value) {
        return new JAXBElement<ExistsPushChannelResponse>(_ExistsPushChannelResponse_QNAME, ExistsPushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnassignEntityToPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "unassignEntityToPushChannel")
    public JAXBElement<UnassignEntityToPushChannel> createUnassignEntityToPushChannel(UnassignEntityToPushChannel value) {
        return new JAXBElement<UnassignEntityToPushChannel>(_UnassignEntityToPushChannel_QNAME, UnassignEntityToPushChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignClientToPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignClientToPushChannel")
    public JAXBElement<AssignClientToPushChannel> createAssignClientToPushChannel(AssignClientToPushChannel value) {
        return new JAXBElement<AssignClientToPushChannel>(_AssignClientToPushChannel_QNAME, AssignClientToPushChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignEntityToPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignEntityToPushChannel")
    public JAXBElement<AssignEntityToPushChannel> createAssignEntityToPushChannel(AssignEntityToPushChannel value) {
        return new JAXBElement<AssignEntityToPushChannel>(_AssignEntityToPushChannel_QNAME, AssignEntityToPushChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPushChannelsOfApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getPushChannelsOfApplication")
    public JAXBElement<GetPushChannelsOfApplication> createGetPushChannelsOfApplication(GetPushChannelsOfApplication value) {
        return new JAXBElement<GetPushChannelsOfApplication>(_GetPushChannelsOfApplication_QNAME, GetPushChannelsOfApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEntitiesAssociatedWithPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getEntitiesAssociatedWithPushChannelResponse")
    public JAXBElement<GetEntitiesAssociatedWithPushChannelResponse> createGetEntitiesAssociatedWithPushChannelResponse(GetEntitiesAssociatedWithPushChannelResponse value) {
        return new JAXBElement<GetEntitiesAssociatedWithPushChannelResponse>(_GetEntitiesAssociatedWithPushChannelResponse_QNAME, GetEntitiesAssociatedWithPushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PushChanAlreadyRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "PushChanAlreadyRegisteredException")
    public JAXBElement<PushChanAlreadyRegisteredException> createPushChanAlreadyRegisteredException(PushChanAlreadyRegisteredException value) {
        return new JAXBElement<PushChanAlreadyRegisteredException>(_PushChanAlreadyRegisteredException_QNAME, PushChanAlreadyRegisteredException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientsFromPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "getClientsFromPushChannel")
    public JAXBElement<GetClientsFromPushChannel> createGetClientsFromPushChannel(GetClientsFromPushChannel value) {
        return new JAXBElement<GetClientsFromPushChannel>(_GetClientsFromPushChannel_QNAME, GetClientsFromPushChannel.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignClientToPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignClientToPushChannelResponse")
    public JAXBElement<AssignClientToPushChannelResponse> createAssignClientToPushChannelResponse(AssignClientToPushChannelResponse value) {
        return new JAXBElement<AssignClientToPushChannelResponse>(_AssignClientToPushChannelResponse_QNAME, AssignClientToPushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignEntityToPushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "assignEntityToPushChannelResponse")
    public JAXBElement<AssignEntityToPushChannelResponse> createAssignEntityToPushChannelResponse(AssignEntityToPushChannelResponse value) {
        return new JAXBElement<AssignEntityToPushChannelResponse>(_AssignEntityToPushChannelResponse_QNAME, AssignEntityToPushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePushChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "createPushChannelResponse")
    public JAXBElement<CreatePushChannelResponse> createCreatePushChannelResponse(CreatePushChannelResponse value) {
        return new JAXBElement<CreatePushChannelResponse>(_CreatePushChannelResponse_QNAME, CreatePushChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "deletePushChannel")
    public JAXBElement<DeletePushChannel> createDeletePushChannel(DeletePushChannel value) {
        return new JAXBElement<DeletePushChannel>(_DeletePushChannel_QNAME, DeletePushChannel.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PushChanNotRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "PushChanNotRegisteredException")
    public JAXBElement<PushChanNotRegisteredException> createPushChanNotRegisteredException(PushChanNotRegisteredException value) {
        return new JAXBElement<PushChanNotRegisteredException>(_PushChanNotRegisteredException_QNAME, PushChanNotRegisteredException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnassignClientFromPushChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "unassignClientFromPushChannel")
    public JAXBElement<UnassignClientFromPushChannel> createUnassignClientFromPushChannel(UnassignClientFromPushChannel value) {
        return new JAXBElement<UnassignClientFromPushChannel>(_UnassignClientFromPushChannel_QNAME, UnassignClientFromPushChannel.class, null, value);
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
