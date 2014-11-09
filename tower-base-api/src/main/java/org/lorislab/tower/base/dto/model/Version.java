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
    private String uid;
    /**
     * The version.
     */
    private int ver = 1;
    /**
     * The build date.
     */
    private Date date;
    /**
     * The service name.
     */
    private String service;
    /**
     * The MAVEN group id.
     */
    private String groupId;
    /**
     * The MAVEN artifact id.
     */
    private String artifactId;
    /**
     * The MAVEN version.
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
     * The project version.
     */
    private String project;
    /**
     * The other parameters.
     */
    private Map<String, String> other = new HashMap<>();
    /**
     * The manifest.
     */
    private Map<String, String> manifest = new HashMap<>();

    /**
     * Gets the UID.
     *
     * @return the UID.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the UID.
     *
     * @param uid the UID to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Gets the version of the request.
     *
     * @return the version of the request.
     */
    public int getVer() {
        return ver;
    }

    /**
     * Sets the version of the request.
     *
     * @param ver the version of the request.
     */
    public void setVer(int ver) {
        this.ver = ver;
    }

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
     * @param date the date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the service.
     *
     * @return the service.
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the service.
     *
     * @param service the service to set.
     */
    public void setService(String service) {
        this.service = service;
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
     * @param groupId the group ID.
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the artifact ID.
     *
     * @return the artifact ID.
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Sets the artifact ID.
     *
     * @param artifactId the artifact ID.
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
     * @param version the version.
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
     * @param scm the SCM.
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
     * @param build the build.
     */
    public void setBuild(String build) {
        this.build = build;
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

    /**
     * Gets the project.
     *
     * @return the project.
     */
    public String getProject() {
        return project;
    }

    /**
     * Sets the project.
     *
     * @param project the project.
     */
    public void setProject(String project) {
        this.project = project;
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
     * @param other the other parameters.
     */
    public void setOther(Map<String, String> other) {
        this.other = other;
    }

    /**
     * Gets the manifest.
     *
     * @return the manifest.
     */
    public Map<String, String> getManifest() {
        return manifest;
    }

    /**
     * Sets the manifest.
     *
     * @param manifest the manifest.
     */
    public void setManifest(Map<String, String> manifest) {
        this.manifest = manifest;
    }

}
