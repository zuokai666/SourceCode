package org.zk.catalina;

import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletSecurityElement;
import javax.servlet.descriptor.JspConfigDescriptor;

import org.zk.catalina.Loader;
import org.zk.catalina.Manager;
import org.zk.catalina.ThreadBindingListener;
import org.zk.catalina.WebResourceRoot;
import org.zk.catalina.Wrapper;
import org.zk.tomcat.util.descriptor.web.ApplicationParameter;
import org.zk.tomcat.util.descriptor.web.ErrorPage;
import org.zk.tomcat.util.descriptor.web.FilterDef;
import org.zk.tomcat.util.descriptor.web.FilterMap;
import org.zk.tomcat.util.descriptor.web.SecurityConstraint;
import org.zk.tomcat.util.http.CookieProcessor;
import org.zk.catalina.Authenticator;
import org.zk.catalina.deploy.NamingResourcesImpl;
import org.zk.tomcat.InstanceManager;
import org.zk.tomcat.JarScanner;
import org.zk.tomcat.util.descriptor.web.LoginConfig;

/**
 * 这个接口是真他妈的多，复制也好累
 * 
 * WelcomeFile、ErrorPage、mimeType的增删改查
 * 
 * @author king
 * @date 2019-05-03 11:53:26
 * 
 */
public interface Context extends Container, ContextBind{
	
    String ADD_WELCOME_FILE_EVENT = "addWelcomeFile";
    String REMOVE_WELCOME_FILE_EVENT = "removeWelcomeFile";
    String CLEAR_WELCOME_FILES_EVENT = "clearWelcomeFiles";
    String CHANGE_SESSION_ID_EVENT = "changeSessionId";
    
    /**
     * 和POST表单提交的enctype="multipart/form-data"有关
     */
    boolean getAllowCasualMultipartParsing();
    void setAllowCasualMultipartParsing(boolean allowCasualMultipartParsing);
    public Object[] getApplicationEventListeners();
    public void setApplicationEventListeners(Object listeners[]);
    public Object[] getApplicationLifecycleListeners();
    public void setApplicationLifecycleListeners(Object listeners[]);
    public String getCharset(Locale locale);
    public URL getConfigFile();
    public void setConfigFile(URL configFile);
    public boolean getConfigured();
    public void setConfigured(boolean configured);
    public boolean getCookies();
    public void setCookies(boolean cookies);
    public String getSessionCookieName();
    public void setSessionCookieName(String sessionCookieName);
    /**
     * 和Cookie的HttpOnly属性有关
     */
    public boolean getUseHttpOnly();
    public void setUseHttpOnly(boolean useHttpOnly);
    public String getSessionCookieDomain();
    public void setSessionCookieDomain(String sessionCookieDomain);
    public String getSessionCookiePath();
    public void setSessionCookiePath(String sessionCookiePath);
    public boolean getSessionCookiePathUsesTrailingSlash();
    public void setSessionCookiePathUsesTrailingSlash(boolean sessionCookiePathUsesTrailingSlash);
    public boolean getCrossContext();
    public String getAltDDName();
    public void setAltDDName(String altDDName) ;
    public void setCrossContext(boolean crossContext);
    public boolean getDenyUncoveredHttpMethods();
    public void setDenyUncoveredHttpMethods(boolean denyUncoveredHttpMethods);
    public String getDisplayName();
    public void setDisplayName(String displayName);
    public boolean getDistributable();
    public void setDistributable(boolean distributable);
    public String getDocBase();
    public void setDocBase(String docBase);
    public String getEncodedPath();
    public boolean getIgnoreAnnotations();
    public void setIgnoreAnnotations(boolean ignoreAnnotations);
    public LoginConfig getLoginConfig();
    public void setLoginConfig(LoginConfig config);
    public NamingResourcesImpl getNamingResources();
    public void setNamingResources(NamingResourcesImpl namingResources);
    public String getPath();
    public void setPath(String path);
    public String getPublicId();
    public void setPublicId(String publicId);
    public boolean getReloadable();
    public void setReloadable(boolean reloadable);
    public boolean getOverride();
    public void setOverride(boolean override);
    public boolean getPrivileged();
    public void setPrivileged(boolean privileged);
    public ServletContext getServletContext();
    public int getSessionTimeout();
    public void setSessionTimeout(int timeout);
    public boolean getSwallowAbortedUploads();
    public void setSwallowAbortedUploads(boolean swallowAbortedUploads);
    public boolean getSwallowOutput();
    public void setSwallowOutput(boolean swallowOutput);
    public String getWrapperClass();
    public void setWrapperClass(String wrapperClass);
    public boolean getXmlNamespaceAware();
    public void setXmlNamespaceAware(boolean xmlNamespaceAware);
    public boolean getXmlValidation();
    public void setXmlValidation(boolean xmlValidation);
    public boolean getXmlBlockExternal();
    public void setXmlBlockExternal(boolean xmlBlockExternal);
    public boolean getTldValidation();
    public void setTldValidation(boolean tldValidation);
    public JarScanner getJarScanner();
    public void setJarScanner(JarScanner jarScanner);
    public Authenticator getAuthenticator();
    public void setLogEffectiveWebXml(boolean logEffectiveWebXml);
    public boolean getLogEffectiveWebXml();
    public InstanceManager getInstanceManager();
    public void setInstanceManager(InstanceManager instanceManager);
    public void setContainerSciFilter(String containerSciFilter);
    public String getContainerSciFilter();
    
