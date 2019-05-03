package org.zk.tomcat.util.res;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.util.Assert;

/**
 * 一个包一个单例
 * 
 * @author king
 * @date 2019-05-03 15:11:10
 *
 */
public class StringManager {
	
	public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StringManager.class);
	//key:packageName
	private static final Map<String, Map<Locale,StringManager>> managers = new HashMap<String, Map<Locale, StringManager>>();
	
    private final ResourceBundle bundle;
    private final Locale locale;
    
    public Locale getLocale() {
        return locale;
    }
    
    public String getString(String key){
    	Assert.hasText(key, "key不能为空");
    	return bundle == null ? null : bundle.getString(key);
    }
    
    public String getString(String key, Object... args) {
        String value = getString(key);
        if (value == null) {
            value = key;
        }
        MessageFormat mf = new MessageFormat(value);
        mf.setLocale(locale);
        return mf.format(args, new StringBuffer(), null).toString();
    }
    
	private StringManager(String packageName, Locale locale) {
		String bundleName = packageName + ".LocalStrings";
		ResourceBundle bnd = null;
		try {
			if(locale.getLanguage().equals(Locale.ENGLISH.getLanguage())){
				locale = Locale.ROOT;
			}
			bnd = ResourceBundle.getBundle(bundleName, locale);
		} catch (MissingResourceException e) {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			if(cl != null){
				try {
					bnd = ResourceBundle.getBundle(bundleName, locale, cl);
				} catch (MissingResourceException e1) {
					//do nothing.
				}
			}
		}
		bundle = bnd;
		if (bundle != null) {
            Locale bundleLocale = bundle.getLocale();
            if (bundleLocale.equals(Locale.ROOT)) {
                this.locale = Locale.ENGLISH;
            } else {
                this.locale = bundleLocale;
            }
        } else {
            this.locale = null;
        }
	}
	
	public static final StringManager getManager(Class<?> clazz) {
		return getManager(clazz.getPackage().getName());
	}

	public static final StringManager getManager(String packageName) {
		return getManager(packageName, Locale.getDefault());
	}
	
	/**
	 * 加锁，保证线程安全，一个包一个实例
	 */
	public static final synchronized StringManager getManager(String packageName, Locale locale) {
		Map<Locale,StringManager> map = managers.get(packageName);
        if (map == null) {
        	map = new HashMap<Locale, StringManager>();
        	managers.put(packageName, map);
        }
        StringManager mgr = map.get(locale);
        if (mgr == null) {
            mgr = new StringManager(packageName, locale);
            map.put(locale, mgr);
        }
        return mgr;
	}
}