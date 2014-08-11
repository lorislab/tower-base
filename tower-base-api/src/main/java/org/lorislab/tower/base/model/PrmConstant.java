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
package org.lorislab.tower.base.model;

/**
 * The bridge model constant.
 *
 * @author Andrej Petras
 */
public interface PrmConstant {

    /**
     * The ARM file.
     */
    public static final String FILE_NAME = "prm.properties";
    
    /**
     * The ARM file directory.
     */
    public static final String DIR_LOCATION = "META-INF/tower";

    /**
     * The ARM file location.
     */
    public static final String FILE_LOCATION = DIR_LOCATION + "/" + FILE_NAME;

    /**
     * The MAVEN group id parameter.
     */
    public static final String MAVEN_GROUP_ID = "Maven-GroupId";
    
    /**
     * The MAVEN artifact id parameter.
     */
    public static final String MAVEN_ARTIFACT_ID = "Maven-ArtifactId";

    /**
     * The MAVEN version parameter.
     */
    public static final String MAVEN_VERSION = "Maven-Version";
    
    /**
     * The release SCM.
     */
    public static final String RELEASE_SCM = "Release-Scm";
    
    /**
     * The release build.
     */
    public static final String RELEASE_BUILD = "Release-Build";
    
    /**
     * The release date.
     */
    public static final String RELEASE_DATE = "Release-Date";
    
    /**
     * The key.
     */
    public static final String RELEASE_KEY = "Release-Key";  
    
    /**
     * The project version parameter.
     */
    public static final String PROJECT_VERSION = "Project-Version";    
}
