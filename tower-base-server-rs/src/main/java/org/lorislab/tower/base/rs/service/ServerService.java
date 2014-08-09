/*
 * Copyright 2013 lorislab.org.
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
package org.lorislab.tower.base.rs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lorislab.tower.base.dto.model.Request;
import org.lorislab.tower.base.dto.model.Result;

/**
 * The server process service.
 *
 * @author Andrej Petras
 */
@Path("server")
public interface ServerService {

    /**
     * Sends the build request for the system.
     *
     * @param request the request for the system.
     * @return the result.
     */
    @POST
    @Path("deploy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result deploy(Request request);
    
    /**
     * Sends the build request for the application.
     *
     * @param request the request for the application.
     * @return the result.
     */
    @POST
    @Path("install")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result install(Request request);    
}
