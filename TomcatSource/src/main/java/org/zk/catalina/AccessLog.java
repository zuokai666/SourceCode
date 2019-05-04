package org.zk.catalina;

import org.zk.catalina.connector.Request;
import org.zk.catalina.connector.Response;

/**
 * 复制过来，没有分析
 * 
 * @author king
 * @date 2019-05-04 20:57:27
 *
 */
public interface AccessLog {

    public static final String REMOTE_ADDR_ATTRIBUTE =
    	"org.apache.catalina.AccessLog.RemoteAddr";

    /**
     * Name of request attribute used to override remote host name recorded by
     * the AccessLog.
     */
    public static final String REMOTE_HOST_ATTRIBUTE =
        "org.apache.catalina.AccessLog.RemoteHost";

    /**
     * Name of request attribute used to override the protocol recorded by the
     * AccessLog.
     */
    public static final String PROTOCOL_ATTRIBUTE =
        "org.apache.catalina.AccessLog.Protocol";

    /**
     * Name of request attribute used to override the server port recorded by
     * the AccessLog.
     */
    public static final String SERVER_PORT_ATTRIBUTE =
        "org.apache.catalina.AccessLog.ServerPort";


    /**
     * Add the request/response to the access log using the specified processing
     * time.
     *
     * @param request   Request (associated with the response) to log
     * @param response  Response (associated with the request) to log
     * @param time      Time taken to process the request/response in
     *                  milliseconds (use 0 if not known)
     */
    public void log(Request request, Response response, long time);

    /**
     * Should this valve set request attributes for IP address, hostname,
     * protocol and port used for the request? This are typically used in
     * conjunction with the {@link org.apache.catalina.valves.AccessLogValve}
     * which will otherwise log the original values.
     *
     * The attributes set are:
     * <ul>
     * <li>org.apache.catalina.RemoteAddr</li>
     * <li>org.apache.catalina.RemoteHost</li>
     * <li>org.apache.catalina.Protocol</li>
     * <li>org.apache.catalina.ServerPost</li>
     * </ul>
     *
     * @param requestAttributesEnabled  <code>true</code> causes the attributes
     *                                  to be set, <code>false</code> disables
     *                                  the setting of the attributes.
     */
    public void setRequestAttributesEnabled(boolean requestAttributesEnabled);

    /**
     * @see #setRequestAttributesEnabled(boolean)
     * @return <code>true</code> if the attributes will be logged, otherwise
     *         <code>false</code>
     */
    public boolean getRequestAttributesEnabled();
}