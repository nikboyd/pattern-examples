package dev.educery.resources;

import java.io.*;
import java.util.*;

/**
 * A persistence mechanism for the Resource backing store.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
class ResourceStorage implements Reporting {

    static final ResourceStorage Instance = new ResourceStorage();
    static void loadResources(Registry.Registrar registrar) {
        Instance.loadResources().forEach((k, v) -> {
            registrar.register(new Resource((String)k, (String)v)); }); }

    static void saveResources(Registry registry) { Instance.saveResources(prepare(registry)); }
    static Properties prepare(Registry registry) {
        Properties p = new Properties();
        registry.resourceNames().forEach((name) -> {
            Resource r = registry.resourceNamed(name);
            p.put(r.getName(), r.getValue());
        });
        return p; }

    static final String TestFileLocation = "src/test/resources/test.resource.properties";
    static final File TestFile = new File(TestFileLocation);

    static final String ResourceFileLocation = "target/resources.properties";
    static final File ResourceFile = new File(ResourceFileLocation);

    protected Properties loadResources() {
        Properties results = new Properties();
        loadResources(results, ResourceFile.exists() ? ResourceFile : TestFile);
        if (!ResourceFile.exists()) saveResources(results);
        return results; }

    protected void loadResources(Properties values, File file) {
        try { try (InputStream s = new FileInputStream(file)) { values.load(s); } }
        catch (IOException ex) { error(ex); } }

    protected void saveResources(Properties values) {
        try { try (OutputStream s = new FileOutputStream(ResourceFile)) { values.store(s, ResourceFileLocation); } }
        catch (IOException ex) { error(ex); } }

} // ResourceStorage
