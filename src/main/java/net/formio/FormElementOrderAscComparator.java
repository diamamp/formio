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
package net.formio;

import java.util.Comparator;

/**
 * Compares form elements according their order attributes in ascendant direction.
 * @author Radek Beran
 */
class FormElementOrderAscComparator implements Comparator<FormElement> {

	@Override
	public int compare(FormElement e1, FormElement e2) {
		// e1 and e2 should not be null
		return Integer.compare(e1.getOrder(), e2.getOrder());
	}

}