    /**
     * Add a new Listener class name to the set of Listeners
     * configured for this application.
     *
     * @param listener Java class name of a listener class
     */
    public void addApplicationListener(String listener);


    /**
     * Add a new application parameter for this application.
     *
     * @param parameter The new application parameter
     */
    public void addApplicationParameter(ApplicationParameter parameter);


    /**
     * Add a security constraint to the set for this web application.
     *
     * @param constraint The security constraint that should be added
     */
    public void addConstraint(SecurityConstraint constraint);


    /**
     * Add an error page for the specified error or Java exception.
     *
     * @param errorPage The error page definition to be added
     */
    public void addErrorPage(ErrorPage errorPage);


    /**
     * Add a filter definition to this Context.
     *
     * @param filterDef The filter definition to be added
     */
    public void addFilterDef(FilterDef filterDef);


    /**
     * Add a filter mapping to this Context.
     *
     * @param filterMap The filter mapping to be added
     */
    public void addFilterMap(FilterMap filterMap);

    /**
     * Add a filter mapping to this Context before the mappings defined in the
     * deployment descriptor but after any other mappings added via this method.
     *
     * @param filterMap The filter mapping to be added
     *
     * @exception IllegalArgumentException if the specified filter name
     *  does not match an existing filter definition, or the filter mapping
     *  is malformed
     */
    public void addFilterMapBefore(FilterMap filterMap);


    /**
     * Add a Locale Encoding Mapping (see Sec 5.4 of Servlet spec 2.4)
     *
     * @param locale locale to map an encoding for
     * @param encoding encoding to be used for a give locale
     */
    public void addLocaleEncodingMappingParameter(String locale, String encoding);


    /**
     * Add a new MIME mapping, replacing any existing mapping for
     * the specified extension.
     *
     * @param extension Filename extension being mapped
     * @param mimeType Corresponding MIME type
     */
    public void addMimeMapping(String extension, String mimeType);


    /**
     * Add a new context initialization parameter, replacing any existing
     * value for the specified name.
     *
     * @param name Name of the new parameter
     * @param value Value of the new  parameter
     */
    public void addParameter(String name, String value);


    /**
     * Add a security role reference for this web application.
     *
     * @param role Security role used in the application
     * @param link Actual security role to check for
     */
    public void addRoleMapping(String role, String link);


    /**
     * Add a new security role for this web application.
     *
     * @param role New security role
     */
    public void addSecurityRole(String role);

    /**
     * Add a new servlet mapping, replacing any existing mapping for
     * the specified pattern.
     *
     * @param pattern URL pattern to be mapped
     * @param name Name of the corresponding servlet to execute
     */
    public void addServletMappingDecoded(String pattern, String name);


    /**
     * Add a new servlet mapping, replacing any existing mapping for
     * the specified pattern.
     *
     * @param pattern URL pattern to be mapped
     * @param name Name of the corresponding servlet to execute
     * @param jspWildcard true if name identifies the JspServlet
     * and pattern contains a wildcard; false otherwise
     */
    public void addServletMappingDecoded(String pattern, String name,
            boolean jspWildcard);


