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
package org.lorislab.tower.base.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The manifest loader utility.
 *
 * @author Andrej Petras
 */
public final class ManifestLoader {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ManifestLoader.class.getName());

    /**
     * The WEB-INF folder name in the WAR package.
     */
    private static final String WEB_INF = "WEB-INF";

    /**
     * The EAR package path substring.
     */
    private static final String EAR_SUBSTRING = ".ear/";

    /**
     * The default constructor.
     */
    private ManifestLoader() {
        // empty contrustor
    }

    /**
     * Loads the manifest from WAR.
     *
     * @param clazz the class.
     * @return the manifest attributes.
     */
    public static Map<String, String> loadManifestFromToMap(Class clazz) {
        Manifest tmp = loadManifestFrom(clazz);
        return loadManifestToMap(tmp);
    }

    /**
     * Loads the manifest from WAR.
     *
     * @param clazz the class.
     * @return the manifest.
     */
    public static Manifest loadManifestFrom(Class clazz) {
        Manifest result = null;
        ProtectionDomain domain = clazz.getProtectionDomain();
        if (domain != null) {
            CodeSource codeSource = domain.getCodeSource();
            if (codeSource != null) {
                URL url = codeSource.getLocation();
                String urlString = codeSource.getLocation().toExternalForm();

                if (urlString.contains(EAR_SUBSTRING)) {
                    urlString = urlString.substring(0, urlString.lastIndexOf(EAR_SUBSTRING)) + EAR_SUBSTRING;
                } else if (urlString.contains(WEB_INF)) {
                    urlString = urlString.substring(0, urlString.lastIndexOf(WEB_INF));
                }

                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    LOGGER.log(Level.SEVERE, "Error creating url for String: " + urlString, e);
                }
                result = loadManifest(url);
            }
        }
        return result;
    }

    /**
     * Load the manifest of the JAR file for the class.
     *
     * @param clazz the class
     * @return the manifest attributes of the JAR file for the class.
     */
    public static Map<String, String> loadManifestFromJarToMap(Class clazz) {
        Manifest tmp = loadManifestFromJar(clazz);
        return loadManifestToMap(tmp);
    }

    /**
     * Load the manifest of the JAR file for the class.
     *
     * @param clazz the class
     * @return the manifest of the JAR file for the class.
     */
    public static Manifest loadManifestFromJar(Class clazz) {
        Manifest result = null;
        ProtectionDomain domain = clazz.getProtectionDomain();
        if (domain != null) {
            CodeSource codeSource = domain.getCodeSource();
            if (codeSource != null) {
                result = loadManifest(codeSource.getLocation());
            }
        }
        return result;
    }

    /**
     * Loads the manifest to map.
     *
     * @param manifest the manifest.
     * @return the corresponding map of attributes.
     */
    public static Map<String, String> loadManifestToMap(Manifest manifest) {
        Map<String, String> result = null;
        if (manifest != null) {
            Attributes mainAttribs = manifest.getMainAttributes();
            if (mainAttribs != null) {
                result = new HashMap<>();
                for (Map.Entry<Object, Object> entry : mainAttribs.entrySet()) {
                    result.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        }
        return result;
    }

    /**
     * Read manifest from the URL.
     *
     * @param url the manifest URL.
     * @return the corresponding map of attributes.
     */
    public static Map<String, String> loadManifestToMap(URL url) {
        Manifest tmp = loadManifest(url);
        return loadManifestToMap(tmp);
    }

    /**
     * Read manifest from the URL.
     *
     * @param url the manifest URL.
     * @return the corresponding manifest.
     */
    public static Manifest loadManifest(URL url) {
        Manifest result = null;
        if (url != null) {
            InputStream manifestStream = null;
            try {
                manifestStream = urlToStream(new URL(url + "/" + JarFile.MANIFEST_NAME));
                result = new Manifest(manifestStream);
            } catch (MalformedURLException e1) {
                LOGGER.log(Level.FINEST, e1.getMessage(), e1);
            } catch (IOException e) {
                LOGGER.log(Level.FINEST, e.getMessage(), e);
            } finally {
                if (manifestStream != null) {
                    try {
                        manifestStream.close();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error closing stream: " + e.getMessage(), e);
                    }
                }
            }

            if (result == null) {
                JarInputStream jis = null;
                try {
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setUseCaches(false);
                    if (urlConnection instanceof JarURLConnection) {
                        JarURLConnection jarUrlConnection = (JarURLConnection) urlConnection;
                        result = jarUrlConnection.getManifest();
                    } else {
                        jis = new JarInputStream(urlConnection.getInputStream());
                        result = jis.getManifest();
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error reading META-INF/MANIFEST.MF file: " + e.getMessage(), e);
                } finally {
                    if (jis != null) {
                        try {
                            jis.close();
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Error closing stream: " + e.getMessage(), e);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Creates the input stream to URL.
     *
     * @param url the URL.
     * @return the corresponding stream to the URL.
     * @throws IOException if the method fails.
     */
    private static InputStream urlToStream(URL url) throws IOException {
        if (url != null) {
            URLConnection connection = url.openConnection();
            try {
                connection.setUseCaches(false);
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
            return connection.getInputStream();
        } else {
            return null;
        }
    }
}
