package com.zyy.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class ScriptEngineDemo {

    @Test
    public void testScriptEngine() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine extensionEngine = manager.getEngineByExtension("js");
        ScriptEngine mimeTypeEngine = manager.getEngineByMimeType("text/javascript");
        ScriptEngine nameEngine = manager.getEngineByName("JavaScript");

        System.out.println(extensionEngine.getClass() == mimeTypeEngine.getClass()); // true
        System.out.println(extensionEngine.getClass() == mimeTypeEngine.getClass()); // true
        System.out.println(nameEngine.getClass() == nameEngine.getClass()); // true
    }

    @Test
    public void testScriptEngineEval() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();

        // ScriptEngine engine = manager.getEngineByExtension("js");
        // ScriptEngine engine = manager.getEngineByMimeType("text/javascript");
        ScriptEngine engine = manager.getEngineByName("JavaScript"); // JavaScript javascript is ok, JAVASCRIPT not ok

        Object obj = engine.eval("1/3");
        System.out.println(obj.getClass().getName()); // java.lang.Double
        System.out.println(obj); // Infinity
    }
}
