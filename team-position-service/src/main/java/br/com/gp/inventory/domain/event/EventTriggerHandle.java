package br.com.gp.inventory.domain.event;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

class EventTriggerHandle<L> implements InvocationHandler, Listener<L> {

	private final Set<L> listeners;

	protected EventTriggerHandle() {
		this.listeners = new CopyOnWriteArraySet<L>();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Throwable exception = null;
		
		for (L listener : listeners) {
			try {
				method.invoke(listener, args);
			} catch (RuntimeException e){
				if (exception == null){
					exception = e;
				}
			}
		}

		if (exception != null) {
			throw exception;
		}

		return null;
	}

	@Override
	public void addListener(L listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeListener(L listener) {
		this.listeners.remove(listener);
	}

	
}
