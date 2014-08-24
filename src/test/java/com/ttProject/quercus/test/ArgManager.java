package com.ttProject.quercus.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArgManager {
	private static ArgManager instance = new ArgManager();
	private Map<String, Object> args = new ConcurrentHashMap<String, Object>();
	public static synchronized ArgManager getInstance() {
		return instance;
	}
	public void setArg(String name, Object value) {
		args.put(name, value);
	}
	public Object getValue(String name) {
		return args.get(name);
	}
}