    /**
     * Add a resource which will be watched for reloading by the host auto
     * deployer. Note: this will not be used in embedded mode.
     *
     * @param name Path to the resource, relative to docBase
     */
    public void addWatchedResource(String name);


    /**
     * Add a new welcome file to the set recognized by this Context.
     *
     * @param name New welcome file name
     */
    public void addWelcomeFile(String name);


    /**
     * Add the classname of a LifecycleListener to be added to each
     * Wrapper appended to this Context.
     *
     * @param listener Java class name of a LifecycleListener class
     */
    public void addWrapperLifecycle(String listener);


    /**
     * Add the classname of a ContainerListener to be added to each
     * Wrapper appended to this Context.
     *
     * @param listener Java class name of a ContainerListener class
     */
    public void addWrapperListener(String listener);


    /**
     * Factory method to create and return a new Wrapper instance, of
     * the Java implementation class appropriate for this Context
     * implementation.  The constructor of the instantiated Wrapper
     * will have been called, but no properties will have been set.
     *
     * @return a newly created wrapper instance that is used to wrap a Servlet
     */
    public Wrapper createWrapper();


    /**
     * @return the set of application listener class names configured
     * for this application.
     */
    public String[] findApplicationListeners();


    /**
     * @return the set of application parameters for this application.
     */
    public ApplicationParameter[] findApplicationParameters();


    /**
     * @return the set of security constraints for this web application.
     * If there are none, a zero-length array is returned.
     */
    public SecurityConstraint[] findConstraints();


    /**
     * @return the error page entry for the specified HTTP error code,
     * if any; otherwise return <code>null</code>.
     *
     * @param errorCode Error code to look up
     */
    public ErrorPage findErrorPage(int errorCode);

    /**
     * Find and return the ErrorPage instance for the specified exception's
     * class, or an ErrorPage instance for the closest superclass for which
     * there is such a definition.  If no associated ErrorPage instance is
     * found, return <code>null</code>.
     *
     * @param throwable The exception type for which to find an ErrorPage
     *
     * @return the error page entry for the specified Java exception type,
     *         if any; otherwise return {@code null}.
     */
    public ErrorPage findErrorPage(Throwable throwable);


    /**
     * @return the set of defined error pages for all specified error codes
     * and exception types.
     */
    public ErrorPage[] findErrorPages();


    /**
     * @return the filter definition for the specified filter name, if any;
     * otherwise return <code>null</code>.
     *
     * @param filterName Filter name to look up
     */
    public FilterDef findFilterDef(String filterName);


    /**
     * @return the set of defined filters for this Context.
     */
    public FilterDef[] findFilterDefs();


    /**
     * @return the set of filter mappings for this Context.
     */
    public FilterMap[] findFilterMaps();


    /**
     * @return the MIME type to which the specified extension is mapped,
     * if any; otherwise return <code>null</code>.
     *
     * @param extension Extension to map to a MIME type
     */
    public String findMimeMapping(String extension);


    /**
     * @return the extensions for which MIME mappings are defined.  If there
     * are none, a zero-length array is returned.
     */
    public String[] findMimeMappings();


    /**
     * @return the value for the specified context initialization
     * parameter name, if any; otherwise return <code>null</code>.
     *
     * @param name Name of the parameter to return
     */
    public String findParameter(String name);


    /**
     * @return the names of all defined context initialization parameters
     * for this Context.  If no parameters are defined, a zero-length
     * array is returned.
     */
    public String[] findParameters();


    /**
     * For the given security role (as used by an application), return the
     * corresponding role name (as defined by the underlying Realm) if there
     * is one.  Otherwise, return the specified role unchanged.
     *
     * @param role Security role to map
     * @return The role name that was mapped to the specified role
     */
    public String findRoleMapping(String role);


    /**
     * @return <code>true</code> if the specified security role is defined
     * for this application; otherwise return <code>false</code>.
     *
     * @param role Security role to verify
     */
    public boolean findSecurityRole(String role);


    /**
     * @return the security roles defined for this application.  If none
     * have been defined, a zero-length array is returned.
     */
    public String[] findSecurityRoles();


