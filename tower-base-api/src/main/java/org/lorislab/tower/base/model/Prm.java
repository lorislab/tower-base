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

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The project release model.
 *
 * @author Andrej Petras
 */
public class Prm implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 661979832180196561L;
    /**
     * The data.
     */
    private Date date;
    /**
     * The group ID.
     */
    private String groupId;
    /**
     * The artifact ID.
     */
    private String artifactId;
    /**
     * The version.
     */
    private String version;
    /**
     * The SCM.
     */
    private String scm;
    /**
     * The build.
     */
    private String build;
    /**
     * The key.
     */
    private String key;
    /**
     * The other parameters.
     */
    private Map<String, String> other = new HashMap<>();

    /**
     * Gets the date.
     *
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the group ID.
     *
     * @return the group ID.
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the group ID.
     *
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the artifact ID.
     *
     * @return the artifactId the artifact ID.
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Sets the artifact ID.
     *
     * @param artifactId the artifactId to set
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Gets the version.
     *
     * @return the version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the version to set.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the SCM.
     *
     * @return the SCM.
     */
    public String getScm() {
        return scm;
    }

    /**
     * Sets the SCM.
     *
     * @param scm the SCM to set
     */
    public void setScm(String scm) {
        this.scm = scm;
    }

    /**
     * Gets the build.
     *
     * @return the build.
     */
    public String getBuild() {
        return build;
    }

    /**
     * Sets the build.
     *
     * @param build the build to set.
     */
    public void setBuild(String build) {
        this.build = build;
    }

    /**
     * Gets the other parameters.
     *
     * @return the other parameters.
     */
    public Map<String, String> getOther() {
        return other;
    }

    /**
     * Sets the other parameters.
     *
     * @param other the other to set.
     */
    public void setOther(Map<String, String> other) {
        this.other = other;
    }

    /**
     * Gets the key.
     *
     * @return the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the key.
     */
    public void setKey(String key) {
        this.key = key;
    }

}
