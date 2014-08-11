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
package org.lorislab.tower.base.dto.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The version model.
 *
 * @author Andrej Petras
 */
public class Version {

    /**
     * The UID of the request.
     */
    public String uid;
    /**
     * The version.
     */
    public int ver = 1;
    /**
     * The build date.
     */
    public Date date;
    /**
     * The service name.
     */
    public String service;
    /**
     * The MAVEN group id.
     */
    public String groupId;
    /**
     * The MAVEN artifact id.
     */
    public String artifactId;
    /**
     * The MAVEN version.
     */
    public String version;
    /**
     * The SCM.
     */
    public String scm;
    /**
     * The build.
     */
    public String build;
    /**
     * The key.
     */
    public String key;   
    /**
     * The project version.
     */
    public String project;
    /**
     * The other parameters.
     */
    public Map<String, String> other = new HashMap<>();
    /**
     * The manifest.
     */
    public Map<String, String> manifest = new HashMap<>();
}
