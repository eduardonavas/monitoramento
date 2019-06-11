package br.com.vivo.model;

import br.com.vivo.threads.Monitor;

public class MonitorBean {

	private Thread thread;
	private Monitor monitor;
	
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
}
