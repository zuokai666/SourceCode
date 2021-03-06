package org.zk.tomcat.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 多个错误组成的一个错误
 * 
 * @author king
 * @date 2019-05-04 21:25:42
 *
 */
public class MultiThrowable extends Throwable {

    private static final long serialVersionUID = 1L;
    
    private List<Throwable> throwables = new ArrayList<>();
    
    /**
     * Add a throwable to the list of wrapped throwables.
     *
     * @param t The throwable to add
     */
    public void add(Throwable t) {
        throwables.add(t);
    }


    /**
     * @return A read-only list of the wrapped throwables.
     */
    public List<Throwable> getThrowables() {
        return Collections.unmodifiableList(throwables);
    }


    /**
     * @return {@code null} if there are no wrapped throwables, the Throwable if
     *         there is a single wrapped throwable or the current instance of
     *         there are multiple wrapped throwables
     */
    public Throwable getThrowable() {
        if (size() == 0) {
            return null;
        } else if (size() == 1) {
            return throwables.get(0);
        } else {
            return this;
        }
    }


    /**
     * @return The number of throwables currently wrapped by this instance.
     */
    public int size() {
        return throwables.size();
    }


    /**
     * Overrides the default implementation to provide a concatenation of the
     * messages associated with each of the wrapped throwables. Note that the
     * format of the returned String is not guaranteed to be fixed and may
     * change in a future release.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(": ");
        sb.append(size());
        sb.append(" wrapped Throwables: ");
        for (Throwable t : throwables) {
            sb.append("[");
            sb.append(t.getMessage());
            sb.append("]");
        }
        return sb.toString();
    }
}