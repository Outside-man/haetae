package us.betahouse.haetae.serviceimpl.schedule.dto;



import us.betahouse.util.common.ToString;

import java.util.concurrent.ScheduledFuture;


public class RealTask extends ToString {
	private String cron;
	
	private Object task;
	
	private ScheduledFuture<?> future;

	public String getCron() { return cron; }

	public void setCron(String cron) { this.cron = cron; }

	public Object getTask() { return task; }

	public void setTask(Object task) { this.task = task; }

	public ScheduledFuture<?> getFuture() { return future; }

	public void setFuture(ScheduledFuture<?> future) { this.future = future; }


}
