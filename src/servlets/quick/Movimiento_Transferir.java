package servlets.quick;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import entity.Movimiento;
import entity.Paginator;
import logicImpl.AuthManager;
import logicImpl.MovimientoLogicImpl;
import logicImpl.AuthManager.TokenData;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

@WebServlet("/api/quick/movements/transfer")
public class Movimiento_Transferir extends BaseServlet {

	private static final long serialVersionUID = 1L;

	private MovimientoLogicImpl ML = new MovimientoLogicImpl();
	 //**
	//AGREGAR TRANSFERENCIA
	//
	//**
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Dictionary parameters = getParameters(request);
		if(parameters == null) {
			die(response,false, 400, "Bad request");
			return;
		}
		
		Response<Movimiento> res = ML.createMovement(parameters);
		response.setStatus(res.status ? 201 : 400);
	}
	
	@Override
	protected String[] getAllowedMethods() {
		// TODO Auto-generated method stub
		return new String[] {"POST"};
	}

}
