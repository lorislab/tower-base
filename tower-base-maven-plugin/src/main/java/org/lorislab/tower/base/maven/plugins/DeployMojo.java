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

import java.net.URL;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.lorislab.tower.base.dto.model.Version;
import org.lorislab.tower.base.server.client.ServerClient;

/**
 * The deploy goal.
 * 
 * @author Andrej Petras
 */
@Mojo(name = "deploy",
        defaultPhase = LifecyclePhase.DEPLOY,
        requiresProject = true,
        threadSafe = true)
@Execute(goal = "deploy", phase = LifecyclePhase.PREPARE_PACKAGE)
public class DeployMojo extends AbstractMonitorMojo {

    /**
     * The deploy key parameter.
     */
    @Parameter
    private String deployKey;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(URL url, String user, String password, boolean authetication, Version version) throws Exception {
        ServerClient.deploy(url, user, password, authetication, deployKey, version);
    }
}