    /**
     * @return the servlet name mapped by the specified pattern (if any);
     * otherwise return <code>null</code>.
     *
     * @param pattern Pattern for which a mapping is requested
     */
    public String findServletMapping(String pattern);


    /**
     * @return the patterns of all defined servlet mappings for this
     * Context.  If no mappings are defined, a zero-length array is returned.
     */
    public String[] findServletMappings();

    /**
     * @return the associated ThreadBindingListener.
     */
    public ThreadBindingListener getThreadBindingListener();


    /**
     * Get the associated ThreadBindingListener.
     *
     * @param threadBindingListener Set the listener that will receive
     *       notifications when entering and exiting the application scope
     */
    public void setThreadBindingListener(ThreadBindingListener threadBindingListener);


    /**
     * @return the set of watched resources for this Context. If none are
     * defined, a zero length array will be returned.
     */
    public String[] findWatchedResources();


    /**
     * @return <code>true</code> if the specified welcome file is defined
     * for this Context; otherwise return <code>false</code>.
     *
     * @param name Welcome file to verify
     */
    public boolean findWelcomeFile(String name);


    /**
     * @return the set of welcome files defined for this Context.  If none are
     * defined, a zero-length array is returned.
     */
    public String[] findWelcomeFiles();


    /**
     * @return the set of LifecycleListener classes that will be added to
     * newly created Wrappers automatically.
     */
    public String[] findWrapperLifecycles();


    /**
     * @return the set of ContainerListener classes that will be added to
     * newly created Wrappers automatically.
     */
    public String[] findWrapperListeners();


    /**
     * Notify all {@link javax.servlet.ServletRequestListener}s that a request
     * has started.
     *
     * @param request The request object that will be passed to the listener
     * @return <code>true</code> if the listeners fire successfully, else
     *         <code>false</code>
     */
    public boolean fireRequestInitEvent(ServletRequest request);

    /**
     * Notify all {@link javax.servlet.ServletRequestListener}s that a request
     * has ended.
     *
     * @param request The request object that will be passed to the listener
     * @return <code>true</code> if the listeners fire successfully, else
     *         <code>false</code>
     */
    public boolean fireRequestDestroyEvent(ServletRequest request);

    /**
     * Reload this web application, if reloading is supported.
     *
     * @exception IllegalStateException if the <code>reloadable</code>
     *  property is set to <code>false</code>.
     */
    public void reload();


    /**
     * Remove the specified application listener class from the set of
     * listeners for this application.
     *
     * @param listener Java class name of the listener to be removed
     */
    public void removeApplicationListener(String listener);


    /**
     * Remove the application parameter with the specified name from
     * the set for this application.
     *
     * @param name Name of the application parameter to remove
     */
    public void removeApplicationParameter(String name);


    /**
     * Remove the specified security constraint from this web application.
     *
     * @param constraint Constraint to be removed
     */
    public void removeConstraint(SecurityConstraint constraint);


    /**
     * Remove the error page for the specified error code or
     * Java language exception, if it exists; otherwise, no action is taken.
     *
     * @param errorPage The error page definition to be removed
     */
    public void removeErrorPage(ErrorPage errorPage);


    /**
     * Remove the specified filter definition from this Context, if it exists;
     * otherwise, no action is taken.
     *
     * @param filterDef Filter definition to be removed
     */
    public void removeFilterDef(FilterDef filterDef);


    /**
     * Remove a filter mapping from this Context.
     *
     * @param filterMap The filter mapping to be removed
     */
    public void removeFilterMap(FilterMap filterMap);


    /**
     * Remove the MIME mapping for the specified extension, if it exists;
     * otherwise, no action is taken.
     *
     * @param extension Extension to remove the mapping for
     */
    public void removeMimeMapping(String extension);


    /**
     * Remove the context initialization parameter with the specified
     * name, if it exists; otherwise, no action is taken.
     *
     * @param name Name of the parameter to remove
     */
    public void removeParameter(String name);


    /**
     * Remove any security role reference for the specified name
     *
     * @param role Security role (as used in the application) to remove
     */
    public void removeRoleMapping(String role);


    /**
     * Remove any security role with the specified name.
     *
     * @param role Security role to remove
     */
    public void removeSecurityRole(String role);


    /**
     * Remove any servlet mapping for the specified pattern, if it exists;
     * otherwise, no action is taken.
     *
     * @param pattern URL pattern of the mapping to remove
     */
    public void removeServletMapping(String pattern);


