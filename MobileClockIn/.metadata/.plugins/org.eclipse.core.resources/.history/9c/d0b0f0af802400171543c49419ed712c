package test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import ua.server.config.db.CreateDB;
import ua.server.model.entity.Coordinate;
import ua.server.model.entity.Device;
import ua.server.model.entity.Employee;
import ua.server.model.entity.IEntity;

public class TestMain {
	private  static List<Class<?>> classes = Arrays.asList(Device.class);

	public static void main(String[] args) {
		CreateDB cdb = new CreateDB();
		cdb.manageDB(classes, true);
	}

}
