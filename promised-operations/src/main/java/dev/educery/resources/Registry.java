package dev.educery.resources;

import java.util.*;
import static java.lang.Runtime.*;

/**
 * A resource registry.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class Registry {

    private final HashMap<String, Resource> contents = new HashMap();
    public Set<String> resourceNames() { return contents.keySet(); }
    public Resource resourceNamed(String name) { return contents.get(name); }

    private boolean isDirty = false;
    public boolean isDirty() { return this.isDirty; }
    protected void makeDirty() { makeDirty(true); }
    protected void makeDirty(boolean value) { this.isDirty = value; }
    public void flush() { if (isDirty()) saveResources(); }

    // the registry instance creation is lazy
    private static Registry CachedRegistry;
    public static synchronized Registry getRegistry() {
        if (CachedRegistry == null) CachedRegistry = Registry.withResources();
        return CachedRegistry; }

    // Resources are loaded and registered after Registry creation
    protected static Registry withResources() {
        Registry result = new Registry();
        result.loadResources();
        result.registerShutdown();
        return result; }

    /**
     * Registrar has privileged access to the contents of this registry.
     * It gets passed to ResourceStorage to register the loaded Resources.
     */
    protected class Registrar { public void register(Resource r) { contents.put(r.getName(), r); } }
    protected void loadResources() { contents.clear(); ResourceStorage.loadResources(new Registrar()); }

    // Updated resources are saved at shutdown.
    protected void registerShutdown() { getRuntime().addShutdownHook(new Thread(() -> saveResources())); }
    protected void saveResources() { ResourceStorage.saveResources(this); makeDirty(false); }

} // Registry
