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
package org.lorislab.tower.base.dto.converter;

import java.util.UUID;
import org.lorislab.tower.base.model.Prm;
import org.lorislab.tower.base.dto.model.Version;

/**
 * The object converter.
 *
 * @author Andrej Petras
 */
public final class ObjectConverter {

    /**
     * The default constructor.
     */
    private ObjectConverter() {
        // empty constructor
    }

    /**
     * Creates the version.
     *
     * @return the version.
     */
    public static Version create() {
        Version result = new Version();
        result.uid = UUID.randomUUID().toString();
        return result;
    }

    /**
     * Update the version with the project release model.
     *
     * @param version the version.
     * @param prm the project release model.
     * @return the version.
     */
    public static Version update(Version version, Prm prm) {
        if (prm != null && version != null) {
            version.date = prm.getDate();
            version.key = prm.getKey();
            
            // add maven attributes
            version.groupId = prm.getGroupId();
            version.artifactId = prm.getArtifactId();
            version.version = prm.getVersion();            
            
            // add release attribtues
            version.scm = prm.getScm();
            version.build = prm.getBuild();
            version.project = prm.getProjectVersion();
            
            // add ARM other attributes
            version.other = prm.getOther();
        }
        return version;
    }

   
}
