/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.formio.validation.constraints;

import static org.junit.Assert.*;
import net.formio.validation.constraints.RodneCisloValidation;

import org.junit.Test;

public class RodneCisloValidationTest {

	@Test
	public void testIsValidRc() {
		assertFalse(RodneCisloValidation.isRodneCislo(null));
		assertFalse(RodneCisloValidation.isRodneCislo(""));
		assertFalse(RodneCisloValidation.isRodneCislo("4w4w4qw"));
		
		assertTrue(RodneCisloValidation.isRodneCislo("830719/0562"));
		assertTrue(RodneCisloValidation.isRodneCislo("780123/3540")); // valid even if not divisible by 11
		assertTrue(RodneCisloValidation.isRodneCislo("0531135099"));
		assertTrue(RodneCisloValidation.isRodneCislo("0681186066"));
	}

}
