/*
 * Copyright 2007-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.xebia.xke.test.jdbc.datasource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.sql.Driver;

/**
 * <p>
 * H2 DB specific initializing <code>DriverManagerDataSource</code>. If values
 * aren't set for driver class name, url, username, or password defaults for an
 * in memory H2 DB are used.
 * </p>
 * 
 * <p>
 * After the properties are set any database initialization scripts are run.
 * This is very useful for unit testing.
 * </p>
 * 
 * @author Original code from David Winterfeldt
 * 
 * @see org.springframework.jdbc.datasource.SimpleDriverDataSource
 */
public class H2InitializingDriverManagerDataSource extends InitializingDriverManagerDataSource implements InitializingBean {

    protected static final String DRIVER_CLASS_NAME = "org.h2.Driver";

    protected static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    protected static final String USERNAME = "sa";

    protected static final String PASSWORD = "";

    /**
     * Implementation of <code>InitializingBean</code>
     */
    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        if (getDriver() == null && !StringUtils.hasText(driverClassName)) {
            setDriverClass((Class<? extends Driver>) ClassUtils.forName(DRIVER_CLASS_NAME, ClassUtils.getDefaultClassLoader()));
        }

        if (!StringUtils.hasText(getUrl())) {
            setUrl(URL);
        }

        if (!StringUtils.hasText(getUsername())) {
            setUsername(USERNAME);
        }

        if (!StringUtils.hasText(getPassword())) {
            setPassword(PASSWORD);
        }

        super.afterPropertiesSet();
    }

}
