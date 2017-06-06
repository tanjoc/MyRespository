/*
 * Copyright 2015-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.utils.rest;

import cn.edu.utils.rest.exceptions.BadRequestMapper;
import cn.edu.utils.rest.exceptions.IllegalArgumentExceptionMapper;
import cn.edu.utils.rest.exceptions.IllegalStateExceptionMapper;
import cn.edu.utils.rest.exceptions.NotFoundMapper;
import cn.edu.utils.rest.exceptions.ServerErrorMapper;
import cn.edu.utils.rest.exceptions.ServiceNotFoundMapper;
import cn.edu.utils.rest.exceptions.WebApplicationExceptionMapper;
import com.google.common.collect.ImmutableSet;
import cn.edu.utils.rest.exceptions.EntityNotFoundMapper;

import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Base web application.
 */
public abstract class AbstractWebApplication extends Application {

    /**
     * Returns the aggregate set of resources, writers and mappers combined
     * with a default set of such web entities.
     *
     * @param classes set of resources, writers and mappers
     * @return combined set of web entities
     */
    protected Set<Class<?>> getClasses(Class<?>... classes) {
        ImmutableSet.Builder<Class<?>> builder = ImmutableSet.builder();
        builder.add(ServiceNotFoundMapper.class,
                    EntityNotFoundMapper.class,
                    NotFoundMapper.class,
                    ServerErrorMapper.class,
                    BadRequestMapper.class,
                    WebApplicationExceptionMapper.class,
                    IllegalArgumentExceptionMapper.class,
                    IllegalStateExceptionMapper.class,
                    JsonBodyWriter.class);
        builder.add(classes);
        return builder.build();
    }

    @Override
    public abstract Set<Class<?>> getClasses();

}
