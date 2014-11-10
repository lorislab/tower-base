/*
 * Copyright 2014 lorislab.org.
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
package org.lorislab.tower.base.server.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

/**
 * The basic authenticator.
 *
 * @author Andrej_Petras
 */
public class BasicAuthenticator implements ClientRequestFilter {

    /**
     * The token.
     */
    private final String token;

    /**
     * The default constructor.
     *
     * @param user the user.
     * @param password the password.
     */
    public BasicAuthenticator(String user, String password) {
        String tmp = user + ":" + password;
        try {
            token = "BASIC " + DatatypeConverter.printBase64Binary(tmp.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("Cannot encode with UTF-8", ex);
        }
    }

    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        headers.add("Authorization", token);
    }
}
