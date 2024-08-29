package edu.ucsy.social.data;

import java.lang.reflect.InvocationTargetException;

import edu.ucsy.social.data.annotation.Entity;
import edu.ucsy.social.data.exception.IllegalEntityException;
import edu.ucsy.social.data.exception.NoSuchModelException;

public abstract class ModelFactory {

	private static String location = "edu.ucsy.social.model.%sModel";
	private static String searchableLocation = "edu.ucsy.social.model.%sSearchModel";;

	private static boolean isEntity(Class<?> e) {
		var annotation = e.getDeclaredAnnotation(Entity.class);
		if (null != annotation) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> Model<T> getModel(Class<T> e) {

		if (!isEntity(e)) {
			throw new IllegalEntityException(e);
		}

		try {
			var c = Class.forName(location.formatted(e.getSimpleName()));
			var constructor = c.getDeclaredConstructor();
			var model = constructor.newInstance();

			return (Model<T>) model;

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}

		throw new NoSuchModelException(e);
	}

	@SuppressWarnings("unchecked")
	public static <T> Searchable<T> getSearchModel(Class<T> e) {
		if (!isEntity(e)) {
			throw new IllegalEntityException(e);
		}

		try {
			var c = Class.forName(searchableLocation.formatted(e.getSimpleName()));
			var constructor = c.getDeclaredConstructor();
			var model = constructor.newInstance();

			return (Searchable<T>) model;

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		throw new NoSuchModelException(e);
	}
}