    /**
     * Remove the specified watched resource name from the list associated
     * with this Context.
     *
     * @param name Name of the watched resource to be removed
     */
    public void removeWatchedResource(String name);


    /**
     * Remove the specified welcome file name from the list recognized
     * by this Context.
     *
     * @param name Name of the welcome file to be removed
     */
    public void removeWelcomeFile(String name);


    /**
     * Remove a class name from the set of LifecycleListener classes that
     * will be added to newly created Wrappers.
     *
     * @param listener Class name of a LifecycleListener class to be removed
     */
    public void removeWrapperLifecycle(String listener);


    /**
     * Remove a class name from the set of ContainerListener classes that
     * will be added to newly created Wrappers.
     *
     * @param listener Class name of a ContainerListener class to be removed
     */
    public void removeWrapperListener(String listener);


    /**
     * @return the real path for a given virtual path, if possible; otherwise
     * return <code>null</code>.
     *
     * @param path The path to the desired resource
     */
    public String getRealPath(String path);


    /**
     * @return the effective major version of the Servlet spec used by this
     * context.
     */
    public int getEffectiveMajorVersion();


    /**
     * Set the effective major version of the Servlet spec used by this
     * context.
     *
     * @param major Set the version number
     */
    public void setEffectiveMajorVersion(int major);


    /**
     * @return the effective minor version of the Servlet spec used by this
     * context.
     */
    public int getEffectiveMinorVersion();


    /**
     * Set the effective minor version of the Servlet spec used by this
     * context.
     *
     * @param minor Set the version number
     */
    public void setEffectiveMinorVersion(int minor);


    /**
     * @return the JSP configuration for this context.
     * Will be null if there is no JSP configuration.
     */
    public JspConfigDescriptor getJspConfigDescriptor();

    /**
     * Set the JspConfigDescriptor for this context.
     * A null value indicates there is not JSP configuration.
     *
     * @param descriptor the new JSP configuration
     */
    public void setJspConfigDescriptor(JspConfigDescriptor descriptor);

    /**
     * Add a ServletContainerInitializer instance to this web application.
     *
     * @param sci       The instance to add
     * @param classes   The classes in which the initializer expressed an
     *                  interest
     */
    public void addServletContainerInitializer(
            ServletContainerInitializer sci, Set<Class<?>> classes);

    /**
     * Is this Context paused whilst it is reloaded?
     *
     * @return <code>true</code> if the context has been paused
     */
    public boolean getPaused();


    /**
     * Is this context using version 2.2 of the Servlet spec?
     *
     * @return <code>true</code> for a legacy Servlet 2.2 webapp
     */
    boolean isServlet22();

    /**
     * Notification that Servlet security has been dynamically set in a
     * {@link javax.servlet.ServletRegistration.Dynamic}
     * @param registration Servlet security was modified for
     * @param servletSecurityElement new security constraints for this Servlet
     * @return urls currently mapped to this registration that are already
     *         present in web.xml
     */
    Set<String> addServletSecurity(ServletRegistration.Dynamic registration,
            ServletSecurityElement servletSecurityElement);

    /**
     * Sets the (comma separated) list of Servlets that expect a resource to be
     * present. Used to ensure that welcome files associated with Servlets that
     * expect a resource to be present are not mapped when there is no resource.
     *
     * @param resourceOnlyServlets The Servlet names comma separated list
     */
    public void setResourceOnlyServlets(String resourceOnlyServlets);

    /**
     * Obtains the list of Servlets that expect a resource to be present.
     *
     * @return  A comma separated list of Servlet names as used in web.xml
     */
    public String getResourceOnlyServlets();

    /**
     * Checks the named Servlet to see if it expects a resource to be present.
     *
     * @param servletName   Name of the Servlet (as per web.xml) to check
     * @return              <code>true</code> if the Servlet expects a resource,
     *                      otherwise <code>false</code>
     */
    public boolean isResourceOnlyServlet(String servletName);

    /**
     * @return the base name to use for WARs, directories or context.xml files
     * for this context.
     */
    public String getBaseName();

    /**
     * Set the version of this web application - used to differentiate
     * different versions of the same web application when using parallel
     * deployment.
     *
     * @param webappVersion The webapp version associated with the context,
     *    which should be unique
     */
    public void setWebappVersion(String webappVersion);

