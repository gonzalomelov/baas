
/**
 * UserSoapFacadeCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package uy.com.group05.baasadmin.pl.services;

    /**
     *  UserSoapFacadeCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class UserSoapFacadeCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public UserSoapFacadeCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public UserSoapFacadeCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for loginUser method
            * override this method for handling normal response from loginUser operation
            */
           public void receiveResultloginUser(
                    uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.LoginUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loginUser operation
           */
            public void receiveErrorloginUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUsers method
            * override this method for handling normal response from getUsers operation
            */
           public void receiveResultgetUsers(
                    uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.GetUsersResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUsers operation
           */
            public void receiveErrorgetUsers(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registerUser method
            * override this method for handling normal response from registerUser operation
            */
           public void receiveResultregisterUser(
                    uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.RegisterUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registerUser operation
           */
            public void receiveErrorregisterUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isUserLoggedIn method
            * override this method for handling normal response from isUserLoggedIn operation
            */
           public void receiveResultisUserLoggedIn(
                    uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.IsUserLoggedInResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isUserLoggedIn operation
           */
            public void receiveErrorisUserLoggedIn(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for logoutUser method
            * override this method for handling normal response from logoutUser operation
            */
           public void receiveResultlogoutUser(
                    uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.LogoutUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from logoutUser operation
           */
            public void receiveErrorlogoutUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for validateUser method
            * override this method for handling normal response from validateUser operation
            */
           public void receiveResultvalidateUser(
                    uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.ValidateUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from validateUser operation
           */
            public void receiveErrorvalidateUser(java.lang.Exception e) {
            }
                


    }
    