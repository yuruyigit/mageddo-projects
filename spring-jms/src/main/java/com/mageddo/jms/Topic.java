package com.mageddo.jms;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Topic implements TopicDefinition {

	private String topic;
	private String factory;
	private int consumers;
	private long interval;
	private long maxInterval;
	private int maxTries;
	private boolean autoConfigure;
	private Map<String, Object> props;
	private RetryStrategy retryStrategy;
	private String dlq;

	public Topic(){
		this.autoConfigure = true;
	}

	public Topic(String topic){
		this();
		this.topic = topic;
	}

	public Topic(String topic, String factory, int consumers, long interval, int maxTries, boolean autoConfigure) {
		this(topic, factory, consumers, interval, maxTries,  autoConfigure, null);
	}

	public Topic(String topic, String factory, int consumers, long interval, int maxTries, boolean autoConfigure, MapBuilder props) {
		this();
		this.topic = topic;
		this.factory = factory;
		this.consumers = consumers;
		this.interval = interval;
		this.maxInterval = interval;
		this.maxTries = maxTries;
		this.autoConfigure = autoConfigure;
		this.props = props == null ? null : props.get();
	}

	public String getName() {
		return topic;
	}

	public int getConsumers() {
		return consumers;
	}

	public String getFactory() {
		return factory;
	}

	public long getInterval() {
		return interval;
	}

	public int getMaxTries() {
		return maxTries;
	}

	public boolean isAutoConfigure() {
		return autoConfigure;
	}

	public Map<String, Object> getProps() {
		return props;
	}

	@Override
	public long getMaxInterval() {
		return maxInterval;
	}

	public Topic topic(String topic) {
		this.topic = topic;
		return this;
	}

	public Topic factory(String factory) {
		this.factory = factory;
		return this;
	}

	public Topic consumers(int consumers) {
		this.consumers = consumers;
		return this;
	}

	public Topic interval(long interval) {
		this.interval = interval;
		return maxInterval(Math.max(maxInterval, interval));
	}

	public Topic interval(Duration duration) {
		return interval(duration.toMillis());
	}

	private Topic maxInterval(Duration duration) {
		return maxInterval(duration.toMillis());
	}

	public Topic maxTries(int maxTries) {
		this.maxTries = maxTries;
		return this;
	}

	public Topic autoConfigure(boolean autoConfigure) {
		this.autoConfigure = autoConfigure;
		return this;
	}

	public Topic props(Map<String, Object> props) {
		this.props = props;
		return this;
	}

	public Topic props(MapBuilder props) {
		this.props = props.get();
		return this;
	}

	public Topic maxInterval(long maxInterval) {
		this.maxInterval = Math.max(maxInterval, getInterval());
		return this;
	}

	@Override
	public RetryStrategy getRetryStrategy() {
		return retryStrategy;
	}

	public Topic retryStrategy(RetryStrategy retryStrategy) {
		this.retryStrategy = retryStrategy;
		return this;
	}

	public static class MapBuilder {
		private final Map<String, Object> map;

		public MapBuilder(Map<String, Object> map) {
			this.map = map;
		}

		public static MapBuilder map(){
			return new MapBuilder(new HashMap<>());
		}

		public MapBuilder prop(String k, Object v){
			this.map.put(k, v);
			return this;
		}

		public Map<String, Object> get(){
			return this.map;
		}
	}

	public String getDlq() {
		return dlq;
	}

	public Topic dlq(String dlq) {
		this.dlq = dlq;
		return this;
	}
}