    /**
     * @return The version of this web application, used to differentiate
     * different versions of the same web application when using parallel
     * deployment.
     */
    public String getWebappVersion();

    /**
     * Configure whether or not requests listeners will be fired on forwards for
     * this Context.
     *
     * @param enable <code>true</code> to fire request listeners when forwarding
     */
    public void setFireRequestListenersOnForwards(boolean enable);

    /**
     * @return whether or not requests listeners will be fired on forwards for
     * this Context.
     */
    public boolean getFireRequestListenersOnForwards();

    /**
     * Configures if a user presents authentication credentials, whether the
     * context will process them when the request is for a non-protected
     * resource.
     *
     * @param enable <code>true</code> to perform authentication even outside
     *    security constraints
     */
    public void setPreemptiveAuthentication(boolean enable);

    /**
     * @return if a user presents authentication credentials, will the
     * context will process them when the request is for a non-protected
     * resource.
     */
    public boolean getPreemptiveAuthentication();

    /**
     * Configures if a response body is included when a redirect response is
     * sent to the client.
     *
     * @param enable <code>true</code> to send a response body for redirects
     */
    public void setSendRedirectBody(boolean enable);

    /**
     * @return if the context is configured to include a response body as
     * part of a redirect response.
     */
    public boolean getSendRedirectBody();

    /**
     * @return the Loader with which this Context is associated.
     */
    public Loader getLoader();

    /**
     * Set the Loader with which this Context is associated.
     *
     * @param loader The newly associated loader
     */
    public void setLoader(Loader loader);

    /**
     * @return the Resources with which this Context is associated.
     */
    public WebResourceRoot getResources();

    /**
     * Set the Resources object with which this Context is associated.
     *
     * @param resources The newly associated Resources
     */
    public void setResources(WebResourceRoot resources);

    /**
     * Session相关
     */
    public Manager getManager();


    /**
     * Set the Manager with which this Context is associated.
     *
     * @param manager The newly associated Manager
     */
    public void setManager(Manager manager);

    /**
     * Sets the flag that indicates if /WEB-INF/classes should be treated like
     * an exploded JAR and JAR resources made available as if they were in a
     * JAR.
     *
     * @param addWebinfClassesResources The new value for the flag
     */
    public void setAddWebinfClassesResources(boolean addWebinfClassesResources);

    /**
     * @return the flag that indicates if /WEB-INF/classes should be treated like
     * an exploded JAR and JAR resources made available as if they were in a
     * JAR.
     */
    public boolean getAddWebinfClassesResources();

    /**
     * Add a post construct method definition for the given class, if there is
     * an existing definition for the specified class - IllegalArgumentException
     * will be thrown.
     *
     * @param clazz Fully qualified class name
     * @param method
     *            Post construct method name
     * @throws IllegalArgumentException
     *             if the fully qualified class name or method name are
     *             <code>NULL</code>; if there is already post construct method
     *             definition for the given class
     */
    public void addPostConstructMethod(String clazz, String method);

    /**
     * Add a pre destroy method definition for the given class, if there is an
     * existing definition for the specified class - IllegalArgumentException
     * will be thrown.
     *
     * @param clazz Fully qualified class name
     * @param method
     *            Post construct method name
     * @throws IllegalArgumentException
     *             if the fully qualified class name or method name are
     *             <code>NULL</code>; if there is already pre destroy method
     *             definition for the given class
     */
    public void addPreDestroyMethod(String clazz, String method);

    /**
     * Removes the post construct method definition for the given class, if it
     * exists; otherwise, no action is taken.
     *
     * @param clazz
     *            Fully qualified class name
     */
    public void removePostConstructMethod(String clazz);

    /**
     * Removes the pre destroy method definition for the given class, if it
     * exists; otherwise, no action is taken.
     *
     * @param clazz
     *            Fully qualified class name
     */
    public void removePreDestroyMethod(String clazz);

    /**
     * Returns the method name that is specified as post construct method for
     * the given class, if it exists; otherwise <code>NULL</code> will be
     * returned.
     *
     * @param clazz
     *            Fully qualified class name
     *
     * @return the method name that is specified as post construct method for
     *         the given class, if it exists; otherwise <code>NULL</code> will
     *         be returned.
     */
    public String findPostConstructMethod(String clazz);

