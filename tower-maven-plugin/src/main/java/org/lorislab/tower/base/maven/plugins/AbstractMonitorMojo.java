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
package org.lorislab.tower.base.maven.plugins;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.maven.artifact.manager.WagonManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.wagon.authentication.AuthenticationInfo;
import org.lorislab.tower.base.loader.PrmLoader;
import org.lorislab.tower.base.loader.ManifestLoader;
import org.lorislab.tower.base.model.Prm;
import org.lorislab.tower.base.dto.converter.ObjectConverter;
import org.lorislab.tower.base.dto.model.Version;

/**
 * The abstract monitor MOJO.
 *
 * @author Andrej Petras
 */
public abstract class AbstractMonitorMojo extends AbstractMojo {

    /**
     * The map of the modules and extensions.
     */
    protected static final Map<String, String> MAP = new HashMap<>();

    /**
     * Load the map of the modules and extensions.
     */
    static {
        MAP.put("ejb", "jar");
    }

    /**
     * The MAVEN project.
     */
    @Component
    protected MavenProject project;

    /**
     * The URL parameter.
     */
    @Parameter
    protected URL url;

    /**
     * The username parameter.
     */
    @Parameter
    protected String username;

    /**
     * The password parameter.
     */
    @Parameter
    protected String password;

    /**
     * The server parameter.
     */
    @Parameter
    protected String server;

    /**
     * The MAVEN Wagon manager to use when obtaining server authentication
     * details.
     */
    @Component(role = WagonManager.class)
    protected WagonManager wagonManager;

    /**
     * The path of the file to deploy.
     */
    @Parameter(defaultValue = "${project.build.directory}/${project.build.finalName}", required = true)
    protected String deployFile;

    /**
     * The external path of the file to deploy.
     */
    @Parameter(defaultValue = "", required = false, property = "bridge.deploy.file")
    protected String exDeployFile;

    /**
     * The extension of the file to deploy.
     */
    @Parameter(defaultValue = "${project.packaging}", required = true)
    protected String packing;

    /**
     * The default execution.
     *
     * @throws MojoExecutionException if the execution fails.
     * @throws MojoFailureException if the build process fails.
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        String user = username;
        String pass = password;

        if (server != null) {
            AuthenticationInfo info = wagonManager.getAuthenticationInfo(server);
            if (info == null) {
                throw new MojoExecutionException("Missing server " + server);
            }
            user = info.getUserName();
            pass = info.getPassword();
        }

        String extension = packing;
        if (MAP.containsKey(extension)) {
            extension = MAP.get(extension);
        }

        String tmp = deployFile + "." + extension;
        if (exDeployFile != null && !exDeployFile.isEmpty()) {
            tmp = exDeployFile;
            getLog().info("Deploy the external file: " + tmp);
        } else {
            getLog().info("Deploy the current build file: " + tmp);
        }
        File file = new File(tmp);
        if (file.exists()) {

            Version version = null;
            try {
                URL fileUrl = file.toURI().toURL();
                Prm bridge = PrmLoader.loadFromJar(file);
                if (bridge == null) {
                    getLog().warn("No bridge properties file found in the file: " + tmp);
                }
                Map<String, String> map = ManifestLoader.loadManifestToMap(fileUrl);

                if (bridge != null && map != null) {
                    if (bridge.getDate() != null && bridge.getKey() != null) {
                        version = ObjectConverter.create();
                        ObjectConverter.update(version, bridge);
                        version.manifest = map;
                    }
                }
            } catch (MalformedURLException ex) {
                throw new MojoExecutionException("Error create the version report", ex);
            }

            if (version != null) {
                try {
                    execute(url, user, pass, user != null, version);
                } catch (Exception ex) {
                    getLog().error(ex.getMessage());
                    throw new MojoExecutionException("Error sending the version data to the server", ex);
                }
            } else {
                throw new MojoExecutionException("The version report could not be created! Please check your configuration.");
            }
        } else {
            throw new MojoExecutionException("The file " + tmp + " is missing!");
        }
    }

    /**
     * Execute the call to the monitor service.
     *
     * @param url the URL.
     * @param user the user.
     * @param password the password.
     * @param authetication the authentication flag.
     * @param version the version information.
     * @throws Exception if the method fails.
     */
    protected abstract void execute(URL url, String user, String password, boolean authetication, Version version) throws Exception;
}
