/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.formio.binding;

import java.util.Collections;

/**
 * Instantiator that already holds ready instance of type T.
 * @author Radek Beran
 * @param <T>
 */
public class InstanceHoldingInstantiator<T> implements Instantiator<T> {
	
	private final T instance;
	
	public InstanceHoldingInstantiator(T instance) {
		if (instance == null) throw new IllegalArgumentException("Instance cannot be null");
		this.instance = instance;
	}

	@Override
	public T instantiate(ConstructionDescription cd, Object... args) {
		return instance;
	}

	@Override
	public ConstructionDescription getDescription(ArgumentNameResolver argNameResolver) {
		return new ConstructionDescription(null, Collections.<String>emptyList());
	}

}