    /**
     * Returns the method name that is specified as pre destroy method for the
     * given class, if it exists; otherwise <code>NULL</code> will be returned.
     *
     * @param clazz
     *            Fully qualified class name
     *
     * @return the method name that is specified as pre destroy method for the
     *         given class, if it exists; otherwise <code>NULL</code> will be
     *         returned.
     */
    public String findPreDestroyMethod(String clazz);

    /**
     * Returns a map with keys - fully qualified class names of the classes that
     * have post construct methods and the values are the corresponding method
     * names. If there are no such classes an empty map will be returned.
     *
     * @return a map with keys - fully qualified class names of the classes that
     *         have post construct methods and the values are the corresponding
     *         method names.
     */
    public Map<String, String> findPostConstructMethods();

    /**
     * Returns a map with keys - fully qualified class names of the classes that
     * have pre destroy methods and the values are the corresponding method
     * names. If there are no such classes an empty map will be returned.
     *
     * @return a map with keys - fully qualified class names of the classes that
     *         have pre destroy methods and the values are the corresponding
     *         method names.
     */
    public Map<String, String> findPreDestroyMethods();

    /**
     * @return  the token necessary for operations on the associated JNDI naming
     * context.
     */
    public Object getNamingToken();

    /**
     * Sets the {@link CookieProcessor} that will be used to process cookies
     * for this Context.
     *
     * @param cookieProcessor   The new cookie processor
     *
     * @throws IllegalArgumentException If a {@code null} CookieProcessor is
     *         specified
     */
    public void setCookieProcessor(CookieProcessor cookieProcessor);

    /**
     * @return the {@link CookieProcessor} that will be used to process cookies
     * for this Context.
     */
    public CookieProcessor getCookieProcessor();

    /**
     * When a client provides the ID for a new session, should that ID be
     * validated? The only use case for using a client provided session ID is to
     * have a common session ID across multiple web applications. Therefore,
     * any client provided session ID should already exist in another web
     * application. If this check is enabled, the client provided session ID
     * will only be used if the session ID exists in at least one other web
     * application for the current host. Note that the following additional
     * tests are always applied, irrespective of this setting:
     * <ul>
     * <li>The session ID is provided by a cookie</li>
     * <li>The session cookie has a path of {@code /}</li>
     * </ul>
     *
     * @param validateClientProvidedNewSessionId
     *          {@code true} if validation should be applied
     */
    public void setValidateClientProvidedNewSessionId(boolean validateClientProvidedNewSessionId);

    /**
     * Will client provided session IDs be validated (see {@link
     * #setValidateClientProvidedNewSessionId(boolean)}) before use?
     *
     * @return {@code true} if validation will be applied. Otherwise, {@code
     *         false}
     */
    public boolean getValidateClientProvidedNewSessionId();

    /**
     * If enabled, requests for a web application context root will be
     * redirected (adding a trailing slash) by the Mapper. This is more
     * efficient but has the side effect of confirming that the context path is
     * valid.
     *
     * @param mapperContextRootRedirectEnabled Should the redirects be enabled?
     */
    public void setMapperContextRootRedirectEnabled(boolean mapperContextRootRedirectEnabled);

    /**
     * Determines if requests for a web application context root will be
     * redirected (adding a trailing slash) by the Mapper. This is more
     * efficient but has the side effect of confirming that the context path is
     * valid.
     *
     * @return {@code true} if the Mapper level redirect is enabled for this
     *         Context.
     */
    public boolean getMapperContextRootRedirectEnabled();

    /**
     * If enabled, requests for a directory will be redirected (adding a
     * trailing slash) by the Mapper. This is more efficient but has the
     * side effect of confirming that the directory is valid.
     *
     * @param mapperDirectoryRedirectEnabled Should the redirects be enabled?
     */
    public void setMapperDirectoryRedirectEnabled(boolean mapperDirectoryRedirectEnabled);

    /**
     * Determines if requests for a directory will be redirected (adding a
     * trailing slash) by the Mapper. This is more efficient but has the
     * side effect of confirming that the directory is valid.
     *
     * @return {@code true} if the Mapper level redirect is enabled for this
     *         Context.
     */
    public boolean getMapperDirectoryRedirectEnabled();

