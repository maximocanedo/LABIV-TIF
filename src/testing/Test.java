package testing;

import max.*;
import logicImpl.*;
import entity.*;

public class Test {
	public static void main(String[] args) {
		SolicitudPrestamoLogicImpl logic = new SolicitudPrestamoLogicImpl();
		Response<SolicitudPrestamo> res = logic.getAll();
		System.out.println(res.toFinalJSON());
	}
}
