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
package org.lorislab.tower.base.dto.model;

/**
 * The request.
 *
 * @author Andrej Petras
 */
public class Request {

    /**
     * The version.
     */
    private Version version;

    /**
     * The key.
     */
    private String key;

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
     * Gets the version.
     *
     * @return the version.
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the version.
     */
    public void setVersion(Version version) {
        this.version = version;
    }

}
