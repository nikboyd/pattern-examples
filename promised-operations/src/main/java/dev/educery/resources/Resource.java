package dev.educery.resources;

/**
 * A managed resource.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class Resource {

    public static void flushResources() { Registry.getRegistry().flush(); }
    public static Resource named(String name) {
        return Registry.getRegistry().resourceNamed(name); }

    protected Resource(String name, String value) {
        this.name = name; this.value = value; }

    public String getName() { return this.name; }
    protected String name = "";

    public String getValue() { return this.value; }
    protected String value;

    public void setValue(String value) {
        this.value = value; // dirty = unsaved state change
        Registry.getRegistry().makeDirty(); }

    // ... additional resource features ...

} // Resource
