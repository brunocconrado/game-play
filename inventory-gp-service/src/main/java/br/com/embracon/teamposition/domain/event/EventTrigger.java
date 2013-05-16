package br.com.embracon.teamposition.domain.event;

import java.lang.reflect.Proxy;



//Dispara Evento
public class EventTrigger<L> extends EventTriggerHandle<L> {

	@SuppressWarnings("unused")
	private final Class<L> type;

	private final L proxy;

	public static <T> EventTrigger<T> newInstance(Class<T> type) {
		return new EventTrigger<T>(type);
	}

	private EventTrigger(Class<L> type) {
		this.type = type;
		proxy = type.cast(Proxy.newProxyInstance(this.getClass()
				.getClassLoader(), new Class[]{type}, this));
	}

	public L fire() {
		return proxy;
	}

}