    /**
     * Controls whether HTTP 1.1 and later location headers generated by a call
     * to {@link javax.servlet.http.HttpServletResponse#sendRedirect(String)}
     * will use relative or absolute redirects.
     * <p>
     * Relative redirects are more efficient but may not work with reverse
     * proxies that change the context path. It should be noted that it is not
     * recommended to use a reverse proxy to change the context path because of
     * the multiple issues it creates.
     * <p>
     * Absolute redirects should work with reverse proxies that change the
     * context path but may cause issues with the
     * {@link org.apache.catalina.filters.RemoteIpFilter} if the filter is
     * changing the scheme and/or port.
     *
     * @param useRelativeRedirects {@code true} to use relative redirects and
     *                             {@code false} to use absolute redirects
     */
    public void setUseRelativeRedirects(boolean useRelativeRedirects);

    /**
     * Will HTTP 1.1 and later location headers generated by a call to
     * {@link javax.servlet.http.HttpServletResponse#sendRedirect(String)} use
     * relative or absolute redirects.
     *
     * @return {@code true} if relative redirects will be used {@code false} if
     *         absolute redirects are used.
     *
     * @see #setUseRelativeRedirects(boolean)
     */
    public boolean getUseRelativeRedirects();

    /**
     * Are paths used in calls to obtain a request dispatcher expected to be
     * encoded? This affects both how Tomcat handles calls to obtain a request
     * dispatcher as well as how Tomcat generates paths used to obtain request
     * dispatchers internally.
     *
     * @param dispatchersUseEncodedPaths {@code true} to use encoded paths,
     *        otherwise {@code false}
     */
    public void setDispatchersUseEncodedPaths(boolean dispatchersUseEncodedPaths);

    /**
     * Are paths used in calls to obtain a request dispatcher expected to be
     * encoded? This applys to both how Tomcat handles calls to obtain a request
     * dispatcher as well as how Tomcat generates paths used to obtain request
     * dispatchers internally.
     *
     * @return {@code true} if encoded paths will be used, otherwise
     *         {@code false}
     */
    public boolean getDispatchersUseEncodedPaths();

    /**
     * Set the default request body encoding for this web application.
     *
     * @param encoding The default encoding
     */
    public void setRequestCharacterEncoding(String encoding);

    /**
     * Get the default request body encoding for this web application.
     *
     * @return The default request body encoding
     */
    public String getRequestCharacterEncoding();

    /**
     * Set the default response body encoding for this web application.
     *
     * @param encoding The default encoding
     */
    public void setResponseCharacterEncoding(String encoding);

    /**
     * Get the default response body encoding for this web application.
     *
     * @return The default response body encoding
     */
    public String getResponseCharacterEncoding();

    /**
     * Configure if, when returning a context path from {@link
     * javax.servlet.http.HttpServletRequest#getContextPath()}, the return value
     * is allowed to contain multiple leading '/' characters.
     *
     * @param allowMultipleLeadingForwardSlashInPath The new value for the flag
     */
    public void setAllowMultipleLeadingForwardSlashInPath(
            boolean allowMultipleLeadingForwardSlashInPath);

    /**
     * When returning a context path from {@link
     * javax.servlet.http.HttpServletRequest#getContextPath()}, is it allowed to
     * contain multiple leading '/' characters?
     *
     * @return <code>true</code> if multiple leading '/' characters are allowed,
     *         otherwise <code>false</code>
     */
    public boolean getAllowMultipleLeadingForwardSlashInPath();


    public void incrementInProgressAsyncCount();


    public void decrementInProgressAsyncCount();


    /**
     * Configure whether Tomcat will attempt to create an upload target used by
     * this web application if it does not exist when the web application
     * attempts to use it.
     *
     * @param createUploadTargets {@code true} if Tomcat should attempt to
     *          create the upload target, otherwise {@code false}
     */
    public void setCreateUploadTargets(boolean createUploadTargets);


    /**
     * Will Tomcat attempt to create an upload target used by this web
     * application if it does not exist when the web application attempts to use
     * it?
     *
     * @return {@code true} if Tomcat will attempt to create an upload target
     *         otherwise {@code false}
     */
    public boolean getCreateUploadTargets();
}