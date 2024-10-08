package edu.ucsy.social.data;

import java.lang.reflect.InvocationTargetException;

import edu.ucsy.social.data.db.DatabaseConnector;

public abstract class ModelFactory {

	private static String location = "edu.ucsy.social.model.%sModel";

	@SuppressWarnings("unchecked")
	public static <T> Model<T> getModel(Class<T> e, DatabaseConnector connector) {

		try {
			var c = Class.forName(location.formatted(e.getSimpleName()));
			var constructor = c.getDeclaredConstructor(DatabaseConnector.class);
			var model = constructor.newInstance(connector);

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

		return null;
	}
	
}