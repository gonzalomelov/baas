
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

    private final static QName _CreateApplication_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "createApplication");
    private final static QName _CreateApplicationResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "createApplicationResponse");
    private final static QName _ExistsApplication_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsApplication");
    private final static QName _ListApplications_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "listApplications");
    private final static QName _ExistsEntityApplication_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsEntityApplication");
    private final static QName _EntityCollectionAlreadyExistsException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "EntityCollectionAlreadyExistsException");
    private final static QName _ExistsEntityApplicationResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsEntityApplicationResponse");
    private final static QName _ApplicationDTO_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "applicationDTO");
    private final static QName _ListApplicationsResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "listApplicationsResponse");
    private final static QName _AppNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "AppNotRegisteredException");
    private final static QName _ExistsApplicationResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsApplicationResponse");
    private final static QName _ExistsRoleApplication_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsRoleApplication");
    private final static QName _UserNotRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "UserNotRegisteredException");
    private final static QName _NombreAppAlreadyRegisteredException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "NombreAppAlreadyRegisteredException");
    private final static QName _ExistsRoleApplicationResponse_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "existsRoleApplicationResponse");
    private final static QName _MongoDBAlreadyExistsException_QNAME = new QName("http://soap.services.sl.baascore.group05.com.uy/", "MongoDBAlreadyExistsException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.com.group05.baascore.sl.services.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateApplication }
     * 
     */
    public CreateApplication createCreateApplication() {
        return new CreateApplication();
    }

    /**
     * Create an instance of {@link CreateApplicationResponse }
     * 
     */
    public CreateApplicationResponse createCreateApplicationResponse() {
        return new CreateApplicationResponse();
    }

    /**
     * Create an instance of {@link ExistsApplication }
     * 
     */
    public ExistsApplication createExistsApplication() {
        return new ExistsApplication();
    }

    /**
     * Create an instance of {@link ListApplications }
     * 
     */
    public ListApplications createListApplications() {
        return new ListApplications();
    }

    /**
     * Create an instance of {@link ExistsEntityApplication }
     * 
     */
    public ExistsEntityApplication createExistsEntityApplication() {
        return new ExistsEntityApplication();
    }

    /**
     * Create an instance of {@link EntityCollectionAlreadyExistsException }
     * 
     */
    public EntityCollectionAlreadyExistsException createEntityCollectionAlreadyExistsException() {
        return new EntityCollectionAlreadyExistsException();
    }

    /**
     * Create an instance of {@link ExistsEntityApplicationResponse }
     * 
     */
    public ExistsEntityApplicationResponse createExistsEntityApplicationResponse() {
        return new ExistsEntityApplicationResponse();
    }

    /**
     * Create an instance of {@link ApplicationDTO }
     * 
     */
    public ApplicationDTO createApplicationDTO() {
        return new ApplicationDTO();
    }

    /**
     * Create an instance of {@link ListApplicationsResponse }
     * 
     */
    public ListApplicationsResponse createListApplicationsResponse() {
        return new ListApplicationsResponse();
    }

    /**
     * Create an instance of {@link AppNotRegisteredException }
     * 
     */
    public AppNotRegisteredException createAppNotRegisteredException() {
        return new AppNotRegisteredException();
    }

    /**
     * Create an instance of {@link ExistsApplicationResponse }
     * 
     */
    public ExistsApplicationResponse createExistsApplicationResponse() {
        return new ExistsApplicationResponse();
    }

    /**
     * Create an instance of {@link ExistsRoleApplication }
     * 
     */
    public ExistsRoleApplication createExistsRoleApplication() {
        return new ExistsRoleApplication();
    }

    /**
     * Create an instance of {@link UserNotRegisteredException }
     * 
     */
    public UserNotRegisteredException createUserNotRegisteredException() {
        return new UserNotRegisteredException();
    }

    /**
     * Create an instance of {@link MongoDBAlreadyExistsException }
     * 
     */
    public MongoDBAlreadyExistsException createMongoDBAlreadyExistsException() {
        return new MongoDBAlreadyExistsException();
    }

    /**
     * Create an instance of {@link ExistsRoleApplicationResponse }
     * 
     */
    public ExistsRoleApplicationResponse createExistsRoleApplicationResponse() {
        return new ExistsRoleApplicationResponse();
    }

    /**
     * Create an instance of {@link NombreAppAlreadyRegisteredException }
     * 
     */
    public NombreAppAlreadyRegisteredException createNombreAppAlreadyRegisteredException() {
        return new NombreAppAlreadyRegisteredException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "createApplication")
    public JAXBElement<CreateApplication> createCreateApplication(CreateApplication value) {
        return new JAXBElement<CreateApplication>(_CreateApplication_QNAME, CreateApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "createApplicationResponse")
    public JAXBElement<CreateApplicationResponse> createCreateApplicationResponse(CreateApplicationResponse value) {
        return new JAXBElement<CreateApplicationResponse>(_CreateApplicationResponse_QNAME, CreateApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsApplication")
    public JAXBElement<ExistsApplication> createExistsApplication(ExistsApplication value) {
        return new JAXBElement<ExistsApplication>(_ExistsApplication_QNAME, ExistsApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListApplications }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "listApplications")
    public JAXBElement<ListApplications> createListApplications(ListApplications value) {
        return new JAXBElement<ListApplications>(_ListApplications_QNAME, ListApplications.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsEntityApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsEntityApplication")
    public JAXBElement<ExistsEntityApplication> createExistsEntityApplication(ExistsEntityApplication value) {
        return new JAXBElement<ExistsEntityApplication>(_ExistsEntityApplication_QNAME, ExistsEntityApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityCollectionAlreadyExistsException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "EntityCollectionAlreadyExistsException")
    public JAXBElement<EntityCollectionAlreadyExistsException> createEntityCollectionAlreadyExistsException(EntityCollectionAlreadyExistsException value) {
        return new JAXBElement<EntityCollectionAlreadyExistsException>(_EntityCollectionAlreadyExistsException_QNAME, EntityCollectionAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsEntityApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsEntityApplicationResponse")
    public JAXBElement<ExistsEntityApplicationResponse> createExistsEntityApplicationResponse(ExistsEntityApplicationResponse value) {
        return new JAXBElement<ExistsEntityApplicationResponse>(_ExistsEntityApplicationResponse_QNAME, ExistsEntityApplicationResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ListApplicationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "listApplicationsResponse")
    public JAXBElement<ListApplicationsResponse> createListApplicationsResponse(ListApplicationsResponse value) {
        return new JAXBElement<ListApplicationsResponse>(_ListApplicationsResponse_QNAME, ListApplicationsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsApplicationResponse")
    public JAXBElement<ExistsApplicationResponse> createExistsApplicationResponse(ExistsApplicationResponse value) {
        return new JAXBElement<ExistsApplicationResponse>(_ExistsApplicationResponse_QNAME, ExistsApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsRoleApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsRoleApplication")
    public JAXBElement<ExistsRoleApplication> createExistsRoleApplication(ExistsRoleApplication value) {
        return new JAXBElement<ExistsRoleApplication>(_ExistsRoleApplication_QNAME, ExistsRoleApplication.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link NombreAppAlreadyRegisteredException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "NombreAppAlreadyRegisteredException")
    public JAXBElement<NombreAppAlreadyRegisteredException> createNombreAppAlreadyRegisteredException(NombreAppAlreadyRegisteredException value) {
        return new JAXBElement<NombreAppAlreadyRegisteredException>(_NombreAppAlreadyRegisteredException_QNAME, NombreAppAlreadyRegisteredException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsRoleApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "existsRoleApplicationResponse")
    public JAXBElement<ExistsRoleApplicationResponse> createExistsRoleApplicationResponse(ExistsRoleApplicationResponse value) {
        return new JAXBElement<ExistsRoleApplicationResponse>(_ExistsRoleApplicationResponse_QNAME, ExistsRoleApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MongoDBAlreadyExistsException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "MongoDBAlreadyExistsException")
    public JAXBElement<MongoDBAlreadyExistsException> createMongoDBAlreadyExistsException(MongoDBAlreadyExistsException value) {
        return new JAXBElement<MongoDBAlreadyExistsException>(_MongoDBAlreadyExistsException_QNAME, MongoDBAlreadyExistsException.class, null, value);
    }

}
