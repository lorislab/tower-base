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
package org.lorislab.tower.base.server.client;

import java.net.URL;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.lorislab.tower.base.dto.model.Request;
import org.lorislab.tower.base.dto.model.Result;
import org.lorislab.tower.base.dto.model.Version;
import org.lorislab.tower.base.dto.model.enums.Status;
import org.lorislab.tower.base.rs.service.ServerService;

/**
 * The tower client.
 *
 * @author Andrej Petras
 */
public final class ServerClient {

    /**
     * The agent monitor URL.
     */
    private static final String APP_URL = "tower-rs/service";

    /**
     * The default constructor.
     */
    private ServerClient() {
        // empty constructor
    }

    /**
     * Creates the server service.
     *
     * @param url the URL.
     * @param username the user name.
     * @param password the password.
     * @return the monitor service.
     */
    private static ServerService createService(URL url, String username, String password, boolean auth) {
        ResteasyProviderFactory rpf = ResteasyProviderFactory.getInstance();
        RegisterBuiltin.register(rpf);

        String tmp = url.toString();
        if (!tmp.endsWith("/")) {
            tmp = tmp + "/";
        }
        tmp = tmp + APP_URL;

        ClientExecutor executor = ClientRequest.getDefaultExecutor();
        if (auth) {
            Credentials credentials = new UsernamePasswordCredentials(username, password);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            BasicCredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(AuthScope.ANY, credentials);
            httpClient.setCredentialsProvider(provider);
            executor = new ApacheHttpClient4Executor(httpClient);
        }
        return ProxyFactory.create(ServerService.class, tmp, executor);
    }

    /**
     * Sends the version for the system <code>key</code> to the tower.
     * server.
     *
     * @param url the URL.
     * @param user the user name.
     * @param password the password.
     * @param auth the authentication flag.
     * @param key the system key.
     * @param version the version.
     * @throws java.lang.Exception if the method fails.
     */
    public static void install(URL url, String user, String password, boolean auth, String key, Version version) throws Exception {
        ServerService service = createService(url, user, password, auth);
        Request request = createRequest(key, version);
        Result result = service.install(request);
        if (result != null) {
            if (result.status == null || result.status.equals(Status.ERROR)) {
                throw new Exception("Could not install the build for the application: " + result.message);
            }
        }
    }

    /**
     * Sends the version for the system <code>key</code> to the tower.
     * server.
     *
     * @param url the URL.
     * @param user the user name.
     * @param password the password.
     * @param auth the authentication flag.
     * @param key the system key.
     * @param version the version.
     * @throws java.lang.Exception if the method fails.
     */
    public static void deploy(URL url, String user, String password, boolean auth, String key, Version version) throws Exception {
        ServerService service = createService(url, user, password, auth);
        Request request = createRequest(key, version);
        Result result = service.deploy(request);
        if (result != null) {
            if (result.status == null || result.status.equals(Status.ERROR)) {
                throw new Exception("Could not deploy the build for the system: " + result.message);
            }
        }
    }

    /**
     * Creates the request.
     *
     * @param key the key.
     * @param version the version.
     * @return the corresponding request.
     */
    private static Request createRequest(String key, Version version) {
        Request request = new Request();
        request.version = version;
        request.key = key;
        return request;
    }
}
