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
package net.formio;

import java.util.Locale;

import net.formio.binding.Instantiator;
import net.formio.binding.StaticFactoryMethod;
import net.formio.internal.FormUtils;
import net.formio.render.BasicFormRenderer;
import net.formio.render.FormMethod;
import net.formio.render.RenderContext;

/**
 * API for form definition and processing.
 * @author Radek Beran
 */
public final class Forms {
	
	public static final String AUTH_TOKEN_FIELD_NAME = "formAuthToken";
	
	/**
	 * Separator of parts in the path (used in fully qualified field name).
	 */
	public static final String PATH_SEP = "-";

	/**
	 * Starts building basic mapping for which all the fields and nested mappings 
	 * must be explicitly specified.
	 * @param editedObjectClass class of form mapping data
	 * @param propertyName name of the form/property with nested data
	 * @return form mapping builder
	 */
	public static <T> BasicFormMappingBuilder<T> basic(Class<T> editedObjectClass, String propertyName) {
		return basic(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Starts building basic mapping for which all the fields and nested mappings 
	 * must be explicitly specified.
	 * @param editedObjectClass class of form mapping data
	 * @param propertyName name of the form/property with nested data
	 * @param instantiator instantiator of form data
	 * @return form mapping builder
	 */
	public static <T> BasicFormMappingBuilder<T> basic(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator) {
		return basic(editedObjectClass, propertyName, instantiator, DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Starts building basic mapping for which all the fields and nested mappings 
	 * must be explicitly specified.
	 * @param editedObjectClass class of form mapping data
	 * @param propertyName name of the form/property with nested data
	 * @param mappingType type of nested mapping
	 * @return form mapping builder
	 */
	public static <T> BasicFormMappingBuilder<T> basic(Class<T> editedObjectClass, String propertyName, MappingType mappingType) {
		return basic(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), mappingType);
	}
	
	/**
	 * Starts building basic mapping for which all the fields and nested mappings 
	 * must be explicitly specified.
	 * @param editedObjectClass
	 * @param propertyName name of the form/property with nested data
	 * @param instantiator instantiator of form data
	 * @param mappingType type of nested mapping
	 * @return form mapping builder
	 */
	public static <T> BasicFormMappingBuilder<T> basic(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator, MappingType mappingType) {
		return mappingInternal(editedObjectClass, propertyName, instantiator, false, mappingType, false);
	}
	
	/**
	 * Like corresponding basic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> basicSecured(Class<T> editedObjectClass, String propertyName) {
		return basicSecured(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Like corresponding basic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param instantiator
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> basicSecured(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator) {
		return basicSecured(editedObjectClass, propertyName, instantiator, DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Like corresponding basic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param mappingType
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> basicSecured(Class<T> editedObjectClass, String propertyName, MappingType mappingType) {
		return basicSecured(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), mappingType);
	}
	
	/**
	 * Like corresponding basic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param instantiator
	 * @param mappingType
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> basicSecured(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator, MappingType mappingType) {
		return mappingInternal(editedObjectClass, propertyName, instantiator, false, mappingType, true);
	}
	
	/**
	 * Starts building mapping that is automatically specified by introspection 
	 * of given data class.
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automatic(Class<T> editedObjectClass, String propertyName) {
		return automatic(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Starts building mapping that is automatically specified by introspection 
	 * of given data class.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param instantiator
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automatic(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator) {
		return automatic(editedObjectClass, propertyName, instantiator, DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Starts building mapping that is automatically specified by introspection 
	 * of given data class.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param mappingType
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automatic(Class<T> editedObjectClass, String propertyName, MappingType mappingType) {
		return automatic(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), mappingType);
	}
	
	/**
	 * Starts building mapping that is automatically specified by introspection 
	 * of given data class.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param instantiator
	 * @param mappingType
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automatic(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator, MappingType mappingType) {
		return mappingInternal(editedObjectClass, propertyName, instantiator, true, mappingType, false);
	}
	
	/**
	 * Like corresponding automatic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automaticSecured(Class<T> editedObjectClass, String propertyName) {
		return automaticSecured(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Like corresponding automatic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param instantiator
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automaticSecured(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator) {
		return automaticSecured(editedObjectClass, propertyName, instantiator, DEFAULT_MAPPING_TYPE);
	}
	
	/**
	 * Like corresponding automatic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param mappingType
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automaticSecured(Class<T> editedObjectClass, String propertyName, MappingType mappingType) {
		return automaticSecured(editedObjectClass, propertyName, Forms.<T>getDefaultInstantiator(), mappingType);
	}
	
	/**
	 * Like corresponding automatic mapping, including CSRF protection.
	 * @param editedObjectClass
	 * @param propertyName
	 * @param instantiator
	 * @param mappingType
	 * @return
	 */
	public static <T> BasicFormMappingBuilder<T> automaticSecured(Class<T> editedObjectClass, String propertyName, Instantiator<T> instantiator, MappingType mappingType) {
		return mappingInternal(editedObjectClass, propertyName, instantiator, true, mappingType, true);
	}
	
	/**
	 * Instantiator that uses static factory method to construct object of type T.
	 * @param constructedClass
	 * @param methodName
	 * @param factoryClass
	 * @return
	 */
	public static <T, U> Instantiator<T> factoryMethod(Class<T> constructedClass, String methodName, Class<U> factoryClass) {
		return new StaticFactoryMethod<T>(factoryClass, methodName);
	}
	
	/**
	 * Instantiator that uses static factory method to construct object of type T.
	 * @param constructedClass
	 * @param methodName
	 * @return
	 */
	public static <T, U> Instantiator<T> factoryMethod(Class<T> constructedClass, String methodName) {
		return factoryMethod(constructedClass, methodName, constructedClass);
	}
	
	/**
	 * Creates configuration for form processing.
	 * @return
	 */
	public static Config.Builder config() {
		return new Config.Builder();
	}
	
	/**
	 * Creates default configuration for form processing.
	 * @param dataClass
	 * @return
	 */
	public static Config defaultConfig(Class<?> dataClass) {
		return Forms.config()
			.messageBundleName(dataClass.getName().replace(".", "/"))
			.build();
	}
	
	/**
	 * Creates specification of form field.
	 * @param propertyName
	 * @param type
	 * @return
	 */
	public static <T> FieldProps<T> field(String propertyName, String type) {
		return new FieldProps<T>(propertyName, type);
	}
	
	/**
	 * Creates specification of form field.
	 * @param propertyName
	 * @return
	 */
	public static <T> FieldProps<T> field(String propertyName) {
		return field(propertyName, (String)null);
	}
	
	/**
	 * Renders form with embedded form renderer and opens resulting HTML
	 * in default browser of operating system.
	 * @param form
	 * @param locale
	 */
	public static <T> void previewForm(FormMapping<T> form, Locale locale) {
		RenderContext<T> ctx = new RenderContext<T>();
		ctx.setFilledForm(form);
		ctx.setMethod(FormMethod.POST);
		ctx.setActionUrl("#");
		ctx.setLocale(locale);
		String html = new BasicFormRenderer().renderHtmlPage(ctx);
		FormUtils.openHtmlInBrowser(html);
	}
	
	/**
	 * Renders form with embedded form renderer and opens resulting HTML
	 * in default browser of operating system.
	 * @param form
	 * @param locale
	 */
	public static <T> void previewForm(FormMapping<T> form) {
		previewForm(form, Locale.ENGLISH);
	}
	
	private static <T> BasicFormMappingBuilder<T> mappingInternal(Class<T> dataClass, String propertyName, Instantiator<T> instantiator, boolean automatic, MappingType mappingType, boolean secured) {
		return new BasicFormMappingBuilder<T>(dataClass, propertyName, instantiator, automatic, mappingType).secured(secured);
	}
	
	private Forms() {
		throw new AssertionError("Not instantiable, use static members.");
	}
	
	private static final MappingType DEFAULT_MAPPING_TYPE = MappingType.SINGLE;
	private static <T> Instantiator<T> getDefaultInstantiator() {
		return null;
	}
}
