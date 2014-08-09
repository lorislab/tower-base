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
package org.lorislab.tower.base.maven.plugins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.lorislab.tower.base.model.PrmConstant;

/**
 * The application release monitor MAVEN PLUGIN.
 * 
 * @author Andrej Petras
 */
@Mojo( name = "build",         
       defaultPhase = LifecyclePhase.PREPARE_PACKAGE,
       requiresProject = true,
       threadSafe = true )
@Execute( goal = "build", phase = LifecyclePhase.PREPARE_PACKAGE)
public class BuildMojo extends AbstractMojo {

    /**
     * The special types for package.
     */
    private static final Set<String> TYPES = new HashSet<>();
    
    /**
     * The static block
     */
    static {
        TYPES.add("jar");
        TYPES.add("ejb");
    }
    
    /**
     * The MAVEN project.
     */
    @Component
    protected MavenProject project;
    
    /**
     * The release SCM number.
     */
    @Parameter( defaultValue = "" )
    protected String releaseScm;
    
    /**
     * The release build number.
     */
    @Parameter( defaultValue = "" )
    protected String releaseBuild;

    /**
     * The output directory.
     */
    @Parameter( defaultValue = "${project.build.directory}/${project.build.finalName}")
    private File outputDirectory;

    /**
     * The output directory.
     */    
    @Parameter( defaultValue = "${project.build.outputDirectory}")    
    protected File outputJarDirectory;
    
    /**
     * The custom parameters.
     */
    @Parameter
    private Map<String,String> custom;
    
    /**
     * The default execution.
     * 
     * @throws MojoExecutionException if the execution fails.
     * @throws MojoFailureException if the build process fails.
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            
            File output = outputDirectory;
            if (TYPES.contains(project.getArtifact().getType())) {                
                output = outputJarDirectory;
            }
          
            
            File dir = new File(output, PrmConstant.DIR_LOCATION);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, PrmConstant.FILE_NAME);

            Properties properties = new Properties();
            properties.put(PrmConstant.MAVEN_GROUP_ID, project.getGroupId());
            properties.put(PrmConstant.MAVEN_ARTIFACT_ID, project.getArtifactId());
            properties.put(PrmConstant.MAVEN_VERSION, project.getVersion());
            
            Date date = new Date();
            properties.put(PrmConstant.RELEASE_DATE, String.valueOf(date.getTime()));
            
            if (releaseScm == null) {
                releaseScm = "";
            }
            properties.put(PrmConstant.RELEASE_SCM, releaseScm);
            
            if (releaseBuild == null) {
                releaseBuild = "";
            }
            properties.put(PrmConstant.RELEASE_BUILD, releaseBuild);
            // create key
            properties.put(PrmConstant.RELEASE_KEY, UUID.randomUUID().toString());
            
            // add custom properties
            if (custom != null && !custom.isEmpty()) {                
                for (Entry<String,String> entry : custom.entrySet()) {
                    if (properties.getProperty(entry.getKey()) == null) {
                        properties.setProperty(entry.getKey(), entry.getValue());
                    }
                }
            }
            
            OutputStream out = null;
            try {
                out = new FileOutputStream( file );
                properties.store(out, "Application release monitor descriptor");
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            
        } catch (IOException ex) {
            throw new MojoExecutionException("Error creating the tower.properties file", ex);
        }
    }
    
}
